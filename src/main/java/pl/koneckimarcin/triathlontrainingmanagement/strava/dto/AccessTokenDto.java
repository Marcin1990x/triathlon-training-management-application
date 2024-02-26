package pl.koneckimarcin.triathlontrainingmanagement.strava.dto;

public class AccessTokenDto {

    private String accessToken;
    private String expiresAt;

    public AccessTokenDto(String accessToken, String expiresAt) {
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiresAt() {
        return expiresAt;
    }
}
