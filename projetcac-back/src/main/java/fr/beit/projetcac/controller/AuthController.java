package fr.beit.projetcac.controller;

import fr.beit.projetcac.model.User;
import fr.beit.projetcac.payload.LoginDto;
import fr.beit.projetcac.payload.ResetPasswordDto;
import fr.beit.projetcac.repository.UserRepository;
import fr.beit.projetcac.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<User> authenticateUser(@RequestBody LoginDto loginDto) {
        return customUserDetailsService.getByUsernameOrEmail(loginDto.getUsernameOrMail())
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return customUserDetailsService.getByUsernameOrEmail(resetPasswordDto.getUsernameOrMail())
                .map(user -> {
                    String password = "1234"; //fonction pour générer un password à ajouter
                    var newUser = new User(
                            user.getId(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getMail(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getProfilePhoto(),
                            user.getCity(),
                            user.getAddress(),
                            user.getCountry(),
                            user.getPostalCode()
                    );
                    customUserDetailsService.savePassword(newUser);
                    return new ResponseEntity<>(password, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("User not found with username or email : " + resetPasswordDto.getUsernameOrMail(), HttpStatus.UNAUTHORIZED));
    }
}




