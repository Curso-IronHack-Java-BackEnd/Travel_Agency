package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.User;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgentSelfCreateDTO extends User {

    private String specialization;

//    @NotNull
//    private Long managerId;
}
