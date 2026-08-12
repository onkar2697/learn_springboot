package org.example.bookmyshow.repository;

import org.example.bookmyshow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User FindByEmail(String email);

}
