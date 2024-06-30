package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.AgentCommissionPatchDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerUpdateDTO;
import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private AgentRepository agentRepository;

    // Obtener todos los Managers (getAll)
    public List<Manager> getAllManager() {
        return managerRepository.findAll();
    }

    // Obtener un Manager concreto (getById)
    public Manager getManagerById(Long managerId) {
        Manager manager = managerRepository.findById(managerId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager with id: "+ managerId + " not found"));

        return manager;
    }

    // Eliminar un Manager concreto (deleteById)
    public void deleteManagerById(Long managerId) {
        Manager manager = managerRepository.findById(managerId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager with id: "+  managerId + " not found"));

        managerRepository.delete(manager);
    }

    // Crear nuevo Manager (create/post)
    public Manager createManager (ManagerCreateDTO managerDTO){

        Manager newManager = new Manager();
        newManager.setFirstName(managerDTO.getFirstName());
        newManager.setLastName(managerDTO.getLastName());
        newManager.setPhoneNumber(managerDTO.getPhoneNumber());
        newManager.setEmail(managerDTO.getEmail());
        newManager.setDepartment(managerDTO.getDepartment());
        newManager.setYearsOfExperience(managerDTO.getYearsOfExperience());

        return managerRepository.save(newManager);
    }

    // Modificar un Manager concreto (update/ById)
    public Manager updateManager (Long managerId, ManagerUpdateDTO managerDTO){

        Manager updatedManager = managerRepository.findById(managerId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager " + managerId +" not found"));

        if (managerDTO.getFirstName() != null){
            updatedManager.setFirstName(managerDTO.getFirstName());
        }
        if (managerDTO.getLastName() != null){
            updatedManager.setLastName(managerDTO.getLastName());
        }
        if (managerDTO.getPhoneNumber() != null){
            updatedManager.setPhoneNumber(managerDTO.getPhoneNumber());
        }
        if (managerDTO.getEmail() != null){
            updatedManager.setEmail(managerDTO.getEmail());
        }
        if (managerDTO.getDepartment() != null){
            updatedManager.setDepartment(managerDTO.getDepartment());
        }
        if (managerDTO.getYearsOfExperience() != null){
            updatedManager.setYearsOfExperience(managerDTO.getYearsOfExperience());
        }
        managerRepository.save(updatedManager);

        return updatedManager;
    }

}