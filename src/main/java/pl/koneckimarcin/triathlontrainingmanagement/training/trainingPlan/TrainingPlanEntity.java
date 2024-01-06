package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

import java.util.List;

@Entity
@Table(name = "training_plan")
public class TrainingPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private TrainingType trainingType;

    private String description;

    @OneToMany
    private List<StageEntity> stage;
}
