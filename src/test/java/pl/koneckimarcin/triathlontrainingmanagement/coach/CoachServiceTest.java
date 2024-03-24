package pl.koneckimarcin.triathlontrainingmanagement.coach;

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
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.Coach;
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.CoachResponseDto;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test-coach.properties")
@Transactional
public class CoachServiceTest {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private CoachService coachService;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private AthleteRepository athleteRepository;

    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;
    @Value("${sql.script.create.coach2}")
    private String sqlAddCoach2;

    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;
    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddCoach2);
        jdbc.execute(sqlAddAthlete);
    }

    @Test
    void isCoachEntityNullCheck() {

        assertTrue(coachService.checkIfIsNotNull(1L));
        assertFalse(coachService.checkIfIsNotNull(0L));
    }

    @Test
    void shouldReturnCoachById() {

        CoachResponseDto coach = coachService.findById(1L);
        assertNotNull(coach);
        assertEquals("Coach", coach.getFirstName());
        assertEquals("Best", coach.getLastName());
    }

    @Test
    void shouldThrowAnExceptionFindByIdCoachEntityIdNonValid() {

        long nonValidId = 3L;

        assertFalse(coachRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> coachService.findById(nonValidId));
        assertEquals("Coach not found with id : '" + nonValidId + "'", exception.getMessage());
    }

    @Test
    void shouldSaveNewCoach() {

        jdbc.execute(sqlDeleteCoach); // todo: why need this?

        Coach coach = new Coach("New", "Coach");

        coachService.addNew(coach);
        assertThat(coachRepository.findAll(), hasSize(1));
    }

    @Test
    void shouldDeleteCoachEntityById() {

        long id = 1L;

        assertTrue(coachRepository.findById(id).isPresent());
        assertThat(coachRepository.findAll(), hasSize(2));

        coachService.deleteById(id);

        assertThat(coachRepository.findAll(), hasSize(1));
        assertFalse(coachRepository.findById(id).isPresent());
    }

    @Test
    void shouldThrowAnExceptionDeleteByIdCoachEntityIdNonValid() {

        long nonValidId = 3L;

        assertFalse(coachRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> coachService.deleteById(nonValidId));
        assertEquals("Coach not found with id : '" + nonValidId + "'", exception.getMessage());
    }

    @Test
    void shouldAddAthleteToCoach() {

        assertTrue(coachRepository.findById(1L).isPresent());
        assertTrue(athleteRepository.findById(1L).isPresent());

        coachService.addAthleteToCoach(1L, 1L);

        assertThat(coachRepository.findById(1L).get().getAthletes(), hasSize(1));
    }
    @Test
    void shouldRemoveAthleteFromCoachSet() {

        assertTrue(coachRepository.findById(1L).isPresent());
        assertTrue(athleteRepository.findById(1L).isPresent());

        coachService.addAthleteToCoach(1L, 1L);
        assertThat(coachRepository.findById(1L).get().getAthletes(), hasSize(1));

        coachService.removeAthleteFromCoach(1L, 1L);
        assertThat(coachRepository.findById(1L).get().getAthletes(), hasSize(0));
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteCoach);
        jdbc.execute(sqlDeleteAthlete);
    }
}
