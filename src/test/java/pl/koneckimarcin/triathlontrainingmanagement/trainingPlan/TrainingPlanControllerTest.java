package pl.koneckimarcin.triathlontrainingmanagement.trainingPlan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test-training_plan.properties")
public class TrainingPlanControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

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

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddPlan);
    }

    @Test
    void deleteTrainingPlanByIdHttpRequestValidAndNonValidId() throws Exception {

        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(1));

        mockMvc.perform(MockMvcRequestBuilders.delete("/training-plans/{id}", 1))
                .andExpect(status().isOk());

        assertFalse(trainingPlanRepository.findById(1L).isPresent());
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(0));
        //non valid id
        mockMvc.perform(MockMvcRequestBuilders.delete("/training-plans/{id}", 1))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("TrainingPlan not found with id : '1'")));

        assertThat(trainingPlanRepository.findAll(), hasSize(0));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeletePlan);
        jdbc.execute(sqlDeleteCoach);

    }
}
