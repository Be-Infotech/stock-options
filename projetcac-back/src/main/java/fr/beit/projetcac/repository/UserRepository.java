package fr.beit.projetcac.repository;

import fr.beit.projetcac.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMail(String mail);

    Optional<User> findByUsernameOrMail(String username, String mail);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByMail(String mail);
}
