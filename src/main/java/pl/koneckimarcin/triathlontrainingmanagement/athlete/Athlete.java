package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingDay.TrainingDayEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

import java.util.List;

public class Athlete {

    private Long id;

    private String firstName;

    private String lastName;

    private List<TrainingDayEntity> trainingDay;

    private List<TrainingRealizationEntity> trainingRealization;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AthleteEntity mapToAthleteEntity() {

        AthleteEntity athleteEntity = new AthleteEntity();

        athleteEntity.setId(this.id);
        athleteEntity.setFirstName(this.firstName);
        athleteEntity.setLastName(this.lastName);
        athleteEntity.setTrainingDay(this.trainingDay);
        athleteEntity.setTrainingRealization(this.trainingRealization);

        return athleteEntity;
    }

    public static Athlete fromAthleteEntity(AthleteEntity athleteEntity) {

        Athlete athlete = new Athlete();

        athlete.setId(athleteEntity.getId());
        athlete.setFirstName(athleteEntity.getFirstName());
        athlete.setLastName(athleteEntity.getLastName());
        athlete.setTrainingDay(athleteEntity.getTrainingDay());
        athlete.setTrainingRealization(athleteEntity.getTrainingRealization());

        return athlete;
    }

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
