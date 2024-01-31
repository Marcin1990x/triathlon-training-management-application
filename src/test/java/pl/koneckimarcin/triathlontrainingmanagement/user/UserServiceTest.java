package pl.koneckimarcin.triathlontrainingmanagement.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource("/application-test-user.properties")
public class UserServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.user}")
    private String sqlAddUser;
    @Value("${sql.script.create.user1}")
    private String sqlAddUser1;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.user}")
    private String sqlDeleteUser;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddUser);
    }

    @Test
    void shouldReturnAllUsers() {

        jdbc.execute(sqlAddUser1);

        List<User> allUsers = userService.getAllUsers();
        assertThat(allUsers.size(), is(2));
    }

    @Test
    void shouldReturnUserById() {

        User user = userService.getUserById(10L);
        assertNotNull(user);
        assertEquals("Marcin1990", user.getUsername());
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteUser);
        jdbc.execute(sqlDeleteAthlete);
    }

}
