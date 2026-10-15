package org.example.bookmyshow.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    private SecretKey secretKey;

    @jakarta.annotation.PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    //Generate the Access token
    public String generateAccessToken(String email) {

        Date now = new Date();

        Date expiry = new Date(
                now.getTime() + accessTokenExpiration
        );

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    //Extracting mail from token
    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    //Extract the claims
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Validate the token
    public boolean isTokenValid(String token, String email) {
        String tokenEmail =  extractEmail(token);

        return tokenEmail.equals(email)
                && extractAllClaims(token).getExpiration().after(new Date());
    }

}
