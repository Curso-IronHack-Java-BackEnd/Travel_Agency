package com.miguelprojects.travel_agency.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="agents")
@DynamicUpdate
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "first_name")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 9, max = 15)
    private String phoneNumber;

    private String specialization;

    @Column(name = "commission_rate")
    @Min(value =1, message = "The commision rate must be at least 1%")
    @Max(value =10, message = "The commision rate must be a maximum of 10%")
    @Digits(integer = 2, fraction = 2, message = "Wrong Rating Format")
    private Double commissionRate;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();


    // Constructores




    //Getters and Setters



//    public void addReservation(Reservation reservation) {
//        reservations.add(reservation);
//        reservation.setCustomer(this);
//    }
//
//    public void removeReservation(Reservation reservation) {
//        reservations.remove(reservation);
//        reservation.setCustomer(null);
//    }
}
