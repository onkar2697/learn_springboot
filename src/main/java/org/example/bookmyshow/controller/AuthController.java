package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.LoginRequestDTO;
import org.example.bookmyshow.dto.LoginResponseDTO;
import org.example.bookmyshow.dto.RefreshTokenRequestDTO;
import org.example.bookmyshow.dto.RefreshTokenResponseDTO;
import org.example.bookmyshow.service.AuthService;
import org.example.bookmyshow.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Checks authorized user and allows to login
@RestController
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService,  RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestBody RefreshTokenRequestDTO request) {

        refreshTokenService.revokeRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok("Logged out successfully");
    }
}
