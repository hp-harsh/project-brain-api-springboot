package hp.harsh.projectbrainapispringboot.repository;

import java.util.Optional;
import java.util.Set;

import hp.harsh.projectbrainapispringboot.model.Idea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    Optional<Idea> findIdeaById(Long id);
    Set<Idea> findIdeaByTitleContainingIgnoreCase(String title);
}
