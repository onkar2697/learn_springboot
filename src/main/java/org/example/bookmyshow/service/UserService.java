package org.example.bookmyshow.service;


import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        return null;   // We'll improve this later
    }
}
