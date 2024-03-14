package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.repository.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.service.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.WrongDateException;
import pl.koneckimarcin.triathlontrainingmanagement.security.authentication.AuthenticatedUserService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingPlanStatus;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run.RunStageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight.WeightStageEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainingPlanService {

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachService coachService;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    public boolean checkIfIsNotNull(Long id) {

        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);
        if (trainingPlanEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public List<TrainingPlan> getTrainingPlansByAthleteId(Long id) {

        checkAthleteIdException(id);
        return athleteRepository.findById(id).get().getTrainingPlans()
                .stream().map(TrainingPlan::fromTrainingPlanEntity).collect(Collectors.toList());
    }

    public Set<TrainingPlan> getTrainingPlansByCoachId(Long id) {

        checkCoachIdException(id);
        return coachRepository.findById(id).get().getTrainingPlans()
                .stream().map(TrainingPlan::fromTrainingPlanEntity).collect(Collectors.toSet());
    }

    public void deleteById(Long id) {

        checkTrainingPlanIdException(id);

        trainingPlanRepository.deleteById(id);
    }

    public TrainingPlan addNewTrainingPlanToCoach(Long coachId, @Valid TrainingPlan trainingPlan) {

        checkCoachIdException(coachId);

        Optional<CoachEntity> coachEntity = coachRepository.findById(coachId);

        trainingPlan.setTrainingPlanStatus(TrainingPlanStatus.TEMPLATE);

        TrainingPlanEntity savedPlan = trainingPlanRepository.save(trainingPlan.mapToTrainingPlanEntity());

        coachEntity.get().getTrainingPlans().add(savedPlan);
        coachRepository.save(coachEntity.get());

        return TrainingPlan.fromTrainingPlanEntity(savedPlan);
    }

    public TrainingPlan addTrainingPlanToAthleteWithDate(Long athleteId, Long trainingPlanId, Date date) {

        checkAthleteIdException(athleteId);
        checkTrainingPlanIdException(trainingPlanId);
        checkIfDateIsCorrectException(date);

        TrainingPlanEntity trainingPlan = trainingPlanRepository.findById(trainingPlanId).get();
        TrainingPlanEntity copiedPlan = copyTrainingPlanEntity(trainingPlan);
        copiedPlan.setPlannedDate(date);
        copiedPlan.setTrainingPlanStatus(TrainingPlanStatus.PLANNED);

        trainingPlanRepository.save(copiedPlan);

        AthleteEntity athlete = athleteRepository.findById(athleteId).get();
        athlete.getTrainingPlans().add(copiedPlan);
        athleteRepository.save(athlete);

        return TrainingPlan.fromTrainingPlanEntity(copiedPlan);
    }

    private void checkAthleteIdException(Long athleteId) {
        if (!athleteService.checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }
    }

    private void checkCoachIdException(Long coachId) {
        if (!coachService.checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
    }

    private void checkTrainingPlanIdException(Long trainingPlanId) {

        if (!checkIfIsNotNull(trainingPlanId)) {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(trainingPlanId));
        }
    }

    private void checkIfDateIsCorrectException(Date date) {

        if (!isDateCorrect(date)) {
            throw new WrongDateException(date.toString());
        }
    }

    private boolean isDateCorrect(Date date) {

        Date now = Date.valueOf(LocalDate.now());

        return !date.before(now);
    }

    private TrainingPlanEntity copyTrainingPlanEntity(TrainingPlanEntity original) {

        TrainingPlanEntity copy = new TrainingPlanEntity();
        copy.setTrainingType(original.getTrainingType());
        copy.setDescription(original.getDescription());
        copy.setName(original.getName());

        if (original.getStages().size() > 0) {
            handleStagesForCopiedTrainingPlan(original, copy);
        }
        return copy;
    }

    private void handleStagesForCopiedTrainingPlan(TrainingPlanEntity original, TrainingPlanEntity copy) {

        StageEntity stage = original.getStages().get(0);
        List<StageEntity> originalStages = original.getStages();

        List<StageEntity> stagesForCopy = new ArrayList<>();

        if (stage instanceof BikeStageEntity)
            handleBikeStageEntity(originalStages, stagesForCopy);
        if (stage instanceof SwimStageEntity)
            handleSwimStageEntity(originalStages, stagesForCopy);
        if (stage instanceof RunStageEntity)
            handleRunStageEntity(originalStages, stagesForCopy);
        if (stage instanceof WeightStageEntity)
            handleWeightStageEntity(originalStages, stagesForCopy);

        copy.setStages(stagesForCopy);
    }

    private void handleWeightStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(WeightStageEntity.copyStage((WeightStageEntity) originalStage));
    }

    private void handleRunStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(RunStageEntity.copyStage((RunStageEntity) originalStage));
    }

    private void handleSwimStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(SwimStageEntity.copyStage((SwimStageEntity) originalStage));
    }

    private void handleBikeStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(BikeStageEntity.copyStage((BikeStageEntity) originalStage));
    }

    public void removeTrainingPlanFromAthlete(Long athleteId, Long trainingPlanId) {

        checkAthleteIdException(athleteId);
        checkTrainingPlanIdException(trainingPlanId);

        trainingPlanRepository.deleteById(trainingPlanId);
    }

    public TrainingPlan getTrainingPlanById(Long id) {

        checkTrainingPlanIdException(id);

        return TrainingPlan.fromTrainingPlanEntity(trainingPlanRepository.findById(id).get());
    }
}
