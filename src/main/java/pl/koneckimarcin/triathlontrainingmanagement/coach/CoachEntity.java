package pl.koneckimarcin.triathlontrainingmanagement.coach;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;

import java.util.List;

@Entity
@Table(name = "coach")
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private List<AthleteEntity> athletes;

    public Coach mapToCoach() {

        Coach coach = new Coach();

        coach.setFirstName(this.firstName);
        coach.setLastName(this.lastName);
        coach.setAthletes(this.athletes);

        return coach;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<AthleteEntity> getAthletes() {
        return athletes;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAthletes(List<AthleteEntity> athletes) {
        this.athletes = athletes;
    }
}
