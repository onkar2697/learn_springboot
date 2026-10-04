package org.example.bookmyshow.service;

import org.example.bookmyshow.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;


public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

}
