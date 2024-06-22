package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Travel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationDTO {

    private Promotions promotions;

    private Integer adults;

    private Integer children;


    private PaymentMethod paymentMethod;

    private ReservationStatus reservationStatus;

    private BigDecimal deposit;

    @Past(message = "Reservation must have been made in the past")
    private LocalDateTime dateOfReservation;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy="reservation")
    private Travel travel;
}
