package org.example.bookmyshow.repository;

import org.example.bookmyshow.dto.UserDTO;
import org.example.bookmyshow.dto.UserRecordDTO;
import org.example.bookmyshow.entity.User;
import org.example.bookmyshow.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    @Query("SELECT u FROM User u WHERE email=?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE name=?1")
    List<User> findByName(String name);

    //@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.bookings")
    // List<User> findAllUsersWithBooking();       //  using join Fetch to solve N+1 Query problem (JPQL used)

    @EntityGraph(attributePaths = {"bookings"})// using entitygraph to overcome N+1 Query problem and need not write to jPQL query
    @Query("SELECT u FROM User u ORDER BY u.name ASC")    // sorting using JPQL query - only used in Repo no changes in controller and service
    Page<User> findAll(Pageable pageable);   // Using PAgination here divide data and also to use sorting

    @Query("SELECT u.id AS id, u.name AS name, u.email AS email FROM User u")  // interface projection
    List<UserProjection> findUserProjection();

    UserProjection findUserProjectionById(Long id);

    @Query("SELECT new org.example.bookmyshow.dto.UserDTO(u.id,u.name,u.email)FROM User u")   // Constructor Projection
    List<UserDTO> findUserDTO();

    @Query("SELECT new org.example.bookmyshow.dto.UserRecordDTO(u.id,u.name,u.email) FROM User u")  // Record Projection
    List<UserRecordDTO> findUserRecordDTO();
}
