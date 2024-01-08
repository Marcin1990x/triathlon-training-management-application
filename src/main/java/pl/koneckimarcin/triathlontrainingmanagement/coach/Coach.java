package pl.koneckimarcin.triathlontrainingmanagement.coach;

import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;

import java.util.List;

public class Coach {

    private String firstName;

    private String lastName;

    private List<AthleteEntity> athletes;

    public Coach() {
    }

    public Coach(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CoachEntity mapToCoachEntity() {

        CoachEntity coachEntity = new CoachEntity();
        coachEntity.setFirstName(this.firstName);
        coachEntity.setLastName(this.lastName);
        coachEntity.setAthletes(this.athletes);

        return coachEntity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AthleteEntity> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<AthleteEntity> athletes) {
        this.athletes = athletes;
    }
}
