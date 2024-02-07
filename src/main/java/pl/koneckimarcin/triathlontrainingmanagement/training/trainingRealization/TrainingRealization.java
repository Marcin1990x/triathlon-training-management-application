package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import java.sql.Date;

public class TrainingRealization {

    private Long id;

    private String realizationDescription;

    private Date realizationDate;

    private Feelings fellings;

    private int rpeLevel;

    public TrainingRealization() {
    }

    public TrainingRealization(String realizationDescription, Date realizationDate, Feelings fellings, int rpeLevel) {
        this.realizationDescription = realizationDescription;
        this.realizationDate = realizationDate;
        this.fellings = fellings;
        this.rpeLevel = rpeLevel;
    }

    public TrainingRealizationEntity mapToTrainingRealizationEntity() {

        TrainingRealizationEntity trainingRealizationEntity = new TrainingRealizationEntity();

        trainingRealizationEntity.setId(this.id);
        trainingRealizationEntity.setRealizationDescription(this.realizationDescription);
        trainingRealizationEntity.setRealizationDate(this.realizationDate);
        trainingRealizationEntity.setFeelings(this.fellings);
        trainingRealizationEntity.setRpeLevel(this.rpeLevel);

        return trainingRealizationEntity;
    }

    public static TrainingRealization fromTrainingRealizationEntity(TrainingRealizationEntity trainingRealizationEntity) {

        TrainingRealization trainingRealization = new TrainingRealization();

        trainingRealization.setId(trainingRealizationEntity.getId());
        trainingRealization.setRealizationDescription(trainingRealizationEntity.getRealizationDescription());
        trainingRealization.setRealizationDate(trainingRealizationEntity.getRealizationDate());
        trainingRealization.setFellings(trainingRealizationEntity.getFeelings());
        trainingRealization.setRpeLevel(trainingRealizationEntity.getRpeLevel());

        return trainingRealization;
    }

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

    public Feelings getFellings() {
        return fellings;
    }

    public void setFellings(Feelings fellings) {
        this.fellings = fellings;
    }

    public int getRpeLevel() {
        return rpeLevel;
    }

    public void setRpeLevel(int rpeLevel) {
        this.rpeLevel = rpeLevel;
    }
}
