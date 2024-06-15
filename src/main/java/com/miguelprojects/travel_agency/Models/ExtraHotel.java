package com.miguelprojects.travel_agency.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name ="hotel_extras")
@DynamicUpdate
public class ExtraHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extra_id")
    private Integer extraId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private Double extraPrice;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
