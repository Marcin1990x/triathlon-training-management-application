package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.coach.Coach;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.WrongDateException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingPlanStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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


    public boolean checkIfIsNotNull(Long id) {
        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);
        if (trainingPlanEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public List<TrainingPlan> getTrainingPlansByAthleteId(Long id) {

        if (athleteService.checkIfIsNotNull(id)) {

            return athleteRepository.findById(id).get().getTrainingPlans().stream().map(TrainingPlan::fromTrainingPlanEntity).collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
    }

    public void deleteById(Long id) {

        if (checkIfIsNotNull(id)) {
            trainingPlanRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }

    public TrainingPlan addNewTrainingPlanToCoach(Long coachId,@Valid TrainingPlan trainingPlan) {

        Coach coach;

        if (coachService.checkIfIsNotNull(coachId)) {
            Optional<CoachEntity> coachEntity = coachRepository.findById(coachId);
            coach = Coach.fromCoachEntity(coachEntity.get());
            coach.getTrainingPlans().add(trainingPlan);

            coachRepository.save(coach.mapToCoachEntity());
            return trainingPlan;
        } else {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
    }

    public TrainingPlan addTrainingPlanToAthleteWithDate(Long athleteId, Long trainingPlanId, Date date) {

        if (athleteService.checkIfIsNotNull(athleteId) && checkIfIsNotNull(trainingPlanId)) {
            if (isDateCorrect(date)) {

                TrainingPlanEntity trainingPlan = trainingPlanRepository.findById(trainingPlanId).get();
                TrainingPlanEntity copiedPlan = copyTrainingPlanEntity(trainingPlan);
                copiedPlan.setPlannedDate(date);
                copiedPlan.setTrainingPlanStatus(TrainingPlanStatus.PLANNED);

                trainingPlanRepository.save(copiedPlan);

                AthleteEntity athlete = athleteRepository.findById(athleteId).get();
                athlete.getTrainingPlans().add(copiedPlan);
                athleteRepository.save(athlete);

                return TrainingPlan.fromTrainingPlanEntity(copiedPlan);
            } else {
                throw new WrongDateException(date.toString());
            }
        } else {
            if (!checkIfIsNotNull(trainingPlanId)) {
                throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(trainingPlanId));
            } else {
                throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
            }
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

        return copy;
    }
}
