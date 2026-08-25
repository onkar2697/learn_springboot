package org.example.bookmyshow.repository;

import org.example.bookmyshow.entity.Booking;
import org.example.bookmyshow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {


}
