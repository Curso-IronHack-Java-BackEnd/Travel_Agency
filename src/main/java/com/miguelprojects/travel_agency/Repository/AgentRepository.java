package com.miguelprojects.travel_agency.Repository;

import com.miguelprojects.travel_agency.Models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findBySpecialization(String specialization);
}
