package pl.koneckimarcin.triathlontrainingmanagement.security.registration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import pl.koneckimarcin.triathlontrainingmanagement.exception.EmailAddressAlreadyExistException;
import pl.koneckimarcin.triathlontrainingmanagement.user.dto.User;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test-security.properties")
public class RegistrationServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationService loginService;

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
    void shouldRegisterNewUser() {

        loginService.registerUser(user);

        assertTrue(userRepository.findByEmailAddress(user.getEmailAddress()).isPresent());
    }

    @Test
    void shouldThrowEmailAddressAlreadyExistExceptionWhenRegisterNewUser() {

        loginService.registerUser(user);

        assertThrows(EmailAddressAlreadyExistException.class, () ->
                loginService.registerUser(user));
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
        user.setEmailAddress("testEmail@Address");
        return user;
    }


}
