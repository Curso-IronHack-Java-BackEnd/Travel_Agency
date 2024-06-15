package com.miguelprojects.travel_agency.Models;

import com.miguelprojects.travel_agency.Enums.PaymentMethod;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private BigDecimal price;

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

    public Reservation() {    }

    public Reservation(String reservationCode, Promotions promotions, Integer adults, Integer children,
                       PaymentMethod paymentMethod, ReservationStatus reservationStatus, BigDecimal price,
                       LocalDateTime dateOfReservation, Agent agent, Customer customer, Travel travel) {
        this.reservationCode = reservationCode;
        this.promotions = promotions;
        this.adults = adults;
        this.children = children;
        this.paymentMethod = paymentMethod;
        this.reservationStatus = reservationStatus;
        this.price = price;
        this.dateOfReservation = dateOfReservation;
        this.agent = agent;
        this.customer = customer;
        this.travel = travel;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Promotions getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotions promotions) {
        this.promotions = promotions;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return Objects.equals(reservationCode, that.reservationCode) && promotions == that.promotions
                && Objects.equals(adults, that.adults) && Objects.equals(children, that.children)
                && paymentMethod == that.paymentMethod && reservationStatus == that.reservationStatus
                && Objects.equals(price, that.price) && Objects.equals(dateOfReservation, that.dateOfReservation)
                && Objects.equals(agent, that.agent) && Objects.equals(customer, that.customer)
                && Objects.equals(travel, that.travel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationCode, promotions, adults, children, paymentMethod,
                reservationStatus, price, dateOfReservation, agent, customer, travel);
    }
}
