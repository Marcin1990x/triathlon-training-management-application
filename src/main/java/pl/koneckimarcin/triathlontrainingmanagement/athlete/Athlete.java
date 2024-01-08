package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingUnit.TrainingDayEntity;

import java.util.List;

public class Athlete {

    private String firstName;

    private String lastName;

    private List<TrainingDayEntity> trainingUnit;

    private List<TrainingRealizationEntity> trainingRealization;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AthleteEntity mapToAthleteEntity() {

        AthleteEntity athleteEntity = new AthleteEntity();

        athleteEntity.setFirstName(this.firstName);
        athleteEntity.setLastName(this.lastName);
        athleteEntity.setTrainingDay(this.trainingUnit);
        athleteEntity.setTrainingRealization(this.trainingRealization);

        return athleteEntity;
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
