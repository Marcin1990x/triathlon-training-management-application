package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingUnit.TrainingEntity;

@Entity
@Table(name = "training_realization")
public class TrainingRealizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private TrainingEntity trainingUnit;
}
