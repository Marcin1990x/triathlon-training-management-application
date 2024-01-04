package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;

@Entity
@Table (name = "stage")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "stage_type", discriminatorType = DiscriminatorType.STRING)
public abstract class StageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float distance;

    private int heartRate;

    private String description;

    @ManyToOne
    private TrainingPlanEntity trainingPlan;
}
