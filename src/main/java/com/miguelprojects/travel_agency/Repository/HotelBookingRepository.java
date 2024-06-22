package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
}
