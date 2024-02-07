package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationExternal;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.Feelings;

import java.sql.Date;

@Entity
@Table(name = "training_realization_external")
public class TrainingRealizationExternal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String realizationDescription;

    private Date realizationDate;

    @Enumerated(EnumType.STRING)
    private Feelings feelings;

    private int rpeLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealizationDescription() {
        return realizationDescription;
    }

    public void setRealizationDescription(String realizationDescription) {
        this.realizationDescription = realizationDescription;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public Feelings getFeelings() {
        return feelings;
    }

    public void setFeelings(Feelings feelings) {
        this.feelings = feelings;
    }

    public int getRpeLevel() {
        return rpeLevel;
    }

    public void setRpeLevel(int rpeLevel) {
        this.rpeLevel = rpeLevel;
    }
}
