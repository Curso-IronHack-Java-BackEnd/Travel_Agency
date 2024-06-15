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

    private BigDecimal extraPrice;

    @NotBlank(message = "Target Audience is mandatory")
    private Integer targetAudience;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Amenity() {    }

    public Amenity(Long amenityId, String name, String description, BigDecimal extraPrice, Integer targetAudience, Hotel hotel) {
        this.amenityId = amenityId;
        this.name = name;
        this.description = description;
        this.extraPrice = extraPrice;
        this.targetAudience = targetAudience;
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

    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Integer getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(Integer targetAudience) {
        this.targetAudience = targetAudience;
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
                && Objects.equals(description, amenity.description) && Objects.equals(extraPrice, amenity.extraPrice)
                && Objects.equals(targetAudience, amenity.targetAudience) && Objects.equals(hotel, amenity.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amenityId, name, description, extraPrice, targetAudience, hotel);
    }
}
