package fr.beit.projetcac.user;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Nested
    class authenticateUser {
        @Test
        void shouldReturnUser_whenKnownInDatabase() {
            var bddUser = Optional.of(new User(
                    1,
                    "toto",
                    "encodedPassword",
                    "toto@mail.com",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            ));
            var expectedUser = Optional.of(new User(
                    1,
                    "toto",
                    "####",
                    "toto@mail.com",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            ));
            when(passwordEncoder.matches("1234","encodedPassword"))
                    .thenReturn(true);

            when(userRepository.findByUsernameOrMail("toto","toto"))
                    .thenReturn(bddUser);
            assertEquals(expectedUser, userService.authenticateUser("toto","1234"));
        }

        @Test
        void shouldReturnEmpty_whenNotKnownInDatabase() {
            var expectedUser = Optional.<User>empty();
//            when(passwordEncoder.matches("1234", anyString()))
//                    .thenReturn(false);

            when(userRepository.findByUsernameOrMail("toto","toto"))
                    .thenReturn(expectedUser);

            assertEquals(expectedUser, userService.authenticateUser("toto", "1234"));
        }
        @Test
        void shouldReturnEmpty_whenWrongPassword() {
            var bddUser = Optional.of(new User(
                    1,
                    "toto",
                    "encodedPassword",
                    "toto@mail.com",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            ));
            var expectedUser = Optional.<User>empty();
            when(passwordEncoder.matches("1234", "encodedPassword"))
                    .thenReturn(false);

            when(userRepository.findByUsernameOrMail("toto","toto"))
                    .thenReturn(bddUser);

            assertEquals(expectedUser, userService.authenticateUser("toto", "1234"));
        }
    }

    @Nested
    class savePassword {
        @Test
        void shouldReturnNothing_whenSaveInDataBase() {
            var expectedUser = new User(
                    2,
                    "test",
                    "newPassword",
                    "test@mail.com",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            );

            when(userRepository.save(new User(
                            2,
                            "test",
                            "newPassword",
                            "test@mail.com",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                    ))
            )
                    .thenReturn(expectedUser);

            when(passwordEncoder.encode("1234"))
                    .thenReturn("newPassword");

            when(userRepository.findByUsernameOrMail("test", "test"))
                    .thenReturn(Optional.of(expectedUser));

            StepVerifier.create(userService.savePassword("test"))
                    .expectNext("newPassword")
                    .verifyComplete();
        }
    }
}
