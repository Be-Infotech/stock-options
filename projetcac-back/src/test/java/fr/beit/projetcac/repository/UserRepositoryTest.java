package fr.beit.projetcac.repository;

import fr.beit.projetcac.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        userRepository.save(new User(
                1,"test1","","test1@mail.com","","","","","","",""
        ));

        assertEquals(1, userRepository.findAll().size());
        userRepository.save(new User(
                2,"test2","","test2@mail.com","","","","","","",""
        ));
        assertTrue(userRepository.findById(1).isPresent());
        assertTrue(userRepository.findByMail("test1@mail.com").isPresent());
        assertTrue(userRepository.findByUsername("test2").isPresent());
        assertTrue(userRepository.findByUsernameOrMail("test2","test2").isPresent());
        assertTrue(userRepository.existsByMail("test2@mail.com"));
        assertTrue(userRepository.existsByUsername("test1"));
        assertTrue(userRepository.findByUsernameOrMail(null,"test2@mail.com").isPresent());
        assertTrue(userRepository.findByUsernameOrMail("test1",null).isPresent());
        assertFalse(userRepository.findByUsernameOrMail(null,null).isPresent());
    }
}

