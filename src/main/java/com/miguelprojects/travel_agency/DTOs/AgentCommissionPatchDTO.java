package com.miguelprojects.travel_agency.DTOs;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentCommissionPatchDTO {

    @NotNull
    @Min(value =1, message = "The commision rate must be at least 1%")
    @Max(value =15, message = "The commision rate must be a maximum of 15%")
    @Digits(integer = 4, fraction = 2, message = "Wrong Rating Format")
    private BigDecimal commissionRate;

}
