package pl.koneckimarcin.triathlontrainingmanagement.athlete.dto;

import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;

public class AthleteResponseDto {

    private Long id;
    private String firstName;
    private String lastName;

    public static AthleteResponseDto fromAthleteEntity(AthleteEntity athleteEntity) {

        AthleteResponseDto athlete = new AthleteResponseDto();

        athlete.setId(athleteEntity.getId());
        athlete.setFirstName(athleteEntity.getFirstName());
        athlete.setLastName(athleteEntity.getLastName());

        return athlete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
