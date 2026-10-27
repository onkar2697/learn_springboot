package org.example.bookmyshow.dto;

//ResponseDTO for username available or not
public class EmailAvailabilityResponse {
    String email;
    boolean available;

    public EmailAvailabilityResponse(String email, boolean available) {
        this.email = email;
        this.available = available;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
