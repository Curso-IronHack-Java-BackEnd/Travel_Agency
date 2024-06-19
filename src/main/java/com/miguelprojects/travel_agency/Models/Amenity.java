package com.miguelprojects.travel_agency.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Amenity() {    }

    public Amenity(String name, String description, Hotel hotel) {
        this.name = name;
        this.description = description;
        this.hotel = hotel;
    }

    public Long getAmenityId() {
        return amenityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenity amenity = (Amenity) o;
        return Objects.equals(amenityId, amenity.amenityId) && Objects.equals(name, amenity.name)
                && Objects.equals(description, amenity.description) && Objects.equals(hotel, amenity.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amenityId, name, description, hotel);
    }
}
