package org.example.bookmyshow.service;


import org.example.bookmyshow.entity.Booking;
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
//        if(optionalUser.isPresent()){
//            return optionalUser.get();
//        }
//       return null;
       return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(
                        "User with id " + id + " not found"));  // Return a new empty User object if the user doesn't exist.
    }

    public User updateUser(Long id, User user) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            User savedUser = userRepository.save(existingUser);
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


    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User with email " + email + " not found"));
    }


    public List<User> findByName(String name){

        List<User> users = userRepository.findByName(name);

        if (users.isEmpty()) {
            throw new UserNotFoundException(
                    "User with name " + name + " not found");
        }

        return users;
//        return userRepository.findByName(name)                    //this we don't use as its not more radble
//                .filter(users -> !users.isEmpty())
//                .orElseThrow(()                                   //for list<USers> we don't use elsethrow method
//                        -> new UserNotFoundException("User with name " +name + " not found"));

    }

//    //if we go for the optional then we have to write
//    public List<User> findByName(String name) {
//
//        List<User> users = userRepository.findByName(name)
//                .orElseThrow(() -> new UserNotFoundException(
//                        "User with name " + name + " not found"));
//
//        if (users.isEmpty()) {
//            throw new UserNotFoundException(
//                    "User with name " + name + " not found");
//        }
//
//        return users;
//    }


    //test method for booking

    public User createUserWithBooking(){
        User user = new User();
        user.setName("alex ray");
        user.setEmail("alex@gmail.com");

        Booking booking = new Booking();
        booking.setMovieName("the rock");

        //Link to both the sided
        booking.setUser(user);
        user.setBookings(List.of(booking));

        return userRepository.save(user);
    }
}
