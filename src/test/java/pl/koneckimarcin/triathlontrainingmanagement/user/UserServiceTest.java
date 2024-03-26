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
import pl.koneckimarcin.triathlontrainingmanagement.athlete.repository.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.exception.IsAlreadyAssignedException;
import pl.koneckimarcin.triathlontrainingmanagement.user.dto.User;
import pl.koneckimarcin.triathlontrainingmanagement.user.role.Role;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource("/application-test-user.properties")
public class UserServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private UserService userService;

    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private UserRepository userRepository;

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
    @Value("${sql.script.create.role}")
    private String sqlAddRole;
    @Value("${sql.script.create.role2}")
    private String sqlAddRole2;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.user}")
    private String sqlDeleteUser;
    @Value("${sql.script.delete.role}")
    private String sqlDeleteRole;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddRole);
        jdbc.execute(sqlAddRole2);
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddUser);
        jdbc.execute(sqlAddCoach);
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

    @Test
    void shouldAddCoachToUserAndAssignCoachRole() {

        jdbc.execute(sqlAddUser1);

        User user = userService.addCoachToUser(11L, 1L);
        assertEquals(1, user.getCoachId());

        UserEntity dbUser = userRepository.findById(11L).get();

        assertTrue(coachRepository.findById(1L).get().isAssignedToUser());
        assertTrue(dbUser.getRoles().stream().anyMatch(role -> role.getRole() == Role.COACH));
    }

    @Test
    void shouldThrowIsAlreadyAssignedExceptionAddCoachToUser() {

        jdbc.execute(sqlAddUser1);

        userService.addCoachToUser(10L, 1L);
        assertThrows(IsAlreadyAssignedException.class, () ->
                userService.addCoachToUser(10L, 1L));
    }

    @Test
    void shouldAddAthleteToUserAndAssignAthleteRole() {

        jdbc.execute(sqlAddUser1);
        jdbc.execute(sqlAddAthlete2);

        User user = userService.addAthleteToUser(11L, 2L);
        assertEquals(2, user.getAthleteId());

        UserEntity dbUser = userRepository.findById(11L).get();

        assertTrue(athleteRepository.findById(2L).get().isAssignedToUser());
        assertTrue(dbUser.getRoles().stream().anyMatch(role -> role.getRole() == Role.ATHLETE));
    }

    @Test
    void shouldThrowIsAlreadyAssignedExceptionAddAthleteToUser() {

        jdbc.execute(sqlAddAthlete2);

        assertThrows(IsAlreadyAssignedException.class, () ->
                userService.addAthleteToUser(10L, 2L));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteUser);
        jdbc.execute(sqlDeleteAthlete);
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteRole);
    }

}
