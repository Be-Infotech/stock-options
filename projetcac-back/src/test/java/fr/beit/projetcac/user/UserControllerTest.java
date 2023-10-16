package fr.beit.projetcac.user;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

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

                    )));

            webTestClient.post()
                    .uri("/user/signin")
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("""
                            ["toto"]
                            """))
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .json( """
                        {
                            "id": 1,
                            ""
                        }
                    """
                    );
        }
    }

}
