package org.example.bookmyshow.dto;

public class UserResponseDTO {
    private String name;
    private String email;
    private Integer age;
    private Long id;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String name, String email, Integer age, Long id) {
        this.name = name;
        this.email = email;
        this.age = age;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
