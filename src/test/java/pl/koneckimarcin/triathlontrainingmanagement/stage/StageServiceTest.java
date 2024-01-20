package pl.koneckimarcin.triathlontrainingmanagement.stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageServiceImpl;

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

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteStage);
        jdbc.execute(sqlDeleteTrainingPlan);
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteAthlete);
    }
}
