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
        user.setId(26L);
        user.setAge(26);

        userRepository.save(user);

        assertEquals("new user",userRepository.findById(26L).get().getName());
        assertEquals("P@ssw0rd",userRepository.findById(26L).get().getPassword());
        assertEquals("newuser@gmail.com",userRepository.findById(26L).get().getEmail());
        assertEquals(26,userRepository.findById(26L).get().getAge());

    }

}
