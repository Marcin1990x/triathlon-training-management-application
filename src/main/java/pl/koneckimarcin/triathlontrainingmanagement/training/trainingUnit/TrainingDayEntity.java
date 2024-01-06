package pl.koneckimarcin.triathlontrainingmanagement.training.trainingUnit;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "training_day")
public class TrainingDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date plannedDate;

    @OneToMany
    private Set<TrainingPlanEntity> trainingPlan;

    @OneToMany
    private Set<TrainingRealizationEntity> trainingRealization;
}
