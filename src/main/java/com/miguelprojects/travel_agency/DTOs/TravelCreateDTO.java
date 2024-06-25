package com.miguelprojects.travel_agency.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.FlightBooking;
import com.miguelprojects.travel_agency.Models.HotelBooking;
import com.miguelprojects.travel_agency.Models.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
