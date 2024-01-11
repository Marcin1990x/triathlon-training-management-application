package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

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

import java.sql.Date;
import java.util.Optional;

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

    public void deleteById(Long id) {

        if (checkIfIsNotNull(id)) {
            trainingPlanRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }

    public TrainingPlan addNewTrainingPlanToCoach(Long coachId, TrainingPlan trainingPlan) {

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

    public void addTrainingPlanToAthlete(Long athleteId, Long trainingPlanId, Date date) { //todo: to finish

//        if (athleteService.checkIfIsNotNull(athleteId) && checkIfIsNotNull(trainingPlanId)) {
//            if (isDateCorrect(date)) {
//
//                TrainingPlanEntity trainingPlan = trainingPlanRepository.findById(trainingPlanId).get();
//
//                TrainingPlanEntity trainingPlanAssigned = new TrainingPlanEntity();
//                trainingPlanAssigned.setId(0L);
//                trainingPlanAssigned.setTrainingType(trainingPlan.getTrainingType());
//                trainingPlanAssigned.setPlannedDate(date);
//                trainingPlanAssigned.setStage(trainingPlan.getStage());
//                trainingPlanAssigned.setTrainingType(trainingPlan.getTrainingType());
//                trainingPlanAssigned.setName(trainingPlan.getName());
//                trainingPlanAssigned.setDescription(trainingPlan.getDescription());
//
//                trainingPlanRepository.save(trainingPlanAssigned);
//
//                AthleteEntity athlete = athleteRepository.findById(athleteId).get();
//                athlete.getTrainingPlans().add(trainingPlanAssigned);
//
//                athleteRepository.save(athlete);
//            } else {
//                throw new EnumConstantNotPresentException(TrainingType.class, "sds waaaaaat!?");
//            }
//        } else {
//            if (!checkIfIsNotNull(trainingPlanId)) {
//                throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(trainingPlanId));
//            } else {
//                throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
//            }
//        }
    }

    private boolean isDateCorrect(Date date) {

        return !date.toString().isEmpty();
    }
}
