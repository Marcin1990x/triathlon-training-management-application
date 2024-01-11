package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Athlete {

    private Long id;

    private String firstName;

    private String lastName;

    private List<TrainingRealizationEntity> trainingRealization;

    private List<TrainingPlan> trainingPlans;

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
        athleteEntity.setTrainingRealization(this.trainingRealization);
        athleteEntity.setTrainingPlans(this.trainingPlans
                .stream().map(TrainingPlan::mapToTrainingPlanEntity).collect(Collectors.toList()));

        return athleteEntity;
    }

    public static Athlete fromAthleteEntity(AthleteEntity athleteEntity) {

        Athlete athlete = new Athlete();

        athlete.setId(athleteEntity.getId());
        athlete.setFirstName(athleteEntity.getFirstName());
        athlete.setLastName(athleteEntity.getLastName());
        athlete.setTrainingRealization(athleteEntity.getTrainingRealization());
        athlete.setTrainingPlans(athleteEntity.getTrainingPlans()
                .stream().map(TrainingPlan::fromTrainingPlanEntity).collect(Collectors.toList()));

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

    public List<TrainingRealizationEntity> getTrainingRealization() {
        return trainingRealization;
    }

    public void setTrainingRealization(List<TrainingRealizationEntity> trainingRealization) {
        this.trainingRealization = trainingRealization;
    }

    public List<TrainingPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }
}
