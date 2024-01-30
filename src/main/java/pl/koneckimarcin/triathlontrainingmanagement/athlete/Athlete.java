package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealization;

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
    private List<TrainingRealization> trainingRealization;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TrainingPlan> trainingPlans = new ArrayList<>();

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

        return athleteEntity;
    }

    public static Athlete fromAthleteEntity(AthleteEntity athleteEntity) {

        Athlete athlete = new Athlete();

        athlete.setId(athleteEntity.getId());
        athlete.setFirstName(athleteEntity.getFirstName());
        athlete.setLastName(athleteEntity.getLastName());
        if (athleteEntity.getTrainingRealization() != null) {
            athlete.setTrainingRealization(athleteEntity.getTrainingRealization()
                    .stream().map(TrainingRealization::fromTrainingRealizationEntity).collect(Collectors.toList()));
        }
        if (athleteEntity.getTrainingPlans() != null) {
            athlete.setTrainingPlans(athleteEntity.getTrainingPlans()
                    .stream().map(TrainingPlan::fromTrainingPlanEntity).collect(Collectors.toList()));
        }
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

    public List<TrainingRealization> getTrainingRealization() {
        return trainingRealization;
    }

    public void setTrainingRealization(List<TrainingRealization> trainingRealization) {
        this.trainingRealization = trainingRealization;
    }

    public List<TrainingPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }
}
