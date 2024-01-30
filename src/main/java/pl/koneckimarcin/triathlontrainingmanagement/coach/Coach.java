package pl.koneckimarcin.triathlontrainingmanagement.coach;

import jakarta.validation.constraints.NotNull;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;

import java.util.HashSet;
import java.util.Set;

public class Coach {

    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private Set<String> athletes = new HashSet<>();

    private Set<String> trainingPlans = new HashSet<>();

    //private Set<TrainingPlan> trainingPlans = new HashSet<>();

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
//        coachEntity.setTrainingPlans
//                (this.trainingPlans.stream().map(TrainingPlan::mapToTrainingPlanEntity).collect(Collectors.toSet()));

        return coachEntity;
    }

    public static Coach fromCoachEntity(CoachEntity coachEntity) {

        Coach coach = new Coach();
        coach.setId(coachEntity.getId());
        coach.setFirstName(coachEntity.getFirstName());
        coach.setLastName(coachEntity.getLastName());
        if (coachEntity.getAthletes() != null) {
            coach.setAthletes(setAthletesView(coachEntity));
        }
        if(coachEntity.getTrainingPlans() != null) {
            coach.setTrainingPlans(setTrainingPlansView(coachEntity));
        }
        return coach;
    }

    private static Set<String> setTrainingPlansView(CoachEntity coach) {

        Set<String> trainingPlansTypeAndName = new HashSet<>();

        Set<TrainingPlanEntity> trainingPlans = coach.getTrainingPlans();
        for(TrainingPlanEntity plan : trainingPlans) {
            trainingPlansTypeAndName.add(plan.getTrainingType() + ": " + plan.getName());
        }
        return trainingPlansTypeAndName;
    }

    private static Set<String> setAthletesView(CoachEntity coach) {

        Set<String> athletesFirstAndLastName = new HashSet<>();

        Set<AthleteEntity> athletes = coach.getAthletes();
        for (AthleteEntity athlete : athletes) {
            athletesFirstAndLastName.add(athlete.getFirstName() + " " + athlete.getLastName());
        }
        return athletesFirstAndLastName;
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

    public Set<String> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(Set<String> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }

    public Set<String> getAthletes() {
        return athletes;
    }

    public void setAthletes(Set<String> athletes) {
        this.athletes = athletes;
    }
}
