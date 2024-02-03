package pl.koneckimarcin.triathlontrainingmanagement.user.role;

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
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application-test-role.properties")
@AutoConfigureMockMvc(addFilters = false)
public class RoleControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Value("${sql.script.create.user}")
    private String sqlAddUser;
    @Value("${sql.script.create.role}")
    private String sqlAddRole;


    @Value("${sql.script.delete.user}")
    private String sqlDeleteUser;
    @Value("${sql.script.delete.role}")
    private String sqlDeleteRole;

    @BeforeEach
    void setup() {
    }

    @Test
    void addRoleToUserByIdHttpRequest() throws Exception {

        jdbc.execute(sqlAddUser);
        jdbc.execute(sqlAddRole);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}/roles", 10)
                        .param("role", "ATHLETE"))
                .andExpect(status().isOk());

        Optional<RoleEntity> first = userRepository.findById(10L).get().getRoles().stream().findFirst();
        assertTrue(first.isPresent());
        assertEquals(Role.ATHLETE, first.get().getRole());
    }

    @AfterEach
    void clean() {
    }
}
