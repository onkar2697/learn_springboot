package org.example.bookmyshow.controller;

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

    @PostMapping
    public User saveUser(@RequestBody User user){
        return service.saveUser(user);
    }
}
