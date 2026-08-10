package org.example.bookmyshow.service;


import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
//    public String welcomeMessage(){
//        return "Welcome to Book My Show!";
//    }

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

    public User getUserById(Long id){
//        Optional<User> optionalUser = userRepository.findById(id);
//
//        if(optionalUser.isPresent()){
//            return optionalUser.get();
//        }
//       return null;
       return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(
                        "User with id " + id + " not found"));
       // Return a new empty User object if the user doesn't exist.
    }

    public User updateUser(Long id, User user) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            User existingUser = optionalUser.get();
//            System.out.println("5. Existing name: " + existingUser.getName());
//            System.out.println("6. Existing email: " + existingUser.getEmail());

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());

//            System.out.println("7. New name: " + existingUser.getName());
//            System.out.println("8. New email: " + existingUser.getEmail());

            User savedUser = userRepository.save(existingUser);
//            System.out.println("9. Saved user: " + savedUser.getName());

            return savedUser;
        }

        System.out.println("10. User NOT FOUND");

        throw new UserNotFoundException("User with id "+id +" not found");
    }

    public void deleteUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }
}
