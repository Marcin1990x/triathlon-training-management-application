package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava.TrainingRealizationStrava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Athlete {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TrainingRealizationStrava> trainingRealizations;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TrainingPlan> trainingPlans = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> trainings;

    private Long stravaId;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Athlete(String firstName, String lastName, Long stravaId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stravaId = stravaId;
    }

    public AthleteEntity mapToAthleteEntity() {

        AthleteEntity athleteEntity = new AthleteEntity();

        athleteEntity.setId(this.id);
        athleteEntity.setFirstName(this.firstName);
        athleteEntity.setLastName(this.lastName);
        athleteEntity.setStravaId(this.stravaId);

        return athleteEntity;
    }

    public static Athlete fromAthleteEntity(AthleteEntity athleteEntity) {

        Athlete athlete = new Athlete();

        athlete.setId(athleteEntity.getId());
        athlete.setFirstName(athleteEntity.getFirstName());
        athlete.setLastName(athleteEntity.getLastName());
        if (athleteEntity.getTrainingRealizations() != null) {
            athlete.setTrainingRealizations(athleteEntity.getTrainingRealizations()
                    .stream().map(TrainingRealizationStrava::fromTrainingRealizationStravaEntity).collect(Collectors.toList()));
        }
        if (athleteEntity.getTrainingPlans() != null) {
            athlete.setTrainings(setTrainingPlansInformation(athleteEntity));
        }
        if (athleteEntity.getTrainingPlans() != null) {
            athlete.setTrainingPlans(athleteEntity.getTrainingPlans()
                    .stream().map(TrainingPlan::fromTrainingPlanEntity).collect(Collectors.toList()));
        }
        return athlete;
    }

    private static List<String> setTrainingPlansInformation(AthleteEntity athlete) {

        List<String> trainingPlansInformation = new ArrayList<>();

        List<TrainingPlanEntity> trainingPlans = athlete.getTrainingPlans();
        for (TrainingPlanEntity plan : trainingPlans) {
            trainingPlansInformation.add(plan.getPlannedDate() + ": " + plan.getTrainingType() + " - " + plan.getName());
        }
        return trainingPlansInformation;
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

    public List<TrainingRealizationStrava> getTrainingRealizations() {
        return trainingRealizations;
    }

    public void setTrainingRealizations(List<TrainingRealizationStrava> trainingRealizations) {
        this.trainingRealizations = trainingRealizations;
    }

    public List<TrainingPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }

    public List<String> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<String> trainings) {
        this.trainings = trainings;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }
}
