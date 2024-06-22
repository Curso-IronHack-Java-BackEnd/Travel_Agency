package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "extras_at_hotel",
            joinColumns = @JoinColumn(name = "hotel_extra_id", referencedColumnName = "extra_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_booking_id", referencedColumnName = "hotel_booking_id"))
    private List<HotelBooking> hotelBookings;

    public ExtraHotel() {    }

    public ExtraHotel(String name, String description, BigDecimal extraPrice, List<HotelBooking> hotelBookings) {
        this.name = name;
        this.description = description;
        this.extraPrice = extraPrice;
        this.hotelBookings = hotelBookings;
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

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraHotel that = (ExtraHotel) o;
        return Objects.equals(extraId, that.extraId) && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(extraPrice, that.extraPrice) && Objects.equals(hotelBookings, that.hotelBookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extraId, name, description, extraPrice, hotelBookings);
    }
}
