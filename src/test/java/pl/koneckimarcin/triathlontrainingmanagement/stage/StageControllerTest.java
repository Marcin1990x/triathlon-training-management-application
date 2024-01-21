package pl.koneckimarcin.triathlontrainingmanagement.stage;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource("/application-test-stage.properties")
@AutoConfigureMockMvc
public class StageControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void addNewStageToTrainingPlanHttpRequest() throws Exception {

        BikeStage bikeStage = new BikeStage();
        bikeStage.setSequence(1);
        bikeStage.setPower(100);

        assertThat(trainingPlanRepository.findById(10L).get().getStages(), hasSize(1));

        mockMvc.perform(MockMvcRequestBuilders.post("/training-plans/{id}/stages=bike", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bikeStage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.power", is(100)))
                .andExpect(jsonPath("$.sequence", is(1)));

        assertThat(trainingPlanRepository.findById(10L).get().getStages(), hasSize(2));
    }
    @Test
    void addNewStageToTrainingPlanHttpRequestIncompatibleTrainingType() throws Exception {

        SwimStage swimStage = new SwimStage();
        swimStage.setSequence(1);

        String errorMessageWithType = "This stage: " + swimStage + " can be added only for training type: SWIM";

        mockMvc.perform(MockMvcRequestBuilders.post("/training-plans/{id}/stages=swim", 10)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(swimStage)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message", is(errorMessageWithType)));

        assertThat(trainingPlanRepository.findById(10L).get().getStages(), hasSize(1));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteStage);
        jdbc.execute(sqlDeleteTrainingPlan);
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteAthlete);
    }
}
