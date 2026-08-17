package org.example.bookmyshow.entity;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Let mysql/database generate the ids it generates id sequestially
    private long  id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
