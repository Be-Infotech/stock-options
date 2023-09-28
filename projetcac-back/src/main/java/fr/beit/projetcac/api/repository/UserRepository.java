package fr.beit.projetcac.api.repository;

import fr.beit.projetcac.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
