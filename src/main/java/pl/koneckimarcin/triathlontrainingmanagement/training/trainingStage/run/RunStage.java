package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class RunStage extends Stage {

    public RunStage() {
    }

    public RunStage(int paceInSecondsPerKm) {
        this.paceInSecondsPerKm = paceInSecondsPerKm;
    }

    public RunStage(long distanceInMeters, long timeInSeconds, int sequence, int heartRate,
                    String description, int paceInSecondsPerKm, int repeat) {
        super(distanceInMeters, timeInSeconds, sequence, heartRate, description, repeat);
        this.paceInSecondsPerKm = paceInSecondsPerKm;
    }

    private int paceInSecondsPerKm;

    public RunStageEntity mapToRunStageEntity() {

        RunStageEntity runStageEntity = new RunStageEntity();

        runStageEntity.setId(this.getId());
        runStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        runStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        runStageEntity.setSequence(this.getSequence());
        runStageEntity.setHeartRate(this.getHeartRate());
        runStageEntity.setDescription(this.getDescription());
        runStageEntity.setPaceInSecondsPerKm(this.getPaceInSecondsPerKm());
        runStageEntity.setRepeat(this.getRepeat());

        return runStageEntity;
    }

    public static RunStage fromRunStageEntity(RunStageEntity runStageEntity) {

        RunStage runStage = new RunStage();

        runStage.setId(runStageEntity.getId());
        runStage.setDistanceInMeters(runStageEntity.getDistanceInMeters());
        runStage.setTimeInSeconds(runStageEntity.getTimeInSeconds());
        runStage.setSequence(runStageEntity.getSequence());
        runStage.setHeartRate(runStageEntity.getHeartRate());
        runStage.setDescription(runStageEntity.getDescription());
        runStage.setPaceInSecondsPerKm(runStageEntity.getPaceInSecondsPerKm());
        runStage.setRepeat(runStageEntity.getRepeat());

        return runStage;
    }

    public int getPaceInSecondsPerKm() {
        return paceInSecondsPerKm;
    }

    public void setPaceInSecondsPerKm(int paceInSecondsPerKm) {
        this.paceInSecondsPerKm = paceInSecondsPerKm;
    }

    @Override
    public String toString() {
        return super.toString() + "RunStage{" +
                "paceInSecondsPerKm=" + paceInSecondsPerKm +
                '}';
    }
}
