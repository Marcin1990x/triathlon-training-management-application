package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class RunStage extends Stage {

    private int paceInSecondsPerKm;

    public RunStageEntity mapToRunStageEntity() {

        RunStageEntity runStageEntity = new RunStageEntity();

        runStageEntity.setId(this.getId());
        runStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        runStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        runStageEntity.setHeartRate(this.getHeartRate());
        runStageEntity.setDescription(this.getDescription());
        runStageEntity.setPaceInSecondsPerKm(this.getPaceInSecondsPerKm());

        return runStageEntity;
    }

    public static RunStage fromRunStageEntity(RunStageEntity runStageEntity) {

        RunStage runStage = new RunStage();

        runStage.setId(runStageEntity.getId());
        runStage.setDistanceInMeters(runStageEntity.getDistanceInMeters());
        runStage.setTimeInSeconds(runStageEntity.getTimeInSeconds());
        runStage.setHeartRate(runStageEntity.getHeartRate());
        runStage.setDescription(runStageEntity.getDescription());
        runStage.setPaceInSecondsPerKm(runStageEntity.getPaceInSecondsPerKm());

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
