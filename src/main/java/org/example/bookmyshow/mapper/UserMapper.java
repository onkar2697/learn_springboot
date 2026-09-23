package org.example.bookmyshow.mapper;

import org.example.bookmyshow.dto.UserRequestDTO;
import org.example.bookmyshow.dto.UserResponseDTO;
import org.example.bookmyshow.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO userRequestDTO){
        User user =  new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setAge(userRequestDTO.getAge());
        user.setPassword(userRequestDTO.getPassword());

        return user;
    }

    public UserResponseDTO toUserResponseDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setAge(user.getAge());
        return userResponseDTO;
    }
}
