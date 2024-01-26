package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("/application-test-athlete.properties")
public class AthleteControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.athlete2}")
    private String sqlAddAthlete2;
    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;

    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddAthlete2);
    }

    @Test
    void getAthleteByIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Bob")))
                .andExpect(jsonPath("$.lastName", is("Nowak")));

        // nonValid id
        mockMvc.perform(MockMvcRequestBuilders.get("/athletes/{id}", 3))
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Athlete not found with id : '3'")));
    }

    @Test
    void getAthletesByCoachIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/coaches/{id}/athletes", 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void addAthleteHttpRequest() throws Exception {

        jdbc.execute(sqlDeleteAthlete);

        Athlete athlete = new Athlete("New", "Athlete");

        mockMvc.perform(MockMvcRequestBuilders.post("/athletes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(athlete)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("New")))
                .andExpect(jsonPath("$.lastName", is("Athlete")));

        assertThat(athleteRepository.findAll(), hasSize(1));
    }

    @Test
    void deleteAthleteByIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/athletes/{id}", 1))
                .andExpect(status().isOk());

        assertThat(athleteRepository.findAll(), hasSize(1));

        mockMvc.perform(MockMvcRequestBuilders.delete("/athletes/{id}", 1))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("Athlete not found with id : '1'")));

        assertThat(athleteRepository.findAll(), hasSize(1));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteAthlete);
        jdbc.execute(sqlDeleteCoach);
    }
}
