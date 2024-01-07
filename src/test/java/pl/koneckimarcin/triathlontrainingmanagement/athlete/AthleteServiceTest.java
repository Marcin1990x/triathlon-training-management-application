package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestNonValidFieldsException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.List;

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

    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.athlete2}")
    private String sqlAddAthlete2;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddAthlete2);
    }
    @Test
    void isAthleteEntityNullCheck() {

        assertTrue(athleteService.checkIfAthleteEntityIsNotNull(1));
        assertFalse(athleteService.checkIfAthleteEntityIsNotNull(0));
    }

    @Test
    void shouldReturnAllAthletes() {

        List<Athlete> athletes = athleteService.getAllAthletes();

        assertThat(athletes, hasSize(2));
        Assertions.assertEquals("Nowak", athletes.get(0).getLastName());
        Assertions.assertEquals("Kowalski", athletes.get(1).getLastName());
    }

    @Test
    void shouldReturnAthleteEntityById() {

        AthleteEntity athleteEntity = athleteService.findAthleteEntityById(1);
        assertNotNull(athleteEntity);
        assertEquals("Bob", athleteEntity.getFirstName());
        assertEquals("Nowak", athleteEntity.getLastName());
    }
    @Test
    void shouldThrowAnExceptionFindByIdAthleteEntityIdNonValid() {

        long nonValidId = 3L;

        assertFalse(athleteRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> athleteService.findAthleteEntityById(nonValidId));
        assertEquals("AthleteEntity not found with id : '" + nonValidId + "'", exception.getMessage());
    }

    @Test
    void shouldReturnAthleteByLastName() {

        String lastName = "Nowak";

        Athlete athlete = athleteService.findAthleteByLastName(lastName);

        assertNotNull(athlete);
        assertEquals("Nowak", athlete.getLastName());
    }
    @Test
    void shouldThrowAnExceptionFindByLastNameAthleteEntityIdNonValid() {

        String nonValidLastname = "Test";

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> athleteService.findAthleteByLastName(nonValidLastname));
        assertEquals("Athlete not found with lastname : '" + nonValidLastname + "'", exception.getMessage());
    }

    @Test
    void shouldSaveNewAthlete() {

        jdbc.execute(sqlDeleteAthlete); // todo: why need this?

        Athlete athlete = new Athlete("New", "Athlete");

        athleteService.addAthlete(athlete);
        assertThat(athleteRepository.findAll(), hasSize(1));
    }
    @Test
    void shouldThrowAnExceptionSaveNewAthleteNonValidObject() {

        String errorMessage = "This fields can not be empty: [firstname, lastname]";

        Athlete athlete = new Athlete();
        athlete.setFirstName("New");
        Athlete athlete1 = new Athlete();
        athlete1.setFirstName("New");
        athlete1.setLastName("");

        BadRequestNonValidFieldsException exception = assertThrows(BadRequestNonValidFieldsException.class,
                () -> athleteService.addAthlete(athlete));
        assertEquals(errorMessage, exception.getMessage());

        assertThrows(BadRequestNonValidFieldsException.class,
                () -> athleteService.addAthlete(athlete1));
    }

    @Test
    void shouldDeleteAthleteEntityById() {

        long id = 1L;

        assertTrue(athleteRepository.findById(id).isPresent());
        assertThat(athleteRepository.findAll(), hasSize(2));

        athleteService.deleteAthleteEntityById(id);

        assertThat(athleteRepository.findAll(), hasSize(1));
        assertFalse(athleteRepository.findById(id).isPresent());
    }

    @Test
    void shouldThrowAnExceptionDeleteByIdAthleteEntityIdNonValid() {

        long nonValidId = 3L;

        assertFalse(athleteRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> athleteService.deleteAthleteEntityById(nonValidId));
        assertEquals("AthleteEntity not found with id : '" + nonValidId + "'", exception.getMessage());
    }
    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteAthlete);
    }
}
