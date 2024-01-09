package pl.koneckimarcin.triathlontrainingmanagement.coach;

import pl.koneckimarcin.triathlontrainingmanagement.athlete.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Coach {

    private Long id;

    private String firstName;

    private String lastName;

    private List<Athlete> athletes = new ArrayList<>();

    private Set<TrainingPlan> trainingPlans = new HashSet<>();

    public Coach() {
    }

    public Coach(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CoachEntity mapToCoachEntity() {

        CoachEntity coachEntity = new CoachEntity();
        coachEntity.setId(this.id);
        coachEntity.setFirstName(this.firstName);
        coachEntity.setLastName(this.lastName);
        coachEntity.setAthletes(this.athletes.stream().map(Athlete::mapToAthleteEntity).toList());
        coachEntity.setTrainingPlans
                (this.trainingPlans.stream().map(TrainingPlan::mapToTrainingPlanEntity).collect(Collectors.toSet()));

        return coachEntity;
    }

    public static Coach fromCoachEntity(CoachEntity coachEntity) {

        Coach coach = new Coach();
        coach.setId(coachEntity.getId());
        coach.setFirstName(coachEntity.getFirstName());
        coach.setLastName(coachEntity.getLastName());
        coach.setAthletes(coachEntity.getAthletes().stream().map(Athlete::fromAthleteEntity).toList());

        return coach;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public Set<TrainingPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(Set<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }
}
