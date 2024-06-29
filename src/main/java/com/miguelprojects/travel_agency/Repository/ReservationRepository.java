package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
