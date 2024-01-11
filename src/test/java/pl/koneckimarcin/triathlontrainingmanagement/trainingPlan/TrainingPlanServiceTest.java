package pl.koneckimarcin.triathlontrainingmanagement.trainingPlan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingType;

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

    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.training-plan}")
    private String sqlAddPlan;

    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.training-plan}")
    private String sqlDeletePlan;

    private TrainingPlan trainingPlan;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddPlan);

        trainingPlan = new TrainingPlan();
        trainingPlan.setDescription("New training plan");
        trainingPlan.setName("Test plan");
        trainingPlan.setTrainingType(TrainingType.SWIM);
    }

    // todo: test checkNull

    @Test
    void shouldDeleteTrainingPlanByIdAndFromCoachSet() {

        assertTrue(trainingPlanRepository.findById(1L).isPresent());
        assertTrue(coachRepository.findById(1L).isPresent());

        trainingPlanService.deleteById(1L);

        assertFalse(trainingPlanRepository.findById(1L).isPresent());
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(0));
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
    void shouldAddNewTrainingPlanToCoach() {

        jdbc.execute(sqlDeletePlan);

        assertTrue(coachRepository.findById(1L).isPresent());

        trainingPlanService.addNewTrainingPlanToCoach(1L, trainingPlan);

        assertThat(trainingPlanRepository.findAll(), hasSize(1));
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(1));
    }

    @Test
    void shouldThrowAnExceptionAddNewTrainingPlanToCoachNonValidCoachEntity() {

        assertFalse(coachRepository.findById(2L).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> trainingPlanService.addNewTrainingPlanToCoach(2L,  trainingPlan));

        assertEquals("Coach not found with id : '2'", exception.getMessage());
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeletePlan);
        jdbc.execute(sqlDeleteCoach);

    }
}
