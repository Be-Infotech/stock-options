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
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public UserDetails loadUserByUsername(String usernameOrMail) throws UsernameNotFoundException{
        User user = userRepository.findByUsernameOrMail(usernameOrMail, usernameOrMail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + usernameOrMail));
        return new org.springframework.security.core.userdetails.User(user.getMail(),
                user.getPassword(),
                new ArrayList<>());
    }

    public Optional<User> getByUsernameOrEmail(String usernameOrEmail) { return userRepository.findByUsernameOrMail(usernameOrEmail,usernameOrEmail);}

    public void savePassword(User user){ userRepository.save(user);}
}