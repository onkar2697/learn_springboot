package org.example.bookmyshow.repository;

import org.example.bookmyshow.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserTest(){
        User user = new User();
        user.setName("new user");
        user.setEmail("newuser@gmail.com");
        user.setPassword("P@ssw0rd");
        user.setAge(26);

//        assertEquals("new user",userRepository.findById(26L).get().getName());
//        assertEquals("P@ssw0rd",userRepository.findById(26L).get().getPassword());

        User savedUser = userRepository.save(user);

        assertEquals("new user", savedUser.getName());
        assertEquals(26, savedUser.getAge());
        assertEquals("newuser@gmail.com", savedUser.getEmail());
        assertEquals("P@ssw0rd", savedUser.getPassword());

    }
}
