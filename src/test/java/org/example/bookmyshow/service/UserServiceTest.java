package org.example.bookmyshow.service;

import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void returnUserIfExists(){
        User user = new User();
        user.setId(2L);
        user.setName("Abe Characterless");
        user.setPassword("nooneisusers");
        user.setEmail("wasted.gmail.com");

        when(userRepository.findByIdAndDeletedFalse(2L))
                .thenReturn(Optional.of(user));

        User result = userService.getUserById(2L);

        assertEquals("Abe Characterless", result.getName());
    }

   @Test
    void throwExceptionIfUserNotFound(){
        when(userRepository.findByIdAndDeletedFalse(2565L))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                ()->userService.getUserById(2565L));
    }

}
