package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

public class AuthenticationResponseDto {

    private Long userId;
    private Long athleteId;
    // todo: below to be done
    // private Long coachId
    // private boolean isStravaAuthenticated
    // access token validation expiration


    public AuthenticationResponseDto() {
    }
    public AuthenticationResponseDto(Long userId, Long athleteId) {
        this.userId = userId;
        this.athleteId = athleteId;
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
}
