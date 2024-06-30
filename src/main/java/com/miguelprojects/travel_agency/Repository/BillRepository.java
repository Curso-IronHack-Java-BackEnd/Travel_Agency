package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
