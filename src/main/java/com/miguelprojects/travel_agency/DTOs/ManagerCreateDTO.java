package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerCreateDTO extends User {

    @NotBlank(message = "Department is mandatory")
    private String department;

    @Min(value = 2, message = "Minimun experience is 2 years")
    private Integer yearsOfExperience;

}
