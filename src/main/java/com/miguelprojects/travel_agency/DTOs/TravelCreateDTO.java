package com.miguelprojects.travel_agency.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelCreateDTO {

    @NotBlank(message = "Destination is mandatory")
    private String destination;

    @NotNull(message = "Duration is mandatory")
    private Integer duration;

    @NotNull(message = "Final price is mandatory")
    private BigDecimal finalPrice;

    @NotNull
    private Long customerId;

    @NotNull
    private String reservationCode;

}
