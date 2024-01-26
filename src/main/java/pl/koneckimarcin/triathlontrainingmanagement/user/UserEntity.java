package pl.koneckimarcin.triathlontrainingmanagement.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;

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
    @Size(min =  8, max = 30)
    private String password;

    @Email
    @NotEmpty
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "athlete_id")
    private AthleteEntity athleteEntity;

    @OneToOne
    @JoinColumn(name = "coach_id")
    private CoachEntity coachEntity;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
