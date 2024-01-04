package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingUnit.TrainingEntity;

import java.util.List;

@Entity
@Table(name = "athlete")
public class AthleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private List<TrainingEntity> trainingUnit;

    @OneToMany
    private List<TrainingRealizationEntity> trainingRealization;

}
