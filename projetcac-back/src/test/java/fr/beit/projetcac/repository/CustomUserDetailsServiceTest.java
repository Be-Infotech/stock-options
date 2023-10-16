package fr.beit.projetcac.repository;

import fr.beit.projetcac.model.User;
import fr.beit.projetcac.service.CustomUserDetailsService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {


    @Mock
    UserRepository userRepository;

    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    @Nested
    class getByUsernameOrEmail {
        @Test
        void shouldReturnUser_whenKnownInDatabase(){
            var expectedUser = Optional.of(new User(
                    1,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            ));

            when(userRepository.findByUsernameOrMail("toto", "toto"))
                    .thenReturn(expectedUser);

            assertEquals(expectedUser, customUserDetailsService.getByUsernameOrEmail("toto"));
        }

        @Test
        void shouldReturnEmpty_whenNotKnownInDatabase(){
            var expectedUser = Optional.<User>empty();

            when(userRepository.findByUsernameOrMail("toto", "toto"))
                    .thenReturn(expectedUser);

            assertEquals(expectedUser, customUserDetailsService.getByUsernameOrEmail("toto"));
        }
    }
    @Nested
    class savePassword{
        @Test
        void shouldReturnNothing_whenSaveInDataBase(){
            var expectedUser = new User(
                    2,
                    "test",
                    "1234",
                    "test@mail.com",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            );
            when(userRepository.save(expectedUser))
                    .doNothing();
        }
    }
}
