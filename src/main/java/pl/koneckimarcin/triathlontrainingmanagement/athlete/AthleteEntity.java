package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingUnit.TrainingDayEntity;

import java.util.List;

@Entity
@Table(name = "athlete")
public class AthleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private List<TrainingDayEntity> trainingUnit;

    @OneToMany
    private List<TrainingRealizationEntity> trainingRealization;

    public Athlete mapToAthlete() {

        Athlete athlete = new Athlete();

        athlete.setFirstName(this.firstName);
        athlete.setLastName(this.lastName);
        athlete.setTrainingUnit(this.trainingUnit);
        athlete.setTrainingRealization(this.trainingRealization);

        return athlete;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTrainingUnit(List<TrainingDayEntity> trainingUnit) {
        this.trainingUnit = trainingUnit;
    }

    public void setTrainingRealization(List<TrainingRealizationEntity> trainingRealization) {
        this.trainingRealization = trainingRealization;
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

    public List<TrainingDayEntity> getTrainingUnit() {
        return trainingUnit;
    }

    public List<TrainingRealizationEntity> getTrainingRealization() {
        return trainingRealization;
    }
}