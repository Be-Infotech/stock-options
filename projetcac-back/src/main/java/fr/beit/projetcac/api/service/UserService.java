package fr.beit.projetcac.api.service;

import fr.beit.projetcac.api.model.User;
import fr.beit.projetcac.api.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getUsers(){return userRepository.findAll(); }
}
