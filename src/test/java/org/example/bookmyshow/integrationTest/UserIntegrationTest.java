package org.example.bookmyshow.integrationTest;


import org.example.bookmyshow.dto.UserRequestDTO;
import org.example.bookmyshow.dto.UserResponseDTO;
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

    @Test
    void shouldCreateAndRetrieveUserThroughService() {

        UserRequestDTO request = new UserRequestDTO(
                "Roman",
                "roman.integration@gmail.com",
                "P@ssw0rd",
                26
        );

        //create user through the real service
        UserResponseDTO createdUser = userService.saveUser(request);

        // verify the response
        assertNotNull(createdUser.getId());
        assertEquals("Roman", createdUser.getName());
        assertEquals("roman.integration@gmail.com", createdUser.getEmail());
        assertEquals(26, createdUser.getAge());

        // retrieve the user through the real service
        User retrievedUser = userService.getUserById(createdUser.getId());

        // verify the persisted user
        assertEquals(createdUser.getId(), retrievedUser.getId());
        assertEquals("Roman", retrievedUser.getName());
        assertEquals("roman.integration@gmail.com", retrievedUser.getEmail());
        assertEquals(26, retrievedUser.getAge());
    }

}
