package fr.beit.projetcac.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    //Pas authentifié
    //Authentifié et connu en base

    public Optional<User> authenticateUser(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userRepository.findByUsernameOrMail(userDetails.getUsername(), userDetails.getUsername())
                    .map(user -> new User(
                            user.getId(),
                            user.getUsername(),
                            "",
                            user.getMail(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getProfilePhoto(),
                            user.getCity(),
                            user.getAddress(),
                            user.getCountry(),
                            user.getPostalCode()
                    ));
        }
        else
            return Optional.empty();
    }

    public String savePassword(String usernameOrMail){
        Optional<User> optionalUser = userRepository.findByUsernameOrMail(usernameOrMail, usernameOrMail);
        if (optionalUser.isPresent()){
          User user = optionalUser.get();
          String password = "1234";
          user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return password;
        }
        else {
            return "";
        }
    }
}
