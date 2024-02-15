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
    private List<TrainingRealizationEntity> trainingRealizations;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "athlete_id")
    private List<TrainingPlanEntity> trainingPlans;

    @Column(name = "has_user")
    private boolean isAssignedToUser = false;

    private Long stravaId;

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

    public List<TrainingRealizationEntity> getTrainingRealizations() {
        return trainingRealizations;
    }

    public void setTrainingRealizations(List<TrainingRealizationEntity> trainingRealizations) {
        this.trainingRealizations = trainingRealizations;
    }

    public List<TrainingPlanEntity> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainingPlanEntity> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }

    public boolean isAssignedToUser() {
        return isAssignedToUser;
    }

    public void setAssignedToUser(boolean assignedToUser) {
        isAssignedToUser = assignedToUser;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }
}
