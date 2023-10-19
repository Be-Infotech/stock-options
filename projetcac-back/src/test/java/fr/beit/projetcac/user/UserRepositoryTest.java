package fr.beit.projetcac.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsernameOrMail() {
        var user1 = userRepository.save(new User(
                1, "test1", "", "test1@mail.com", "", "", "", "", "", "", ""
        ));

        var user2 = userRepository.save(new User(
                2, "test2", "", "test2@mail.com", "", "", "", "", "", "", ""
        ));

        assertEquals(user2, userRepository.findByUsernameOrMail(null, "test2@mail.com").get());
        assertEquals(user1, userRepository.findByUsernameOrMail("test1", null).get());
        assertTrue(userRepository.findByUsernameOrMail(null, null).isEmpty());
    }

    @Test
    public void findById() {
        var user1 = userRepository.save(new User(
                3, "test1", "", "test1@mail.com", "", "", "", "", "", "", ""
        ));

        var user2 = userRepository.save(new User(
                4, "test2", "", "test2@mail.com", "", "", "", "", "", "", ""
        ));

        assertEquals(user1, userRepository.findById(3).get());
    }
}

