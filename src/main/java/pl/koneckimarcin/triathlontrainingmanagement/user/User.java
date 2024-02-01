package pl.koneckimarcin.triathlontrainingmanagement.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // todo: change for test
    private String password;

    private String emailAddress;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long athleteId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long coachId;

    public User() {
    }

    public User(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public UserEntity mapToUserEntity() {

        UserEntity userEntity = new UserEntity();

        userEntity.setId(this.id);
        userEntity.setUsername(this.username);
        userEntity.setPassword(this.password);
        userEntity.setEmailAddress(this.emailAddress);
        return userEntity;
    }

    public static User fromUserEntity(UserEntity userEntity) {

        User user = new User();

        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmailAddress(userEntity.getEmailAddress());
        if(userEntity.getAthleteEntity() != null) {
            user.setAthleteId(userEntity.getAthleteEntity().getId());
        }
        if(userEntity.getCoachEntity() != null) {
            user.setCoachId(userEntity.getCoachEntity().getId());
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

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }
}
