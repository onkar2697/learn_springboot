package org.example.bookmyshow.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncoderTest {

    @Test
    public void testPasswordEncoder() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String rawPassword = bCryptPasswordEncoder.encode("BookMyShow@123");

        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);

        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(bCryptPasswordEncoder.matches(rawPassword, encodedPassword));
        assertFalse(bCryptPasswordEncoder.matches("password", encodedPassword));
    }
}
