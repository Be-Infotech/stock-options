package fr.beit.projetcac.repository;

import fr.beit.projetcac.model.User;
import fr.beit.projetcac.service.CustomUserDetailsService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
public class CustomUserDetailsServiceTest {



    @Mock
    private UserRepository userRepository;


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        final User user = new User(
                1, "test", "1234", "test@mail.com", "", "", "", "", "", "", ""
        );

        Mockito.when(userRepository.findByUsernameOrMail(user.getUsername(), user.getMail()))
                .thenReturn(Optional.of(user));
    }

    @Test
    public void test() {
        final String username = "test";

        final UserDetails userBDD = this.customUserDetailsService.loadUserByUsername(username);
        userBDD.getUsername();assertEquals(username,userBDD.getUsername());
    }

}
