package com.miguelprojects.travel_agency.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgentCreateDTO extends User {

    private String specialization;

    @NotBlank
    @Min(value =1, message = "The commision rate must be at least 1%")
    @Max(value =15, message = "The commision rate must be a maximum of 15%")
    @Digits(integer = 4, fraction = 2, message = "Wrong Rating Format")
    private BigDecimal commissionRate;

    @NotBlank
    private Long managerId;

}

