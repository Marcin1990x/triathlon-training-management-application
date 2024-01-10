package pl.koneckimarcin.triathlontrainingmanagement.coach;

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
import org.springframework.transaction.annotation.Transactional;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test-coach.properties")
@Transactional
public class CoachControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.coach2}")
    private String sqlAddCoach2;

    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddCoach2);
    }

    @Test
    void getCoachByIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/coaches/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Coach")))
                .andExpect(jsonPath("$.lastName", is("Best")));

        // nonValid id
        mockMvc.perform(MockMvcRequestBuilders.get("/coaches/{id}", 3))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Coach not found with id : '3'")));
    }

    @Test
    void addCoachHttpRequest() throws Exception {

        jdbc.execute(sqlDeleteCoach);

        Coach coach = new Coach("New", "Coach");

        mockMvc.perform(MockMvcRequestBuilders.post("/coaches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coach)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("New")))
                .andExpect(jsonPath("$.lastName", is("Coach")));

        assertThat(coachRepository.findAll(), hasSize(1));
        //nonValid Coach
        Coach coachNonValid = new Coach();
        coachNonValid.setFirstName("Created");

        mockMvc.perform(MockMvcRequestBuilders.post("/coaches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coachNonValid)))
                .andExpect(status().is(400));

        assertThat(coachRepository.findAll(), hasSize(1));
    }

    @Test
    void deleteCoachEntityByIdHttpRequestValidAndNonValidId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/coaches/{id}", 1))
                .andExpect(status().isOk());

        assertThat(coachRepository.findAll(), hasSize(1));

        mockMvc.perform(MockMvcRequestBuilders.delete("/coaches/{id}", 1))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("Coach not found with id : '1'")));

        assertThat(coachRepository.findAll(), hasSize(1));
    }

    @Test
    void addNewTrainingPlanToCoachHttpRequest() throws Exception {

        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setDescription("New training plan");

        mockMvc.perform(MockMvcRequestBuilders.put("/coaches/{id}/training-plans", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainingPlan)))
                .andExpect(status().isOk());
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteCoach);
    }
}
