package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
}
