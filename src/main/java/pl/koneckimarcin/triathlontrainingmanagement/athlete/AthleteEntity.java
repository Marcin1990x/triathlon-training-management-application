package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

import java.util.List;

@Entity
@Table(name = "athlete")
public class AthleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "athlete_id")
    private List<TrainingRealizationEntity> trainingRealization;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "athlete_id")
    private List<TrainingPlanEntity> trainingPlans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TrainingRealizationEntity> getTrainingRealization() {
        return trainingRealization;
    }

    public void setTrainingRealization(List<TrainingRealizationEntity> trainingRealization) {
        this.trainingRealization = trainingRealization;
    }

    public List<TrainingPlanEntity> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainingPlanEntity> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }
}
