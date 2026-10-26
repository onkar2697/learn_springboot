package org.example.bookmyshow.dto;

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
