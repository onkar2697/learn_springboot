package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.LoginRequestDTO;
import org.example.bookmyshow.dto.LoginResponseDTO;
import org.example.bookmyshow.dto.RefreshTokenRequestDTO;
import org.example.bookmyshow.dto.RefreshTokenResponseDTO;
import org.example.bookmyshow.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Checks authorized user and allows to login
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request) {

        return authService.login(request);
    }

    @PostMapping("/refresh")
    public RefreshTokenResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO request) {

        return authService.refreshAccessToken(request);
    }
}
