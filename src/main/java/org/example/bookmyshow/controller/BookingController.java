package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.BookingRequestDTO;
import org.example.bookmyshow.entity.Booking;
import org.example.bookmyshow.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bookings")
public class BookingController {

    public final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking creteBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        return bookingService.createBooking(bookingRequestDTO);
    }

}
