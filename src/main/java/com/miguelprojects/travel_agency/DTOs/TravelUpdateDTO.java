package com.miguelprojects.travel_agency.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelUpdateDTO {

    private String destination;

    private String duration;

    private BigDecimal finalPrice;

}
