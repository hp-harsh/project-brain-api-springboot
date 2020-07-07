package hp.harsh.projectbrainapispringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hp.harsh.projectbrainapispringboot.model.Brain;


@Repository
public interface BrainRepository extends JpaRepository<Brain, Long> {

    Optional<Brain> findBrainByUsername(String username);
    Optional<Brain> findBrainByEmailAndPassword(String email, String password);
    //Optional<Brain> findBrainByEmail(String email);
}