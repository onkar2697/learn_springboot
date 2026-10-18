package org.example.bookmyshow.service;

import org.example.bookmyshow.dto.LoginRequestDTO;
import org.example.bookmyshow.dto.LoginResponseDTO;
import org.example.bookmyshow.dto.RefreshTokenRequestDTO;
import org.example.bookmyshow.dto.RefreshTokenResponseDTO;
import org.example.bookmyshow.entity.RefreshToken;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.repository.UserRepository;
import org.example.bookmyshow.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
//Service logic for authentication
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public final UserRepository userRepository;
    public final RefreshTokenService refreshTokenService;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository,
            RefreshTokenService refreshTokenService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        String accessToken =
                jwtService.generateAccessToken(request.getEmail());

        String refreshToken =
                refreshTokenService.createRefreshToken(user);

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    public RefreshTokenResponseDTO refreshAccessToken(RefreshTokenRequestDTO request) {

        String rawToken = request.getRefreshToken();

        RefreshToken refreshToken =
                refreshTokenService.validateRefreshToken(rawToken);

        String email = refreshToken.getUser().getEmail();
        String newAccessToken = jwtService.generateAccessToken(email);

        return new RefreshTokenResponseDTO(newAccessToken);

    }
}
