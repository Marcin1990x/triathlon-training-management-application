package pl.koneckimarcin.triathlontrainingmanagement.user;

import jakarta.persistence.*;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;

@Entity
@Table(name = "application_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    @OneToOne
    @JoinColumn(name = "athlete_id")
    private AthleteEntity athleteEntity;

    @OneToOne
    @JoinColumn(name = "coach_id")
    private CoachEntity coachEntity;
}
