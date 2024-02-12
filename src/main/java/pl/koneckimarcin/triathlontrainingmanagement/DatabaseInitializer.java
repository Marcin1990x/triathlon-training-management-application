package pl.koneckimarcin.triathlontrainingmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.coach.Coach;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachService;
import pl.koneckimarcin.triathlontrainingmanagement.security.registration.RegistrationService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.Feelings;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealization;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;
import pl.koneckimarcin.triathlontrainingmanagement.user.User;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserService;
import pl.koneckimarcin.triathlontrainingmanagement.user.role.Role;
import pl.koneckimarcin.triathlontrainingmanagement.user.role.RoleService;

import java.sql.Date;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private AthleteService athleteService;
    @Autowired
    private UserService userService;
    @Autowired
    private TrainingPlanService tpService;
    @Autowired
    private StageService stageService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TrainingRealizationService trService;


    @Override
    public void run(String... args) throws Exception {

        //add users - for coach and for two athletes
        registrationService.registerUser(new User("Coach", "coach", "coach@app.com"));
        registrationService.registerUser(new User("Triathlete", "triathlete", "triathlete@app.com"));
        registrationService.registerUser(new User("Runner", "runner", "runner@app.com"));
        //add coach, two athletes and assign to users
        coachService.addNew(new Coach("Bob", "Coach"));
        userService.addCoachToUser(1L, 1L);
        athleteService.addNew(new Athlete("John", "Triathlete", 14748685L));
        userService.addAthleteToUser(2L, 1L);
        athleteService.addNew(new Athlete("Adam", "Runner"));
        userService.addAthleteToUser(3L, 2L);
        //add roles to users
        roleService.addRoleToUserById(1L, Role.COACH);
        roleService.addRoleToUserById(1L, Role.ATHLETE);
        roleService.addRoleToUserById(2L, Role.ATHLETE);
        roleService.addRoleToUserById(3L, Role.ATHLETE); // todo: many to
        //add athletes to coach
        coachService.addAthleteToCoach(1L, 1L);
        coachService.addAthleteToCoach(1L, 2L);
        //add training plans with stages to coach
        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Easy swimming", TrainingType.SWIM, "1500m easy"));
        stageService.addNewSwimStageToTrainingPlan(1L,
                new SwimStage(1500, 1800, 1, 0, "easy", 120));

        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Swimming intervals", TrainingType.SWIM, "100m hard/100m easy x10"));
        addStagesForIntervals();

        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Easy long run", TrainingType.RUN, "15k easy"));
        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Intervals", TrainingType.RUN, "200m/45s hard x8"));
        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Long cycling", TrainingType.BIKE, "60k easy"));
        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Intervals", TrainingType.BIKE, "1k hard/60s easy x15"));
        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Weight exercises plan", TrainingType.WEIGHT, "as in the plan"));
        //plan training for athletes
        tpService.addTrainingPlanToAthleteWithDate(1L, 1L, new Date(124, 3, 3));
        tpService.addTrainingPlanToAthleteWithDate(1L, 3L, new Date(124, 3, 4));
        tpService.addTrainingPlanToAthleteWithDate(1L, 7L, new Date(124, 3, 5));
        tpService.addTrainingPlanToAthleteWithDate(1L, 2L, new Date(124, 3, 6));
        tpService.addTrainingPlanToAthleteWithDate(1L, 4L, new Date(124, 3, 7));
        tpService.addTrainingPlanToAthleteWithDate(2L, 4L, new Date(124, 3, 3));
        tpService.addTrainingPlanToAthleteWithDate(2L, 3L, new Date(124, 3, 4));
        tpService.addTrainingPlanToAthleteWithDate(2L, 7L, new Date(124, 3, 5));
        tpService.addTrainingPlanToAthleteWithDate(2L, 5L, new Date(124, 3, 6));
        //add training realization for athlete
        trService.addNewTrainingRealizationToAthlete(1L,
                new TrainingRealization("Done.", new Date(124, 3, 6), Feelings.NORMAL, 3));

    }

    private void addStagesForIntervals() {

        for (int i = 1; i < 21; i++) {
            if (i % 2 != 0) {
                stageService.addNewSwimStageToTrainingPlan(2L,
                        new SwimStage(100, 105, i, 0, "hard", 105));
            } else {
                stageService.addNewSwimStageToTrainingPlan(2L,
                        new SwimStage(100, 120, i, 0, "easy", 120));
            }
        }
    }
}
