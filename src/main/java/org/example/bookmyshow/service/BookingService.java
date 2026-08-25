package org.example.bookmyshow.service;


import org.example.bookmyshow.dto.BookingRequestDTO;
import org.example.bookmyshow.entity.Booking;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.repository.BookingRepository;
import org.example.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(BookingRequestDTO bookingRequestDTO) {
        User user = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(
                        ()->new UserNotFoundException("User not Found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setMovieName(bookingRequestDTO.getMovieName());

        return bookingRepository.save(booking);

    }

}
