package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import jakarta.persistence.*;

@Entity
@Table(name = "training_realization")
public class TrainingRealizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // real realization fields
}
