package fr.beit.projetcac.user;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/updateData")
    public Mono<User> getUserInfo(@RequestBody UserInfoDto userInfoDto){
        return Mono.justOrEmpty(userService.updateUserInfo(userInfoDto))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
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

    @Value
    public static class UserInfoDto{
        String username;
        String mail;
        String profilePhoto;
        String firstName;
        String lastName;
        String address;
        String city;
        String country;
        String postalCode;
    }
}




