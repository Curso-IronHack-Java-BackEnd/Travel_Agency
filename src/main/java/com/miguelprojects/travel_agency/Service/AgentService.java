package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.AgentCreateDTO;
import com.miguelprojects.travel_agency.DTOs.AgentUpdateDTO;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class AgentService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ManagerRepository managerRepository;

    // Obtener todos los Agents (getAll)
    public List<Agent> getAllAgent() {
        return agentRepository.findAll();
    }

    // Obtener un Agent concreto (getById)
    public Agent getAgentById(Long agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent with id: "+ agentId + " not found"));

        return agent;
    }

    // Eliminar un Agent concreto (deleteById)
    public void deleteAgentById(Long agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent with id: "+ agentId + " not found"));
        agentRepository.delete(agent);
    }

    // Crear nuevo Agent (create/post)
    public Agent createAgent (AgentCreateDTO agentDTO){

        Manager manager = managerRepository.findById(agentDTO.getManagerId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Agent doesn´t have any assigned manager"));

        Agent newAgent = new Agent();
        newAgent.setFirstName(agentDTO.getFirstName());
        newAgent.setLastName(agentDTO.getLastName());
        newAgent.setPhoneNumber(agentDTO.getPhoneNumber());
        newAgent.setEmail(agentDTO.getEmail());
        newAgent.setSpecialization(agentDTO.getSpecialization());
        newAgent.setCommissionRate(agentDTO.getCommissionRate());
        newAgent.setManager(manager);

        return agentRepository.save(newAgent);
    }

    // Modificar un Agent concreto (update/ById)
    public Agent updateAgent (Long agentId, AgentUpdateDTO agentDTO){

        Agent updatedAgent = agentRepository.findById(agentId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent " + agentId +" not found"));

        if (agentDTO.getFirstName() != null){
            updatedAgent.setFirstName(agentDTO.getFirstName());
        }
        if (agentDTO.getLastName() != null){
            updatedAgent.setLastName(agentDTO.getLastName());
        }
        if (agentDTO.getPhoneNumber() != null){
            updatedAgent.setPhoneNumber(agentDTO.getPhoneNumber());
        }
        if (agentDTO.getEmail() != null){
            updatedAgent.setEmail(agentDTO.getEmail());
        }
        if (agentDTO.getSpecialization() != null){
            updatedAgent.setSpecialization(agentDTO.getSpecialization());
        }

        return updatedAgent;
    }












}
