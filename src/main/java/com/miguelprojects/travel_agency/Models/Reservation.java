package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.annotations.DynamicUpdate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name ="reservations")
@DynamicUpdate
public class Reservation {
    @Id
    @Column(name = "reservation_code")
    private String reservationCode;

    @NotNull(message = "Number of adults is mandatory")
    private Integer adults;

    private Integer children;

    @NotNull(message = "Promotions are mandatory")
    @Enumerated(EnumType.STRING)
    private Promotions promotions;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment Method is mandatory")
    private PaymentMethod paymentMethod;

    @Column(name = "reservation_status")
    @NotNull(message = "Reservation Status is mandatory")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @NotNull(message = "Deposit is mandatory")
    @Min(value = 0, message = "Deposit must be positive or 0")
    private BigDecimal deposit;

    @Column(name = "date_of_reservation")
    @Past(message = "Reservation must have been made in the past")
    private LocalDateTime dateOfReservation;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelBooking> hotelBookings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightBooking> flightBookings = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy="reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Travel travel;

    public Reservation() {    }

    public Reservation(String reservationCode, Integer adults, Integer children, Promotions promotions,
                       PaymentMethod paymentMethod, ReservationStatus reservationStatus, BigDecimal deposit,
                       LocalDateTime dateOfReservation, Agent agent, Customer customer, List<HotelBooking> hotelBookings,
                       List<FlightBooking> flightBookings, Travel travel) {
        this.reservationCode = reservationCode;
        this.adults = adults;
        this.children = children;
        this.promotions = promotions;
        this.paymentMethod = paymentMethod;
        this.reservationStatus = reservationStatus;
        this.deposit = deposit;
        this.dateOfReservation = dateOfReservation;
        this.agent = agent;
        this.customer = customer;
        this.hotelBookings = hotelBookings;
        this.flightBookings = flightBookings;
        this.travel = travel;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Promotions getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotions promotions) {
        this.promotions = promotions;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public LocalDateTime getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDateTime dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    public List<FlightBooking> getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(List<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationCode, that.reservationCode)
                && Objects.equals(adults, that.adults) && Objects.equals(children, that.children)
                && promotions == that.promotions && paymentMethod == that.paymentMethod
                && reservationStatus == that.reservationStatus && Objects.equals(deposit, that.deposit)
                && Objects.equals(dateOfReservation, that.dateOfReservation)
                && Objects.equals(agent, that.agent) && Objects.equals(customer, that.customer)
                && Objects.equals(hotelBookings, that.hotelBookings) && Objects.equals(flightBookings, that.flightBookings)
                && Objects.equals(travel, that.travel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationCode, adults, children, promotions, paymentMethod,
                reservationStatus, deposit, dateOfReservation, agent, customer,
                hotelBookings, flightBookings, travel);
    }
}
