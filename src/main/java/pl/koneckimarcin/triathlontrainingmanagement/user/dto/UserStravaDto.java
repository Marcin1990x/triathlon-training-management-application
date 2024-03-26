package pl.koneckimarcin.triathlontrainingmanagement.user.dto;

public class UserStravaDto {

    private String expiresAt;

    public UserStravaDto(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }
}
