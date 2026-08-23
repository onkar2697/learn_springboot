package org.example.bookmyshow.dto;

public class BookingRequestDTO {

    private String movieName;
    private String userId;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String user_id) {
        this.userId = user_id;
    }
}
