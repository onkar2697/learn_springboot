package org.example.bookmyshow.repository;

import org.example.bookmyshow.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

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

    @Test
    void findUserByMail(){
        User user = new User();
        user.setName("alex");
        user.setEmail("alex@gmail.com");
        user.setPassword("P@ssw0rd");
        user.setAge(30);

        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());

        assertEquals("alex", savedUser.get().getName());
        assertEquals(30, savedUser.get().getAge());

    }

    @Test
    void findUserByName(){
        User user = new User();
        user.setName("alex");
        user.setEmail("alex@gmail.com");
        user.setPassword("P@ssw0rd");
        user.setAge(30);

        User user1 = new User();
        user1.setName("alex");
        user1.setEmail("alex25@gmail.com");
        user1.setPassword("P@ssw0rd1");
        user1.setAge(31);

        User user3 = new User();
        user3.setName("aj roy");
        user3.setEmail("sdfdsf@gmail");
        user3.setPassword("P@ssw0rd2");
        user3.setAge(32);

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user3);

        List<User> result = userRepository.findByName("alex");
        assertEquals(2,result.size());
    }

    @Test
    void findAllWithPagination(){
        User user = new User();
        user.setName("alex");
        user.setEmail("alex@gmail.com");
        user.setPassword("P@ssw0rd");
        user.setAge(30);

        User user1 = new User();
        user1.setName("alex");
        user1.setEmail("alex25@gmail.com");
        user1.setPassword("P@ssw0rd1");
        user1.setAge(31);

        User user3 = new User();
        user3.setName("aj roy");
        user3.setEmail("sdfdsf@gmail");
        user3.setPassword("P@ssw0rd2");
        user3.setAge(32);

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user3);

        Page<User> result = userRepository.findAll(PageRequest.of(0,2));

        assertEquals(3,result.getTotalElements());  // total users
        assertEquals(2,result.getContent().size()); // total users on single page
        assertEquals(2,result.getTotalPages());     // total pages
    }
}
