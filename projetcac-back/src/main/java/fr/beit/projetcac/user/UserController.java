package fr.beit.projetcac.user;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


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
    public  Mono<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return userService.savePassword(resetPasswordDto.getUsernameOrMail())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
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




