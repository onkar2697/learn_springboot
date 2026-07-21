package org.example.bookmyshow.service;


import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String welcomeMessage(){
        return "Welcome to Book My Show!";
    }
}
