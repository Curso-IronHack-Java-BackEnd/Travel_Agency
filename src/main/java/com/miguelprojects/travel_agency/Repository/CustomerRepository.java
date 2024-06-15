package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
}
