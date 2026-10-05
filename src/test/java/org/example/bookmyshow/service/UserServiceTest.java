package org.example.bookmyshow.service;

import org.example.bookmyshow.dto.UserRequestDTO;
import org.example.bookmyshow.dto.UserResponseDTO;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.mapper.UserMapper;
import org.example.bookmyshow.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserMapper userMapper;
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

    @Test
    void saveUser(){
        UserRequestDTO userRequestDTO = new UserRequestDTO("Roman","roman@gmail.com","P@ssw0rd",25);

        User user = new User();
        user.setId(1L);
        user.setName("Roman");
        user.setPassword("P@ssw0rd");
        user.setEmail("roman@gmail.com");
        user.setAge(25);

        when(userMapper.toEntity(userRequestDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserResponseDTO userResponseDTO = new  UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setAge(user.getAge());

        when(userMapper.toUserResponseDTO(user)).thenReturn(userResponseDTO);
        UserResponseDTO response = userService.saveUser(userRequestDTO);

        assertEquals("Roman", response.getName());
        assertEquals("roman@gmail.com", response.getEmail());
        assertEquals(25, response.getAge());

        verify(userRepository).save(user);
        verify(userMapper).toUserResponseDTO(user);
        verify(userMapper).toEntity(userRequestDTO);
    }
}
