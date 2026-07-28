package org.example.bookmyshow.service;


import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public String welcomeMessage(){
        return "Welcome to Book My Show!";
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
