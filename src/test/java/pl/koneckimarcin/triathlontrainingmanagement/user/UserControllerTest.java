package pl.koneckimarcin.triathlontrainingmanagement.user;

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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("/application-test-user.properties")
public class UserControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.athlete2}")
    private String sqlAddAthlete2;
    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.user}")
    private String sqlAddUser;
    @Value("${sql.script.create.user1}")
    private String sqlAddUser1;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.user}")
    private String sqlDeleteUser;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddUser);
        jdbc.execute(sqlAddCoach);
    }

    @Test
    void getAllUsersHttpRequest() throws Exception {

        jdbc.execute(sqlAddUser1);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getUserByIdHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("Marcin1990")));
    }

    @Test
    void addCoachToUserHttpRequest() throws Exception {

        jdbc.execute(sqlAddUser1);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}/coaches/{coachId}/add", 11, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coachId", is(1)));
    }

    @Test
    void addAthleteToUserHttpRequest() throws Exception {

        jdbc.execute(sqlAddUser1);
        jdbc.execute(sqlAddAthlete2);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}/athletes/{athleteId}/add", 11, 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.athleteId", is(2)));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteUser);
        jdbc.execute(sqlDeleteAthlete);
        jdbc.execute(sqlDeleteCoach);
    }
}
