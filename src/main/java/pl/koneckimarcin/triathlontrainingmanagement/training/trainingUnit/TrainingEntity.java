package pl.koneckimarcin.triathlontrainingmanagement.training.trainingUnit;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

import java.util.Date;

@Entity
@Table(name = "training_unit")
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date plannedDate;

    @ManyToOne
    private AthleteEntity plannedAthlete;

    @OneToOne
    private TrainingPlanEntity trainingPlan;

    @OneToOne
    private TrainingRealizationEntity trainingRealization;
}
