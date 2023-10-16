package fr.beit.projetcac.user;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public Mono<User> authenticateUser(@RequestBody LoginDto loginDto) {
        return Mono.justOrEmpty(userService.authenticateUser(loginDto.getUsernameOrMail(), loginDto.getPassword()))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        Optional<User> optionalUser = userService.authenticateUser(resetPasswordDto.getUsernameOrMail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String password = "1234"; //fonction pour générer un password à ajouter
            user.setPassword(password);
            userService.savePassword(user);
            return new ResponseEntity<>(password, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found with username or email : " + resetPasswordDto.getUsernameOrMail(), HttpStatus.UNAUTHORIZED);
    }

    @Value
    public static class LoginDto {
        String usernameOrMail;
        String password;
    }

    @Value
    public static class ResetPasswordDto {
        String usernameOrMail;
    }
}




