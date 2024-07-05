package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.AgentCommissionPatchDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerUpdateDTO;
import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.User;
import com.miguelprojects.travel_agency.Repository.AgentRepository;
import com.miguelprojects.travel_agency.Repository.ManagerRepository;
import com.miguelprojects.travel_agency.Repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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

    // Obtener su propio manager (getByUsername) (MANAGER)
    public Manager getManagerByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return user.getManager();
    }

    // Eliminar su propio Manager (deleteByUsername)
    public void deleteManagerByUsername(String username) {
        User user = userRepository.findByUsername(username);
        Manager manager = user.getManager();

        for (Agent agent : manager.getAgents()) {
            agent.setManager(null);
            agentRepository.save(agent);
        }
        managerRepository.delete(manager);
    }

    // Crear nuevo Manager (create/post)
    public Manager createManager (String username, ManagerCreateDTO managerDTO){
        User user = userRepository.findByUsername(username);

        if (user.getManager() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Manager " + username +" has already been created");
        } else {
            Manager newManager = new Manager();
            newManager.setFirstName(managerDTO.getFirstName());
            newManager.setLastName(managerDTO.getLastName());
            newManager.setPhoneNumber(managerDTO.getPhoneNumber());
            newManager.setEmail(managerDTO.getEmail());
            newManager.setDepartment(managerDTO.getDepartment());
            newManager.setYearsOfExperience(managerDTO.getYearsOfExperience());
            managerRepository.save(newManager);
            user.setManager(newManager);
            userRepository.save(user);
            return newManager;
        }
    }

    // Modificar un Manager concreto (update/ById)
    public Manager updateManager (String username, ManagerUpdateDTO managerDTO){
        User user = userRepository.findByUsername(username);
        Manager updatedManager = user.getManager();

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
