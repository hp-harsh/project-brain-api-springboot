package hp.harsh.projectbrainapispringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import hp.harsh.projectbrainapispringboot.form.IdeaForm;
import hp.harsh.projectbrainapispringboot.form.IdeaRemovedForm;
import hp.harsh.projectbrainapispringboot.form.IdeaResponseForm;
import hp.harsh.projectbrainapispringboot.model.Idea;
import hp.harsh.projectbrainapispringboot.repository.IdeaRepository;

import org.springframework.web.bind.annotation.*;
import java.util.HashSet;

@RestController
public class IdeaController {
    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @GetMapping("/ideas")
    public IdeaResponseForm findAll() {
        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            responseForm.setData(new HashSet<Idea>(ideaRepository.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Idea>());
        }
        return responseForm;
    }

    @GetMapping("/idea")
    public Idea findOne(@RequestParam Long id) {
        return ideaRepository.findIdeaById(id).orElseThrow();
    }

    @DeleteMapping("/idea/remove")
    public IdeaRemovedForm removeOne(@RequestParam Long id) {
        Idea idea = ideaRepository.findById(id).orElseThrow();
        idea.getAuthor().getIdeas().remove(idea);
        idea.setAuthor(null);

        ideaRepository.save(idea);

        ideaRepository.deleteById(id);

        IdeaRemovedForm ideaRemovedForm = new IdeaRemovedForm();
        ideaRemovedForm.setId("" + id);

        return ideaRemovedForm;
    }

    @PostMapping("/ideas")
    public Idea save(@RequestBody IdeaForm ideaForm) {
        Idea idea = new Idea();
        idea.setTitle(ideaForm.getTitle());
        idea.setContext(ideaForm.getContent());
        idea.setContent(ideaForm.getContent());
        return ideaRepository.save(idea);
    }
}