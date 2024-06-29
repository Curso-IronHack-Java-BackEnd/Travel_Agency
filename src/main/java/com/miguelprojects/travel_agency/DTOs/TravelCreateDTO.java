package com.miguelprojects.travel_agency.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelCreateDTO {

    @NotNull(message = "Destination is mandatory")
    private String destination;

    @NotNull(message = "Duration is mandatory")
    private String duration;

    @NotNull(message = "Final price is mandatory")
    private BigDecimal finalPrice;


    private Long customerId;

    private String reservationCode;

}
