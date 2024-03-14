package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingPlanStatus;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "training_plan")
public class TrainingPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;
    @Enumerated(EnumType.STRING)
    private TrainingPlanStatus trainingPlanStatus;

    @NotEmpty
    private String description;

    private Date plannedDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "training_plan_id")
    @OrderBy("sequence ASC")
    private List<StageEntity> stages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public TrainingPlanStatus getTrainingPlanStatus() {
        return trainingPlanStatus;
    }

    public void setTrainingPlanStatus(TrainingPlanStatus trainingPlanStatus) {
        this.trainingPlanStatus = trainingPlanStatus;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public List<StageEntity> getStages() {
        return stages;
    }

    public void setStages(List<StageEntity> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        return "TrainingPlanEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trainingType=" + trainingType +
                ", trainingPlanStatus=" + trainingPlanStatus +
                ", description='" + description + '\'' +
                ", plannedDate=" + plannedDate +
                ", stage=" + stages +
                '}';
    }
}
