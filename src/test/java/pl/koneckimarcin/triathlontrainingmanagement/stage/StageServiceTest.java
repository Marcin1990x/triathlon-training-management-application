package pl.koneckimarcin.triathlontrainingmanagement.stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import pl.koneckimarcin.triathlontrainingmanagement.exception.IncompatibleTrainingTypeException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageServiceImpl;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test-stage.properties")
public class StageServiceTest {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private StageServiceImpl stageService;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Value("${sql.script.create.stage}")
    private String sqlAddStage;
    @Value("${sql.script.create.stage1}")
    private String sqlAddStage1;
    @Value("${sql.script.create.training-plan}")
    private String sqlAddTrainingPlan;
    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;

    @Value("${sql.script.delete.stage}")
    private String sqlDeleteStage;
    @Value("${sql.script.delete.training-plan}")
    private String sqlDeleteTrainingPlan;
    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddTrainingPlan);
        jdbc.execute(sqlAddStage);
    }

    @Test
    void shouldReturnStagesForTrainingPlanById() {

        assertTrue(trainingPlanRepository.findById(10L).isPresent());
        assertThat(stageService.getStagesForTrainingPlanById(10L), hasSize(1));
    }

    @Test
    void shouldAddNewStageForTrainingPlanById() {

        BikeStage bikeStage = new BikeStage();
        bikeStage.setSequence(1);
        bikeStage.setPower(100);

        assertTrue(trainingPlanRepository.findById(10L).isPresent());
        stageService.addNewBikeStageToTrainingPlan(10L, bikeStage);
    }

    @Test
    void shouldThrowIncompatibleTrainingTypeExceptionWhenAddNewStageForTrainingPlanById() {

        String errorMessageWithType = " can be added only for training type: SWIM";

        SwimStage swimStage = new SwimStage();
        swimStage.setSequence(1);
        swimStage.setPaceInSeconds(100);

        assertTrue(trainingPlanRepository.findById(10L).isPresent());
        assertSame(trainingPlanRepository.findById(10L).get().getTrainingType(), TrainingType.BIKE);

        IncompatibleTrainingTypeException exception = assertThrows(IncompatibleTrainingTypeException.class,
                () -> stageService.addNewSwimStageToTrainingPlan(10L, swimStage));

        assertTrue(exception.getMessage().contains(swimStage + errorMessageWithType));
    }

    @Test
    void shouldDeleteStageById() {

        assertTrue(stageRepository.findById(10L).isPresent());

        stageService.deleteStageById(10L);

        assertFalse(stageRepository.findById(10L).isPresent());
    }
    @Test
    void shouldDeleteAllStagesFromTrainingPlanById() {

        jdbc.execute(sqlAddStage1);

        assertThat(trainingPlanRepository.findById(10L).get().getStages(), hasSize(2));
        assertTrue(stageRepository.findById(10L).isPresent());
        assertTrue(stageRepository.findById(11L).isPresent());

        stageService.deleteAllStagesFromTrainingPlanById(10L);

        assertThat(trainingPlanRepository.findById(10L).get().getStages(), hasSize(0));
        assertFalse(stageRepository.findById(10L).isPresent());
        assertFalse(stageRepository.findById(11L).isPresent());
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteStage);
        jdbc.execute(sqlDeleteTrainingPlan);
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteAthlete);
    }
}