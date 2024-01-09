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
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestNonValidFieldsException;
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

    @Value("${sql.script.create.coach}")
    private String sqlAddCoach;
    @Value("${sql.script.create.coach2}")
    private String sqlAddCoach2;

    @Value("${sql.script.delete.coach}")
    private String sqlDeleteCoach;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddCoach);
        jdbc.execute(sqlAddCoach2);
    }

    @Test
    void isCoachEntityNullCheck() {

        assertTrue(coachService.checkIfIsNotNull(1L));
        assertFalse(coachService.checkIfIsNotNull(0L));
    }

    @Test
    void shouldReturnCoachById() {

        Coach coach = coachService.findById(1L);
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
    void shouldThrowAnExceptionSaveNewCoachNonValidObject() {

        String errorMessage = "This fields can not be empty: [firstname, lastname]";

        Coach coach = new Coach();
        coach.setFirstName("New");
        Coach coach1 = new Coach();
        coach1.setFirstName("New");
        coach1.setLastName("");

        BadRequestNonValidFieldsException exception = assertThrows(BadRequestNonValidFieldsException.class,
                () -> coachService.addNew(coach));
        assertEquals(errorMessage, exception.getMessage());

        assertThrows(BadRequestNonValidFieldsException.class,
                () -> coachService.addNew(coach1));
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

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteCoach);
    }
}
