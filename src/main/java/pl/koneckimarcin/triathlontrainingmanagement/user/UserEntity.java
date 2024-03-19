package pl.koneckimarcin.triathlontrainingmanagement.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.role.RoleEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;

    @NotEmpty
    private String password;

    @Email
    @NotEmpty
    private String emailAddress;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "athlete_id")
    private AthleteEntity athleteEntity;

    @OneToOne
    @JoinColumn(name = "coach_id")
    private CoachEntity coachEntity;

    @Column(name = "refresh")
    private String stravaRefreshToken;

    @Column(name = "access")
    private String stravaAccessToken;

    @Column(name = "access_expiration")
    private String stravaAccessTokenExpirationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public AthleteEntity getAthleteEntity() {
        return athleteEntity;
    }

    public void setAthleteEntity(AthleteEntity athleteEntity) {
        this.athleteEntity = athleteEntity;
    }

    public CoachEntity getCoachEntity() {
        return coachEntity;
    }

    public void setCoachEntity(CoachEntity coachEntity) {
        this.coachEntity = coachEntity;
    }

    public String getStravaRefreshToken() {
        return stravaRefreshToken;
    }

    public void setStravaRefreshToken(String stravaRefreshToken) {
        this.stravaRefreshToken = stravaRefreshToken;
    }

    public String getStravaAccessToken() {
        return stravaAccessToken;
    }

    public void setStravaAccessToken(String stravaAccessToken) {
        this.stravaAccessToken = stravaAccessToken;
    }

    public String getStravaAccessTokenExpirationTime() {
        return stravaAccessTokenExpirationTime;
    }

    public void setStravaAccessTokenExpirationTime(String stravaAccessTokenExpirationTime) {
        this.stravaAccessTokenExpirationTime = stravaAccessTokenExpirationTime;
    }

    public boolean hasAssignedAthlete() {
        return this.athleteEntity != null;
    }

    public boolean hasAssignedCoach() {
        return this.coachEntity != null;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", roles=" + roles +
                ", athleteEntity=" + athleteEntity +
                ", coachEntity=" + coachEntity +
                '}';
    }
}
