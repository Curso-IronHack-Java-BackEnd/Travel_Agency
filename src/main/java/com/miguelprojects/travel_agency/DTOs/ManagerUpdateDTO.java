package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.Person;
import jakarta.validation.constraints.Min;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerUpdateDTO extends Person {

    private String department;

    @Min(value = 2, message = "Minimun experience is 2 years")
    private Integer yearsOfExperience;
}
