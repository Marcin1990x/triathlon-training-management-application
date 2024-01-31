package pl.koneckimarcin.triathlontrainingmanagement.coach;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;

import java.util.Set;

@Entity
@Table(name = "coach")
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "coach_id")
    private Set<AthleteEntity> athletes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "coach_id")
    private Set<TrainingPlanEntity> trainingPlanEntities;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<AthleteEntity> getAthletes() {
        return athletes;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAthletes(Set<AthleteEntity> athletes) {
        this.athletes = athletes;
    }

    public Set<TrainingPlanEntity> getTrainingPlans() {
        return trainingPlanEntities;
    }

    public void setTrainingPlans(Set<TrainingPlanEntity> trainingPlanEntities) {
        this.trainingPlanEntities = trainingPlanEntities;
    }
}
