package fr.beit.projetcac.service;

import fr.beit.projetcac.model.User;
import fr.beit.projetcac.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService{

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){this.userRepository = userRepository;}


    public Optional<User> getByUsernameOrEmail(String usernameOrEmail) { return userRepository.findByUsernameOrMail(usernameOrEmail,usernameOrEmail);}

    public void savePassword(User user){ userRepository.save(user);}
}
