package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingUnit.TrainingEntity;

import java.util.List;

@Entity
@Table(name = "training_plan")
public class TrainingPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private TrainingType trainingType;

    @ManyToOne
    private TrainingEntity trainingUnit;

    @OneToMany
    private List<StageEntity> stage;
}
