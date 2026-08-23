package org.example.bookmyshow.service;


import org.example.bookmyshow.dto.BookingRequestDTO;
import org.example.bookmyshow.entity.Booking;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.repository.BookingRepository;
import org.example.bookmyshow.repository.UserRepository;

import java.util.List;

public class BookinService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public BookinService(UserRepository userRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(BookingRequestDTO bookingRequestDTO) {
        return null;
    }

}
