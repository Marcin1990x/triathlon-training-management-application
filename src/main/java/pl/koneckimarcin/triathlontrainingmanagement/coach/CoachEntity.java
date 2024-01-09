package pl.koneckimarcin.triathlontrainingmanagement.coach;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "coach")
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private List<AthleteEntity> athletes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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

    public Set<TrainingPlanEntity> getTrainingPlans() {
        return trainingPlanEntities;
    }
    public void setTrainingPlans(Set<TrainingPlanEntity> trainingPlanEntities) {
        this.trainingPlanEntities = trainingPlanEntities;
    }
}
