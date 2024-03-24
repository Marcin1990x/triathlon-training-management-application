package pl.koneckimarcin.triathlontrainingmanagement.coach.dto;

import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;

public class CoachResponseDto {

    private Long id;
    private String firstName;
    private String lastName;

    public static CoachResponseDto fromCoachEntity(CoachEntity coachEntity) {

        CoachResponseDto coachResponseDto = new CoachResponseDto();

        coachResponseDto.setId(coachEntity.getId());
        coachResponseDto.setFirstName(coachEntity.getFirstName());
        coachResponseDto.setLastName(coachEntity.getLastName());

        return coachResponseDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
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
}
