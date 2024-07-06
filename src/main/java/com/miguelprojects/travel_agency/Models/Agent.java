package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name ="agents")
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

    private String specialization;

    @NotNull
    @Column(name = "commission_rate")
    @Min(value =1, message = "The commision rate must be at least 1%")
    @Max(value =15, message = "The commision rate must be a maximum of 15%")
    @Digits(integer = 4, fraction = 2, message = "Wrong Rating Format")
    private BigDecimal commissionRate = BigDecimal.ONE;

    @JsonIgnore
    @OneToMany(mappedBy = "agent")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public Agent(String firstName, String lastName, String phoneNumber, String email, Long agentId, String specialization, BigDecimal commissionRate, List<Reservation> reservations, Manager manager) {
        super(firstName, lastName, phoneNumber, email);
        this.agentId = agentId;
        this.specialization = specialization;
        this.commissionRate = commissionRate;
        this.reservations = reservations;
        this.manager = manager;
    }
//    @JsonIgnore
//    @OneToOne ()
//    private User user;
}
