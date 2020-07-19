package hp.harsh.projectbrainapispringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hp.harsh.projectbrainapispringboot.form.NewIdeaForm;
import hp.harsh.projectbrainapispringboot.form.TodoForm;
import hp.harsh.projectbrainapispringboot.model.Brain;
import hp.harsh.projectbrainapispringboot.model.Idea;
import hp.harsh.projectbrainapispringboot.repository.BrainRepository;
import hp.harsh.projectbrainapispringboot.repository.IdeaRepository;

@RestController
public class RelationalController {

    private final BrainRepository brainRepository;
    private final IdeaRepository ideaRepository;

    @Autowired
    public RelationalController(BrainRepository brainRepository, IdeaRepository ideaRepository) {
        this.brainRepository = brainRepository;
        this.ideaRepository = ideaRepository;
    }

    @PostMapping("/assign/new_idea")
    public Idea assignIdeaToBrain(@RequestBody NewIdeaForm newIdeaForm) {
        try {
            Brain brain = brainRepository.findBrainByUsername(newIdeaForm.getUsername()).orElseThrow();

            Idea idea = new Idea();
            idea.setOriginalId(newIdeaForm.getOriginalId());
            idea.setTitle(newIdeaForm.getTitle());
            idea.setContext(newIdeaForm.getContext());
            idea.setContent(newIdeaForm.getContent());
            idea.setAuthor(brain);

            Idea responseIdea = ideaRepository.save(idea);

            brain.getIdeas().add(responseIdea);

            brainRepository.save(brain);

            return responseIdea;
        } catch (Exception e) {
            e.printStackTrace();
            return new Idea();
        }
    }

    @PostMapping("/assign/todo")
    public Brain assignToDoToBrain(@RequestBody TodoForm todoForm) {
        try {
            Idea idea = ideaRepository.findIdeaById(todoForm.getIdeaId()).orElseThrow();
            Brain brain = brainRepository.findBrainByUsername(todoForm.getUsername()).orElseThrow();

            brain.getTodos().add(idea);

            Brain responseBrain = brainRepository.save(brain);

            return responseBrain;
        } catch (Exception e) {
            e.printStackTrace();
            return new Brain();
        }
    }
}