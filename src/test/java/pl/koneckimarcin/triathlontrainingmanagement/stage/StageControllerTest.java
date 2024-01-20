package pl.koneckimarcin.triathlontrainingmanagement.stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource("/application-test-stage.properties")
@AutoConfigureMockMvc
public class StageControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

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
    void getStagesForTrainingPlanByIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/training-plans/{id}/stages", 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteStage);
        jdbc.execute(sqlDeleteTrainingPlan);
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteAthlete);
    }
}
