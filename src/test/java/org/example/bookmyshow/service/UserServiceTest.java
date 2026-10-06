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

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void updateUser(){
        User existingUser = new User();
        existingUser.setId(3L);
        existingUser.setName("Roman");
        existingUser.setPassword("P@ssw0rd");
        existingUser.setAge(26);
        existingUser.setEmail("roman@gmail.com");

        User updatedData = new User();
        updatedData.setId(3L);
        updatedData.setName("NewRoman");
        updatedData.setEmail("new@gmail.com");
        updatedData.setPassword("P@ssw0rd1");
        updatedData.setAge(26);

        when(userRepository.findByIdAndDeletedFalse(3L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(3L,updatedData);

        assertEquals("NewRoman", result.getName());
        assertEquals(26, result.getAge());
        assertEquals("new@gmail.com", result.getEmail());
        assertEquals("P@ssw0rd1", result.getPassword());

        verify(userRepository).save(existingUser);
        verify(userRepository).findByIdAndDeletedFalse(3L);
    }

    @Test
    void softDeleteUserTest(){
        User existingUser =  new User();
        existingUser.setId(3L);
        existingUser.setName("Roman");
        existingUser.setPassword("P@ssw0rd");
        existingUser.setAge(26);
        existingUser.setEmail("abcd");

        when(userRepository.findByIdAndDeletedFalse(3L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

         userService.softDeleteTest(3L);

         verify(userRepository).save(existingUser);
         verify(userRepository).findByIdAndDeletedFalse(3L);  //for hard delete we have to write   verify(userRepository).findById(3L);
   //      verify(userRepository).delete(existingUser);      //we should not verify as its delted insted write it as
        assertTrue(existingUser.isDeleted());
    }

    @Test
    void softDeleteUserNotFound(){
        when(userRepository.findByIdAndDeletedFalse(999L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                ()-> userService.softDeleteTest(999L));
    }
}
