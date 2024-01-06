package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.persistence.*;

@Entity
@Table(name = "stage")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "stage_type", discriminatorType = DiscriminatorType.STRING)
public abstract class StageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long distanceInMeters;

    private long timeInSeconds;

    private int heartRate;

    private String description;
}
