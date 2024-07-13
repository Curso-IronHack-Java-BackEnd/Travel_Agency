package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerCreateDTO extends Person {

    @NotBlank(message = "Department is mandatory")
    private String department;

    @Min(value = 2, message = "Minimun experience is 2 years")
    private Integer yearsOfExperience;

    public ManagerCreateDTO(String firstName, String lastName, String phoneNumber, String email, String department, Integer yearsOfExperience) {
        super(firstName, lastName, phoneNumber, email);
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
    }
}
