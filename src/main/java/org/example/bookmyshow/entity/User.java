package org.example.bookmyshow.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;


}
