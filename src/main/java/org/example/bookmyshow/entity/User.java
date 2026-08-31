package org.example.bookmyshow.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //Let mysql/database generate the ids it generates id sequestially
    private long  Id;
    @NotBlank(message = "Name must not be blank")  // Using Validations
    private String name;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Enter a valid email")
    private String email;
    @Min(value = 18, message= "age must be greater than 18")
    private long age;
    @NotBlank
    @Size(min = 6,max =15, message = "Password should be greater than 6 and less than 15 characters")
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
