package com.miguelprojects.travel_agency.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Table(name ="room_extras")
@DynamicUpdate
public class ExtraRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extra_id")
    private Integer extraId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private BigDecimal extraPrice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
