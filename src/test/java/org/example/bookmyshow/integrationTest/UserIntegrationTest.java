package org.example.bookmyshow.integrationTest;


import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.repository.UserRepository;
import org.example.bookmyshow.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UserIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void shouldCreateAndRetriveUser(){
        User user = new User();
        user.setName("new user");
        user.setEmail("newuser@gmail.com");
        user.setPassword("P@ssw0rd");
        user.setAge(26);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());

        User retrivalUser = userRepository.findByEmail("newuser@gmail.com").orElseThrow();

        assertEquals("new user",retrivalUser.getName());
        assertEquals(26,retrivalUser.getAge());
    }

}
