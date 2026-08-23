package org.example.bookmyshow.repository;

import org.example.bookmyshow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE email=?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE name=?1")
    List<User> findByName(String name);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.bookings")
    List<User> findAllUserWithBookings();


}
