package pl.koneckimarcin.triathlontrainingmanagement.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.coach.Coach;

public class User {

    private Long id;

    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Athlete athlete;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Coach coach;

    public UserEntity mapToUserEntity() {

        UserEntity userEntity = new UserEntity();

        userEntity.setId(this.id);
        userEntity.setUsername(this.username);
        userEntity.setAthleteEntity(this.athlete.mapToAthleteEntity());
        userEntity.setCoachEntity(this.coach.mapToCoachEntity());

        return userEntity;
    }

    public static User fromUserEntity(UserEntity userEntity) {

        User user = new User();

        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        if(userEntity.getAthleteEntity() != null) {
            user.setAthlete(Athlete.fromAthleteEntity(userEntity.getAthleteEntity()));
        }
        if(userEntity.getCoachEntity() != null) {
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
