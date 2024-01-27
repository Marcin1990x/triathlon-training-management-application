package pl.koneckimarcin.triathlontrainingmanagement.security;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.koneckimarcin.triathlontrainingmanagement.user.User;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test-security.properties")
public class LoginControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ObjectMapper mapper;

    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.user}")
    private String sqlAddUser;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.user}")
    private String sqlDeleteUser;

    private User user;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddUser);
        user = createUser();
    }

    @Test
    void registerUserHttpRequest() throws Exception {

        String responseMessage = "Given user details are successfully registered.";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(userRepository.findByEmailAddress("testEmail@address").isPresent());
        assertEquals(responseMessage, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void registerUserHttpRequestExistingEmailAddress() throws Exception {

        String responseMessage = "This email address: '" + user.getEmailAddress() + "' is already in use. ";

        loginService.registerUser(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(responseMessage)));

        assertTrue(userRepository.findByEmailAddress("testEmail@address").isPresent());
    }


    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteUser);
        jdbc.execute(sqlDeleteAthlete);
    }

    private User createUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmailAddress("testEmail@address");
        return user;
    }


}
