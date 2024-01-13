package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test-athlete.properties")
@Transactional
public class AthleteServiceTest {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private AthleteService athleteService;
    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private CoachRepository coachRepository;

    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.athlete2}")
    private String sqlAddAthlete2;
    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddAthlete2);
    }

    @Test
    void isAthleteEntityNullCheck() {

        assertTrue(athleteService.checkIfIsNotNull(1));
        assertFalse(athleteService.checkIfIsNotNull(0));
    }

    @Test
    void shouldReturnAthleteById() {

        Athlete athlete = athleteService.getById(1L);
        assertNotNull(athlete);
        assertEquals("Bob", athlete.getFirstName());
        assertEquals("Nowak", athlete.getLastName());
    }

    @Test
    void shouldReturnAthletesByCoachId() {

        assertTrue(coachRepository.findById(10L).isPresent());
        assertThat(athleteService.getAthletesByCoachId(10L), hasSize(2));
    }

    @Test
    void shouldThrowAnExceptionFindByIdAthleteIdNonValid() {

        long nonValidId = 3L;

        assertFalse(athleteRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> athleteService.getById(nonValidId));
        assertEquals("Athlete not found with id : '" + nonValidId + "'", exception.getMessage());
    }

    @Test
    void shouldSaveNewAthlete() {

        jdbc.execute(sqlDeleteAthlete); // todo: why need this?

        Athlete athlete = new Athlete("New", "Athlete");

        athleteService.addNew(athlete);
        assertThat(athleteRepository.findAll(), hasSize(1));
    }

    @Test
    void shouldDeleteAthleteById() {

        long id = 1L;

        assertTrue(athleteRepository.findById(id).isPresent());
        assertThat(athleteRepository.findAll(), hasSize(2));

        athleteService.deleteById(id);

        assertThat(athleteRepository.findAll(), hasSize(1));
        assertFalse(athleteRepository.findById(id).isPresent());
    }

    @Test
    void shouldThrowAnExceptionDeleteByIdAthleteIdNonValid() {

        long nonValidId = 3L;

        assertFalse(athleteRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> athleteService.deleteById(nonValidId));
        assertEquals("Athlete not found with id : '" + nonValidId + "'", exception.getMessage());
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteAthlete);
        jdbc.execute(sqlDeleteCoach);
    }
}
