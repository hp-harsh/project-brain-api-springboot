package hp.harsh.projectbrainapispringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import hp.harsh.projectbrainapispringboot.form.IdeaForm;
import hp.harsh.projectbrainapispringboot.model.Idea;
import hp.harsh.projectbrainapispringboot.repository.IdeaRepository;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class IdeaController {
    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @GetMapping("/ideas")
    public Collection<Idea> findAll() {
        return ideaRepository.findAll();
    }

    @GetMapping("/idea")
    public Idea findOne(@RequestParam Long id) {
        return ideaRepository.findIdeaById(id).orElseThrow();
    }

    @DeleteMapping("/idea/remove")
    public String removeOne(@RequestParam Long id) {
        ideaRepository.deleteById(id);
        return "idea";
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