package com.miguelprojects.travel_agency.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name ="amenities")
@DynamicUpdate
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_id")
    private Long amenityId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private Double extraPrice;

    @NotBlank(message = "Target Audience is mandatory")
    private Integer targetAudience;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
