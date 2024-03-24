package pl.koneckimarcin.triathlontrainingmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.dto.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.service.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.coach.Coach;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachService;
import pl.koneckimarcin.triathlontrainingmanagement.security.registration.RegistrationService;
import pl.koneckimarcin.triathlontrainingmanagement.strava.StravaPropertyReader;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;
import pl.koneckimarcin.triathlontrainingmanagement.user.User;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserService;
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

    @Override
    public void run(String... args) throws Exception {

        //prepare triathlete user
        String stravaRefreshToken = StravaPropertyReader.getValue("strava_refresh_token");
        User triathlete = new User("Triathlete", "triathlete", "triathlete@app.com");
        triathlete.setStravaRefreshToken(stravaRefreshToken);

        //add users - for coach and for three athletes
        registrationService.registerUser(new User("Coach", "coach", "coach@app.com"));
        registrationService.registerUser(triathlete);
        registrationService.registerUser(new User("Runner", "runner", "runner@app.com"));
        registrationService.registerUser(new User("Ultrarunner", "Ultrarunner", "ultrarunner@app.com"));
        //add coach, three athletes and assign to users
        coachService.addNew(new Coach("Bob", "Coach"));
        userService.addCoachToUser(1L, 1L);
        athleteService.addNew(new Athlete("John", "Triathlete", 14748685L));
        userService.addAthleteToUser(2L, 1L);
        athleteService.addNew(new Athlete("Adam", "Runner"));
        userService.addAthleteToUser(3L, 2L);
        athleteService.addNew(new Athlete("Mike", "Ultrarunner"));
        userService.addAthleteToUser(4L, 3L);
        //add athletes to coach
        coachService.addAthleteToCoach(1L, 1L);
        coachService.addAthleteToCoach(1L, 2L);
        //add training plans with stages to coach
        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Easy swimming", TrainingType.SWIM, "1500m easy"));
        stageService.addNewSwimStageToTrainingPlan(1L,
                new SwimStage(1500, 1800, 1, 0, "easy", 120, 1));

        tpService.addNewTrainingPlanToCoach(1L,
                new TrainingPlan("Swimming intervals", TrainingType.SWIM, "100m hard/100m easy x10"));

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
    }
}
