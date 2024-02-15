package pl.koneckimarcin.triathlontrainingmanagement.trainingRealization;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.koneckimarcin.triathlontrainingmanagement.athlete.repository.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.repository.TrainingRealizationRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.service.TrainingRealizationService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("/application-test-training_realization.properties")
public class TrainingRealizationControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingRealizationService trainingRealizationService;
    @Autowired
    private TrainingRealizationRepository trainingRealizationRepository;
    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sql.script.create.training-realization}")
    private String sqlAddTrainingRealization;
    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.training-realization}")
    private String sqlDeleteTrainingRealization;


    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddTrainingRealization);
    }

    @Test
    void getTrainingRealizationsByAthleteIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/{id}/training-realizations", 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rpeLevel", is(8)));
    }

    @Test
    void deleteTrainingPlanByIdHttpRequestValidAndNonValidId() throws Exception {

        assertThat(athleteRepository.findById(10L).get().getTrainingRealizations(), hasSize(1));

        mockMvc.perform(MockMvcRequestBuilders.delete("/training-realizations/{id}", 10))
                .andExpect(status().isOk());

        assertFalse(trainingRealizationRepository.findById(10L).isPresent());
        assertThat(athleteRepository.findById(10L).get().getTrainingPlans(), hasSize(0));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteTrainingRealization);
        jdbc.execute(sqlDeleteAthlete);
    }
}
