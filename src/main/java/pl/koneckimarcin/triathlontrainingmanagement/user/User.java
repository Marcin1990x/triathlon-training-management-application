package pl.koneckimarcin.triathlontrainingmanagement.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.coach.Coach;

import java.util.Set;

public class User {

    private Long id;

    private String username;

    private String password;

    private String emailAddress;

    private Set<RoleEntity> roles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Athlete athlete;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Coach coach;

    public UserEntity mapToUserEntity() {

        UserEntity userEntity = new UserEntity();

        userEntity.setId(this.id);
        userEntity.setUsername(this.username);
        userEntity.setPassword(this.password);
        userEntity.setEmailAddress(this.emailAddress);
        userEntity.setRoles(this.roles);
        if (this.athlete != null) {
            userEntity.setAthleteEntity(this.athlete.mapToAthleteEntity());
        }
        if (this.coach != null) {
            userEntity.setCoachEntity(this.coach.mapToCoachEntity());
        }
        return userEntity;
    }

    public static User fromUserEntity(UserEntity userEntity) {

        User user = new User();

        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmailAddress(userEntity.getEmailAddress());
        user.setRoles(userEntity.getRoles());
        if (userEntity.getAthleteEntity() != null) {
            user.setAthlete(Athlete.fromAthleteEntity(userEntity.getAthleteEntity()));
        }
        if (userEntity.getCoachEntity() != null) {
            user.setCoach(Coach.fromCoachEntity(userEntity.getCoachEntity()));
        }
        return user;
    }

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

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
