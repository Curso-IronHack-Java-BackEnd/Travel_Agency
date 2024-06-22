package com.miguelprojects.travel_agency.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "extras_at_room",
            joinColumns = @JoinColumn(name = "room_extra_id", referencedColumnName = "extra_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_booking_id", referencedColumnName = "hotel_booking_id"))
    private List<HotelBooking> hotelBookings;

    public ExtraRoom() {    }

    public ExtraRoom(String name, String description, BigDecimal extraPrice, List<HotelBooking> hotelBookings) {
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

    public void setHotelBooking(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraRoom extraRoom = (ExtraRoom) o;
        return Objects.equals(extraId, extraRoom.extraId) && Objects.equals(name, extraRoom.name)
                && Objects.equals(description, extraRoom.description)
                && Objects.equals(extraPrice, extraRoom.extraPrice) && Objects.equals(hotelBookings, extraRoom.hotelBookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extraId, name, description, extraPrice, hotelBookings);
    }
}
