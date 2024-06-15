package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
