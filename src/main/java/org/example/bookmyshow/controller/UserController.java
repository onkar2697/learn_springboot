package org.example.bookmyshow.controller;

import org.example.bookmyshow.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    public UserController(UserService  service){
        this.service = service;
    }

    @GetMapping
    public String getUser(){
        return service.welcomeMessage();
        //return "Hello Welcome to the bookmyshow controller";
    }
}
