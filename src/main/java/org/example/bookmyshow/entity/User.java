package org.example.bookmyshow.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Let mysql/database generate the ids it generates id sequestially
    private long  Id;
    private String name;
    private String email;
    private long age;
    private String password;

    @OneToMany(
            mappedBy = "user",cascade = CascadeType.ALL
//            cascade = { CascadeType.PERSIST,
//                    CascadeType.MERGE,
//                    CascadeType.REMOVE }

    )
    @JsonManagedReference
    private List<Booking> bookings;


    public long getId() {
        return Id;
    }
    public void setId(long id) {
        this.Id = id;
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

    public List<Booking> getBookings() {
        return bookings;
    }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
