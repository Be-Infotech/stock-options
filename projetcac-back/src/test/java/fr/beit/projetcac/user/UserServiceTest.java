package fr.beit.projetcac.user;

import fr.beit.projetcac.user.User;
import fr.beit.projetcac.user.UserService;
import fr.beit.projetcac.user.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Nested
    class authenticateUser {
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

            assertEquals(expectedUser, userService.authenticateUser("toto","1234"));
        }

        @Test
        void shouldReturnEmpty_whenNotKnownInDatabase(){
            var expectedUser = Optional.<User>empty();

            when(userRepository.findByUsernameOrMail("toto", "toto"))
                    .thenReturn(expectedUser);

            assertEquals(expectedUser, userService.authenticateUser("toto","1234"));
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
                    .thenReturn(expectedUser);

            userService.savePassword(expectedUser);
        }
    }
}
