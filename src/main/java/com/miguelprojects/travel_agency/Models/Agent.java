package com.miguelprojects.travel_agency.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="agents")
@DynamicUpdate
public class Agent extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

    private String specialization;

    @Column(name = "commission_rate")
    @Min(value =1, message = "The commision rate must be at least 1%")
    @Max(value =15, message = "The commision rate must be a maximum of 15%")
    @Digits(integer = 4, fraction = 2, message = "Wrong Rating Format")
    private BigDecimal commissionRate;

    @JsonIgnore
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;


    // Constructores

    public Agent() {    }

    public Agent(String firstName, String lastName, String phoneNumber, String email, String specialization,
                 BigDecimal commissionRate, List<Reservation> reservations, Manager manager) {
        super(firstName, lastName, phoneNumber, email);
        this.specialization = specialization;
        this.commissionRate = commissionRate;
        this.reservations = reservations;
        this.manager = manager;
    }


    //Getters and Setters

    public Long getAgentId() {
        return agentId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(agentId, agent.agentId) && Objects.equals(specialization, agent.specialization)
                && Objects.equals(commissionRate, agent.commissionRate) && Objects.equals(reservations, agent.reservations)
                && Objects.equals(manager, agent.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId, specialization, commissionRate, reservations, manager);
    }

}
