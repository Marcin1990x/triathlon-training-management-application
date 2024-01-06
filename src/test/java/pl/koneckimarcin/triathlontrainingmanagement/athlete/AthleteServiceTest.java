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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    private Athlete athlete;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddAthlete2);

        athlete = new Athlete("New", "Athlete");
    }

    @Test
    void shouldReturnAllAthletes() {

        List<Athlete> athletes = athleteService.getAllAthletes();

        assertThat(athletes, hasSize(2));
        Assertions.assertEquals("Nowak", athletes.get(0).getLastName());
        Assertions.assertEquals("Kowalski", athletes.get(1).getLastName());
    }

    @Test
    void shouldReturnAthleteById() {

        Athlete athlete = athleteService.findAthleteById(1);
        assertNotNull(athlete);
        assertEquals("Bob", athlete.getFirstName());
        assertEquals("Nowak", athlete.getLastName());
    }

    @Test
    void shouldThrowAnExceptionAthleteIdNonValid() {
        // todo: do this
    }

    @Test
    void shouldSaveNewAthlete() {

        athleteService.addAthlete(athlete);
        assertThat(athleteRepository.findAll(), hasSize(3));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteAthlete);
    }
}
