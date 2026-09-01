package org.example.bookmyshow.service;


import org.example.bookmyshow.dto.UserDTO;
import org.example.bookmyshow.dto.UserRecordDTO;
import org.example.bookmyshow.entity.Booking;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.projection.UserProjection;
import org.example.bookmyshow.repository.UserRepository;
import org.example.bookmyshow.specifications.UserSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public Page<User> getAllUsers(Pageable pageable){

        //return userRepository.findAllUsersWithBooking(); //used this for join Fetch Query
        return userRepository.findAll(pageable);
    }

//    public Page<User> getAllUsers() {
//        Pageable pageable = PageRequest.of(0, 5,
//                Sort.by("name").ascending());  //sorting using  sort.by method
//
//        return userRepository.findAll(pageable);
//    }

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
            return;
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

//    public User createUserWithBooking(){
//        User user = new User();
//        user.setName("alex ray");
//        user.setEmail("alex@gmail.com");
//
//        Booking booking = new Booking();
//        booking.setMovieName("the rock");
//
//        //Link to both the sided
//        booking.setUser(user);
//        user.setBookings(List.of(booking));
//
//        return userRepository.save(user);
//    }

//    public User updateUserAndBooking(Long id){
//        User user = userRepository.findById(id)
//                .orElseThrow(()->
//                        new UserNotFoundException("User with id " + id + " not found"));
//
//        user.setName("Updated alex");
//
//        Booking booking = user.getBookings().get(0);
//        booking.setMovieName("Spider Man");
//
//
//        return userRepository.save(user);
//    }

    public void deleteUSerAndBooking(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    public List<UserProjection>  findUserProjection(){
        return userRepository.findUserProjection();
    }

    public UserProjection findUserProjectionById(Long id){
        return userRepository.findUserProjectionById(id);
    }

    public List<UserDTO> findUserDTO(){
        return userRepository.findUserDTO();
    }

    public List<UserRecordDTO> findUserRecordDTO(){
        return userRepository.findUserRecordDTO();
    }

    public Page<User> findUsersBySpecification(String name, Pageable pageable) {

        Specification<User> specification =
                UserSpecifications.hasName(name);

        return userRepository.findAll(specification,pageable);
    }
}
