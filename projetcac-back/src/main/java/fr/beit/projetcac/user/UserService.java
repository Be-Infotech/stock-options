package fr.beit.projetcac.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> authenticateUser(String username, String password) {
        return userRepository.findByUsernameOrMail(username, username)
                .filter(user1 -> passwordEncoder.matches(password, user1.getPassword()))
                .map( user1 -> new User(
                        user1.getId(),
                        user1.getUsername(),
                        "####",
                        user1.getMail(),
                        user1.getFirstName(),
                        user1.getLastName(),
                        user1.getProfilePhoto(),
                        user1.getCity(),
                        user1.getAddress(),
                        user1.getCountry(),
                        user1.getPostalCode()
                ));
    }


    public Mono<String> savePassword(String usernameOrMail) {
        return Mono.justOrEmpty(userRepository.findByUsernameOrMail(usernameOrMail, usernameOrMail))
                .map(user -> new User(
                        user.getId(),
                        user.getUsername(),
                        passwordEncoder.encode("1234"),
                        user.getMail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getProfilePhoto(),
                        user.getCity(),
                        user.getAddress(),
                        user.getCountry(),
                        user.getPostalCode()
                ))
                .flatMap(user -> Mono.justOrEmpty(userRepository.save(user)))
                .map(User::getPassword);
    }

}
