package fr.beit.projetcac.user;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@WebFluxTest(UserController.class)
@Import(UserControllerTest.TestConfig.class)
public class UserControllerTest {


    @Autowired
    WebTestClient webTestClient;
    @MockBean
    UserService userService;

    static class TestConfig {
        @Bean
        SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
            return http.csrf(ServerHttpSecurity.CsrfSpec::disable).build();
        }
    }

    @Nested
    class authenticateUser {

        @Test
        void shouldReturnUser() {
            when(userService.authenticateUser("toto", "1234"))
                    .thenReturn(Optional.of(new User(
                            1,
                            "toto",
                            "1234",
                            "toto@mail.com",
                            "toto",
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
                                "usernameOrMail" : "toto",
                                "password" : "1234"
                            }
                            """))
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .json("""
                                {
                                    "id": 1,
                                    "username": "toto",
                                    "password": "1234",
                                    "mail": "toto@mail.com",
                                    "firstName": "toto",
                                    "lastName": "",
                                    "profilePhoto": "",
                                    "city": "",
                                    "address": "",
                                    "country": "",
                                    "postalCode": "" 
                                }
                            """
                    );
        }
        @Test
        void shouldReturnUnauthorized_whenBadUserOrPassword(){
            when(userService.authenticateUser("toto", "1234"))
                    .thenReturn(Optional.empty());



            webTestClient.post()
                    .uri("/user/signin")
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("""
                            {
                                "usernameOrMail" : "toto",
                                "password" : "1234"
                            }
                            """))
                    .exchange()
                    .expectStatus().isUnauthorized();
        }
        @Test
        void shouldReturnBadRequest_whenNoBody(){
            when(userService.authenticateUser(anyString(), anyString()))
                    .thenReturn(Optional.empty());



            webTestClient.post()
                    .uri("/user/signin")
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("""
                     
                            """))
                    .exchange()
                    .expectStatus().isBadRequest();
        }
    }

}
