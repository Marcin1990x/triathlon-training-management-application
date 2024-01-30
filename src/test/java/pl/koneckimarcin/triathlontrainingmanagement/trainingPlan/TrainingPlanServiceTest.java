package pl.koneckimarcin.triathlontrainingmanagement.trainingPlan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.WrongDateException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingPlanStatus;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test-training_plan.properties")
public class TrainingPlanServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private TrainingPlanService trainingPlanService;

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.training-plan}")
    private String sqlAddPlan;
    @Value("${sql.script.create.training-plan2}")
    private String sqlAddPlan2;

    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.training-plan}")
    private String sqlDeletePlan;

    private TrainingPlan trainingPlan;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddPlan);
        jdbc.execute(sqlAddPlan2);

        trainingPlan = new TrainingPlan();
        trainingPlan.setDescription("New training plan");
        trainingPlan.setName("Test plan");
        trainingPlan.setTrainingType(TrainingType.SWIM);
    }

    // todo: test checkNull

    @Test
    void shouldGetTrainingPlansByAthleteId() {

        assertTrue(athleteRepository.findById(1L).isPresent());

        assertThat(trainingPlanService.getTrainingPlansByAthleteId(1L), hasSize(1));
    }

    @Test
    void shouldDeleteTrainingPlanByIdAndFromCoachSet() {

        assertTrue(trainingPlanRepository.findById(10L).isPresent());
        assertTrue(coachRepository.findById(1L).isPresent());

        trainingPlanService.deleteById(10L);

        assertFalse(trainingPlanRepository.findById(10L).isPresent());
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(1));
    }

    @Test
    void shouldThrowAnExceptionDeleteByIdTrainingPlanEntityIdNonValid() {

        long nonValidId = 2L;

        assertFalse(trainingPlanRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> trainingPlanService.deleteById(nonValidId));
        assertEquals("TrainingPlan not found with id : '" + nonValidId + "'", exception.getMessage());
    }

    @Test
    void shouldAddNewTrainingPlanToCoachWithCorrectStatus() {

        assertTrue(coachRepository.findById(1L).isPresent());
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(2));

        TrainingPlan newPlan = trainingPlanService.addNewTrainingPlanToCoach(1L, trainingPlan);

        assertEquals(TrainingPlanStatus.TEMPLATE, newPlan.getTrainingPlanStatus());
        assertThat(trainingPlanRepository.findAll(), hasSize(3));
        System.out.println(coachRepository.findById(1L).get().getTrainingPlans());
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(3));
    }

    @Test
    void shouldThrowAnExceptionAddNewTrainingPlanToCoachNonValidCoachEntity() {

        assertFalse(coachRepository.findById(2L).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> trainingPlanService.addNewTrainingPlanToCoach(2L, trainingPlan));

        assertEquals("Coach not found with id : '2'", exception.getMessage());
    }
    @Test
    void shouldAddTrainingPlanToAthleteWithDate() {

        assertTrue(trainingPlanRepository.findById(10L).isPresent());
        assertTrue(athleteRepository.findById(1L).isPresent());
        assertThat(athleteRepository.findById(1L).get().getTrainingPlans(), hasSize(1));

        Date date = Date.valueOf(LocalDate.now());

        trainingPlanService.addTrainingPlanToAthleteWithDate(1L, 11L, date);

        assertThat(athleteRepository.findById(1L).get().getTrainingPlans(), hasSize(2));
    }
    @Test
    void shouldThrowAnExceptionWhenAddTrainingPlanToAthleteWithInvalidDate() {

        assertThat(athleteRepository.findById(1L).get().getTrainingPlans(), hasSize(1));

        Date date = Date.valueOf(LocalDate.of(2023, 10, 10));

        assertThrows(WrongDateException.class, () ->
                trainingPlanService.addTrainingPlanToAthleteWithDate(1L, 11L, date));

        assertThat(athleteRepository.findById(1L).get().getTrainingPlans(), hasSize(1));
    }


    @AfterEach
    void clean() {
        jdbc.execute(sqlDeletePlan);
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteAthlete);

    }
}
