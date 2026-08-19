package org.example.bookmyshow.controller;

import jakarta.websocket.server.PathParam;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService  service){
        this.service = service;
    }

      @GetMapping
      public List<User> getUsers() {
          return service.getAllUsers();
      }

    @GetMapping("/{id}")
    public User getUSerById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/name/{name}")
    public List<User> findByName(@RequestParam String name){ return service.findByName(name);}
    // request param - when we want to filter/searching/sorting the data

    @PostMapping
    public User saveUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return service.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

    //testing
    @PostMapping("/test")
    public User createUserWithBooking() {
        return service.createUserWithBooking();
    }

    @PutMapping("/merge/{id}")
    public User mergeExample(@PathVariable Long id) {
        return service.updateUserAndBooking(id);
    }


}
