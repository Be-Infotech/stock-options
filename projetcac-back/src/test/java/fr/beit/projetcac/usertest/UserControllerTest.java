package fr.beit.projetcac.usertest;

import fr.beit.projetcac.user.User;
import fr.beit.projetcac.user.UserController;
import fr.beit.projetcac.user.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

@WebFluxTest(UserController.class)
public class UserControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    UserService userService;

    @Nested
    class authenticateUser{

        //Bien authentifié
        //Pas authentifié
        //Appel avec un mauvais body


        @Test
        void shouldReturnUser(){
            when(userService.authenticateUser("toto", "thisisaspassword"))
                    .thenReturn(Optional.of(new User(
                            1,
                            "toto",
                            "1234",
                            "test@mail.com",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                    )));

            webTestClient.post()
                    .uri("/user/signin")
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("""
                            {
                            "usernameOrMail" : "toto"
                            "password" : "1234"
                            }
                            """))
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .json( """
                        {
                            "id": 1,
                            "username": "toto"
                            "password": "",
                            
                        }
                    """
                    );
        }
    }

}
