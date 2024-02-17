package pl.koneckimarcin.triathlontrainingmanagement.trainingRealization;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.repository.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.Feelings;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.dto.TrainingRealizationRequest;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.repository.TrainingRealizationRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.service.TrainingRealizationService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test-training_realization.properties")
public class TrainingRealizationServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private TrainingRealizationService trainingRealizationService;

    @Autowired
    private TrainingRealizationRepository trainingRealizationRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Value("${sql.script.create.training-realization}")
    private String sqlAddTrainingRealization;
    @Value("${sql.script.create.athlete}")
    private String sqlAddAthlete;

    @Value("${sql.script.delete.athlete}")
    private String sqlDeleteAthlete;
    @Value("${sql.script.delete.training-realization}")
    private String sqlDeleteTrainingRealization;

    private TrainingRealization trainingRealization;

    @BeforeEach
    void setup() {
        jdbc.execute(sqlAddAthlete);
        jdbc.execute(sqlAddTrainingRealization);

        trainingRealization = new TrainingRealization();
        trainingRealization.setRealizationDescription("Done.");
    }

    @Test
    void shouldGetTrainingRealizationsByAthleteId() {

        assertTrue(athleteRepository.findById(10L).isPresent());

        assertThat(trainingRealizationService.getTrainingRealizationsByAthleteId(10L), hasSize(1));
    }

    @Test
    void shouldDeleteTrainingRealizationByIdAndFromAthleteList() {

        assertTrue(trainingRealizationRepository.findById(10L).isPresent());
        assertTrue(athleteRepository.findById(10L).isPresent());
        assertThat(athleteRepository.findById(10L).get().getTrainingRealizations(), hasSize(1));

        trainingRealizationService.deleteById(10L);

        assertFalse(trainingRealizationRepository.findById(10L).isPresent());
        assertThat(athleteRepository.findById(10L).get().getTrainingRealizations(), hasSize(0));
    }

    @Test
    void shouldThrowAnExceptionDeleteByIdTrainingRealizationEntityIdNonValid() {

        long nonValidId = 2L;

        assertFalse(trainingRealizationRepository.findById(nonValidId).isPresent());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> trainingRealizationService.deleteById(nonValidId));
        assertEquals("TrainingRealization not found with id : '" + nonValidId + "'", exception.getMessage());
    }

    @Test
    void shouldUpdateTrainingRealizationById() {

        assertTrue(trainingRealizationRepository.findById(10L).isPresent());

        trainingRealizationService.updateTrainingRealizationById(10L, getRequest());

        TrainingRealizationEntity updatedTraining = trainingRealizationRepository.findById(10L).get();

        assertEquals(updatedTraining.getRealizationDescription(), getRequest().getRealizationDescription());
        assertEquals(updatedTraining.getFeelings(), getRequest().getFeelings());
        assertEquals(updatedTraining.getRpeLevel(), getRequest().getRpeLevel());
    }

    @AfterEach
    void clean() {
        jdbc.execute(sqlDeleteTrainingRealization);
        jdbc.execute(sqlDeleteAthlete);
    }

    private TrainingRealizationRequest getRequest() {

        TrainingRealizationRequest request = new TrainingRealizationRequest(
                "Done.",
                Feelings.WEAK,
                6
        );
        return request;
    }
}
