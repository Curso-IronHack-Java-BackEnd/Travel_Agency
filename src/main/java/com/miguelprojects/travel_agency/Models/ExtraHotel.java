package com.miguelprojects.travel_agency.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Objects;

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

    private BigDecimal extraPrice;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public ExtraHotel() {    }

    public ExtraHotel(String name, String description, BigDecimal extraPrice, Hotel hotel) {
        this.name = name;
        this.description = description;
        this.extraPrice = extraPrice;
        this.hotel = hotel;
    }

    public Integer getExtraId() {
        return extraId;
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
        ExtraHotel that = (ExtraHotel) o;
        return Objects.equals(extraId, that.extraId) && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(extraPrice, that.extraPrice) && Objects.equals(hotel, that.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extraId, name, description, extraPrice, hotel);
    }
}
