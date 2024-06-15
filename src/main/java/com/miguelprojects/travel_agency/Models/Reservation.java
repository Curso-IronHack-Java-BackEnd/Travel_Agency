package com.miguelprojects.travel_agency.Models;

import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name ="reservations")
@DynamicUpdate
public class Reservation {
    @Id
    @Column(name = "reservation_code")
    private String reservationCode;

    @NotNull(message = "Promotions are mandatory")
    @Enumerated(EnumType.STRING)
    private Promotions promotions;

    @NotBlank(message = "Number of adults is mandatory")
    private Integer adults;

    private Integer children;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Payment Method is mandatory")
    private PaymentMethod paymentMethod;

    @Column(name = "reservation_status")
    @NotNull(message = "Reservation Status is mandatory")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @NotNull(message = "Price is mandatory")
    private Double price;

    @Column(name = "date_of_reservation")
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
