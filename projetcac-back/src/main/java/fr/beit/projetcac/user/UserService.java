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
        Optional<User> user = userRepository.findByUsernameOrMail(username, username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
           User userInfo = new User(
                    user.get().getId(),
                    user.get().getUsername(),
                    "####",
                    user.get().getMail(),
                    user.get().getFirstName(),
                    user.get().getLastName(),
                    user.get().getProfilePhoto(),
                    user.get().getCity(),
                    user.get().getAddress(),
                    user.get().getCountry(),
                    user.get().getPostalCode()
            );
           return Optional.of(userInfo);
        } else{
            return Optional.empty();
        }
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
