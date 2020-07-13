package hp.harsh.projectbrainapispringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hp.harsh.projectbrainapispringboot.form.BrainFollowForm;
import hp.harsh.projectbrainapispringboot.form.BrainForm;
import hp.harsh.projectbrainapispringboot.form.BrainLoginForm;
import hp.harsh.projectbrainapispringboot.form.BrainResponseForm;
import hp.harsh.projectbrainapispringboot.form.BrainUpdateProfileForm;
import hp.harsh.projectbrainapispringboot.form.IdeaResponseForm;
import hp.harsh.projectbrainapispringboot.model.Brain;
import hp.harsh.projectbrainapispringboot.model.Idea;
import hp.harsh.projectbrainapispringboot.repository.BrainRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class BrainController {
    private final BrainRepository brainRepository;

    @Autowired
    public BrainController(BrainRepository brainRepository) {
        this.brainRepository = brainRepository;
    }

    @GetMapping("/brains")
    public Collection<Brain> findAll() {
        return brainRepository.findAll();
    }

    @GetMapping("/brain")
    public Brain findOneByUsername(@RequestParam String username) {
        return brainRepository.findBrainByUsername(username).orElseThrow();
    }

    @PostMapping("/brain/register")
    public Brain save(@RequestBody BrainForm brainForm) {
        Brain brain = new Brain();
        brain.setEmail(brainForm.getEmail());
        brain.setPassword(brainForm.getPassword());
        brain.setUsername(brainForm.getUsername());
        brain.setFirstname(brainForm.getFirstname());
        brain.setLastname(brainForm.getLastname());

        Brain responseBrain = brainRepository.save(brain);

        // Empty set instead of null to prevent,null pointer in android app
        Set<Brain> arrBrains = Collections.<Brain> emptySet();
        
        responseBrain.setFollowers(arrBrains);
        return responseBrain;
    }

    @PostMapping("/brain/login")
    public Brain save(@RequestBody BrainLoginForm brainLoginForm) {
        Brain brain = brainRepository.findBrainByEmailAndPassword(brainLoginForm.getEmail(), brainLoginForm.getPassword()).orElseThrow();
        return brain;
    }

    @GetMapping(value = "/brain/{username}/ideas")
    public IdeaResponseForm getIdeasForBrain(@PathVariable String username) {
        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            responseForm.setData(brainRepository.findBrainByUsername(username).orElseThrow().getIdeas());
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Idea>());
        }
        return responseForm;
    }

    @GetMapping("/brain/{username}/todos")
    public IdeaResponseForm getTodosForBrain(@PathVariable String username) {

        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            responseForm.setData(brainRepository.findBrainByUsername(username).orElseThrow().getTodos());
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Idea>());
        }
        return responseForm;
    }

    @GetMapping("/brain/{username}/followers")
    public BrainResponseForm getFollowersForBrain(@PathVariable String username) {
        BrainResponseForm responseForm = new BrainResponseForm();

        try {
            responseForm.setData(brainRepository.findBrainByUsername(username).orElseThrow().getFollowers());

        } catch (Exception e) {
            e.printStackTrace();
            
            responseForm.setData(new HashSet<Brain>());
        }
        return responseForm;
    }

    @PostMapping("/brain/update")
    public Brain save(@RequestBody BrainUpdateProfileForm brainUpdateProfileForm) {
        Brain brain = brainRepository.findBrainByUsername(brainUpdateProfileForm.getUsername()).orElseThrow();
        
        brain.setFirstname(brainUpdateProfileForm.getFirstname());
        brain.setLastname(brainUpdateProfileForm.getLastname());

        Brain responseBrain = brainRepository.save(brain);

        return responseBrain;
    }

    @PostMapping("/brain/follow")
    public Brain follow(@RequestBody BrainFollowForm brainFollowForm) {
        Brain brainToFollow = brainRepository.findBrainByUsername(brainFollowForm.getUsername()).orElseThrow();
        Brain brainToFollowed = brainRepository.findBrainByUsername(brainFollowForm.getUsernameToBeFollowed()).orElseThrow();
        
        brainToFollow.getFollowers().add(brainToFollowed);

        Brain responseBrain = brainRepository.save(brainToFollow);

        return responseBrain;
    }
}