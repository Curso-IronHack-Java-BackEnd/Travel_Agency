package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
