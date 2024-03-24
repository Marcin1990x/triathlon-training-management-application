package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.Coach;
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.CoachResponseDto;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;

@RestController
public class CoachController implements CoachOperations {

    @Autowired
    private CoachService coachService;

    @Autowired
    private TrainingPlanService trainingPlanService;

    public CoachResponseDto getById(Long id) {
        return coachService.findById(id);
    }

    public Coach addNew(Coach coach) {

        return coachService.addNew(coach);
    }

    public void deleteById(Long id) {

        coachService.deleteById(id);
    }

    public Coach addAthleteToCoach(Long coachId, Long athleteId) {

        return coachService.addAthleteToCoach(coachId, athleteId);
    }

    @Override
    public Coach removeAthleteFromCoach(Long coachId, Long athleteId) {

        return coachService.removeAthleteFromCoach(coachId, athleteId);
    }
}
