package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

public class AuthenticationResponseDto {

    private Long userId;
    private Long athleteId;
    private boolean isAthlete;
    private boolean hasRefreshToken;
    private String stravaAccessExpiresAt;
    private Long coachId;
    private boolean isCoach;
    // todo: below to be done
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

    public boolean isAthlete() {
        return isAthlete;
    }

    public void setAthlete(boolean athlete) {
        isAthlete = athlete;
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

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public boolean isCoach() {
        return isCoach;
    }

    public void setCoach(boolean coach) {
        isCoach = coach;
    }
}
