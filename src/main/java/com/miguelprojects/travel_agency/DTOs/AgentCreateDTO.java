package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.Person;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgentCreateDTO extends Person {

    private String specialization;

    @NotNull
    @Min(value =1, message = "The commision rate must be at least 1%")
    @Max(value =15, message = "The commision rate must be a maximum of 15%")
    @Digits(integer = 4, fraction = 2, message = "Wrong Rating Format")
    private BigDecimal commissionRate;

    @NotNull
    private Long managerId;

}

