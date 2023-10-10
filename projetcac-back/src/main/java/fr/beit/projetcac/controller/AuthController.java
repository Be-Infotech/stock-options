package fr.beit.projetcac.controller;

import fr.beit.projetcac.model.User;
import fr.beit.projetcac.payload.LoginDto;
import fr.beit.projetcac.payload.ResetPasswordDto;
import fr.beit.projetcac.repository.UserRepository;
import fr.beit.projetcac.service.CustomUserDetailsService;
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
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<Optional<User>> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrMail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> userData = customUserDetailsService.getByUsernameOrEmail(userDetails.getUsername());
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        Optional<User> optionalUser = customUserDetailsService.getByUsernameOrEmail(resetPasswordDto.getUsernameOrMail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String password = "1234"; //fonction pour générer un password à ajouter
            user.setPassword(password);
            customUserDetailsService.savePassword(user);
            return new ResponseEntity<>(password, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found with username or email : " + resetPasswordDto.getUsernameOrMail(), HttpStatus.UNAUTHORIZED);
    }
}




