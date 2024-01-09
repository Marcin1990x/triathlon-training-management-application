package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingDay.TrainingDayEntity;

import java.util.List;

@Entity
@Table(name = "athlete")
public class AthleteEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private List<TrainingDayEntity> trainingDay;

    @OneToMany
    private List<TrainingRealizationEntity> trainingRealization;

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

    public List<TrainingDayEntity> getTrainingDay() {
        return trainingDay;
    }

    public void setTrainingDay(List<TrainingDayEntity> trainingDay) {
        this.trainingDay = trainingDay;
    }

    public List<TrainingRealizationEntity> getTrainingRealization() {
        return trainingRealization;
    }

    public void setTrainingRealization(List<TrainingRealizationEntity> trainingRealization) {
        this.trainingRealization = trainingRealization;
    }
}
