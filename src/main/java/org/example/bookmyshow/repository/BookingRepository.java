package org.example.bookmyshow.repository;

import org.example.bookmyshow.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {


}
