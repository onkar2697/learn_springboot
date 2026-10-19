package org.example.bookmyshow.dto;


//To get the refresh token response
public class RefreshTokenResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer";  //default as bearer

    public RefreshTokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
