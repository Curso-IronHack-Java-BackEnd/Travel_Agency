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
public class TravelUpdateDTO {

    private String destination;

    private String duration;

    private BigDecimal finalPrice;

}
