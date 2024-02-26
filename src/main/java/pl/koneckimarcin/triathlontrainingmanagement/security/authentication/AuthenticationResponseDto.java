package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

public class AuthenticationResponseDto {

    private Long userId;
    private Long athleteId;
    private boolean hasRefreshToken;
    private String stravaAccessExpiresAt;
    // todo: below to be done
    // private Long coachId
    // private boolean isStravaAuthenticated
    // access token validation expiration


    public AuthenticationResponseDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public boolean isHasRefreshToken() {
        return hasRefreshToken;
    }

    public void setHasRefreshToken(boolean hasRefreshToken) {
        this.hasRefreshToken = hasRefreshToken;
    }

    public String getStravaAccessExpiresAt() {
        return stravaAccessExpiresAt;
    }

    public void setStravaAccessExpiresAt(String stravaAccessExpiresAt) {
        this.stravaAccessExpiresAt = stravaAccessExpiresAt;
    }
}
