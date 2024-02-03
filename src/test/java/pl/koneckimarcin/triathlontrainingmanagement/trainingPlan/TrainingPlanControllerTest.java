package pl.koneckimarcin.triathlontrainingmanagement.trainingPlan;

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
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
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
    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.training-plan}")
    private String sqlAddPlan;
    @Value("${sql.script.create.training-plan2}")
    private String sqlAddPlan2;
    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;


    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.training-plan}")
    private String sqlDeleteTrainingPlan;
    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;

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

    @Test
    void getTrainingPlansByAthleteIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/{id}/training-plans", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is("testplan")));
    }

    @Test
    void getTrainingPlansByCoachIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/coaches/{id}/training-plans", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void deleteTrainingPlanByIdHttpRequestValidAndNonValidId() throws Exception {

        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(2));

        mockMvc.perform(MockMvcRequestBuilders.delete("/training-plans/{id}", 10))
                .andExpect(status().isOk());

        assertFalse(trainingPlanRepository.findById(1L).isPresent());
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(1));
        //non valid id
        mockMvc.perform(MockMvcRequestBuilders.delete("/training-plans/{id}", 10))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("TrainingPlan not found with id : '10'")));

        assertThat(trainingPlanRepository.findAll(), hasSize(1));
    }

    @Test
    void addNewTrainingPlanToCoachHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/coaches/{id}/training-plans", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainingPlan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test plan")))
                .andExpect(jsonPath("$.trainingType", is("SWIM")));

        assertThat(trainingPlanRepository.findByTrainingType(TrainingType.SWIM), hasSize(2));
        assertThat(coachRepository.findById(1L).get().getTrainingPlans(), hasSize(3));
    }

    @Test
    void addTrainingPlanToAthleteWithDateHttpRequestExpectSetDateAndStatusPlanned() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/athletes/{id}/training-plans/{id}", 1, 11)
                        .param("plannedDate", "2025-01-22"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainingPlanStatus", is("PLANNED")))
                .andExpect(jsonPath("$.plannedDate", is("2025-01-22")));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteTrainingPlan);
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteAthlete);
    }
}
