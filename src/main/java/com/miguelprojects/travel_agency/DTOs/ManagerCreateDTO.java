package com.miguelprojects.travel_agency.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
