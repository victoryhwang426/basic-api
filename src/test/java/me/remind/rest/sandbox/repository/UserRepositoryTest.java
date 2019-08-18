package me.remind.rest.sandbox.repository;

import me.remind.rest.sandbox.configuration.JpaAuditConfig;
import me.remind.rest.sandbox.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = JpaAuditConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User tempUser = User.builder()
            .firstName("Seungri")
            .surName("Hwang")
            .position("Java developer")
            .githubProfileUrl("https://github.com/victoryhwang426")
            .build();

    @Test
    public void save_should_return_saved_entity(){
        Date currentDate = new Date();
        User user = userRepository.save(tempUser);

        assertNotNull(user);
        assertEquals(tempUser.getId(), user.getId());
        assertTrue(user.getCreated().getTime() >= currentDate.getTime());
    }

    @Test
    public void delete_should_delete_entity(){
        User savedUser = userRepository.save(tempUser);

        userRepository.delete(savedUser);

        Optional<User> optUser = userRepository.findById(savedUser.getId());

        assertFalse(optUser.isPresent());
    }

    @Test
    public void findAll_should_return_all_saved_entities(){
        userRepository.save(tempUser);

        List<User> users = userRepository.findAll();

        assertEquals(1, users.size());
    }

    @Test
    public void findById_should_return_specify_entity(){
        User savedUser = userRepository.save(tempUser);
        Optional<User> optUser = userRepository.findById(savedUser.getId());

        assertTrue(optUser.isPresent());
        assertEquals(savedUser.getId(), optUser.get().getId());
    }
}