package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.DTOs.*;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import com.miguelprojects.travel_agency.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private TravelService travelService;

    //Obtener todos los managers (getAllManagers)
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> getAllManager() {
        return managerService.getAllManager();
    }

    //Obtener un manager concreto (getManagerById)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Manager getManagerById(@PathVariable(name="id") Long managerId) {
        return managerService.getManagerById(managerId);
    }

    //Obtener su propio perfil de manager (getManagerByUsername)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Manager getManagerByUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);
        return managerService.getManagerByUsername(username);
    }

    //Crear un nuevo manager (create/post) (SOLO A SI MISMO)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Manager createManager(@RequestBody ManagerCreateDTO managerDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);
        return managerService.createManager(username, managerDTO);
    }

    //Modificar manager concreto(updateById) (SOLO A SI MISMO)
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateManager(@RequestBody ManagerUpdateDTO managerDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);
        managerService.updateManager(username, managerDTO);
    }

    //Eliminar manager (deleteManagerById) (SOLO A SI MISMO)
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteManager(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("Logged User: " + username);

        managerService.deleteManagerByUsername(username);
    }


    //Obtener todos los agents (getAllAgents)
    @GetMapping("/agents")
    @ResponseStatus(HttpStatus.OK)
    public List<Agent> getAllAgent() {
        return agentService.getAllAgent();
    }

    //Obtener un agent concreto (getAgentById)
    @GetMapping("/agents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agent getAgentById(@PathVariable(name="id") Long agentId) {
        return agentService.getAgentById(agentId);
    }

    //Crear un nuevo agent (create/post)
    @PostMapping("/agents")
    @ResponseStatus(HttpStatus.CREATED)
    public Agent createAgent(@Valid @RequestBody AgentCreateDTO agentDTO) {
        return agentService.createAgent(agentDTO);
    }

    //Modificar commissionRate de un agent concreto(patch)
    @PatchMapping("/agents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchAgentCommission(@PathVariable(name="id") Long agentId, @RequestBody AgentCommissionPatchDTO agentCommissionPatchDTO) {
        agentService.patchAgentCommission(agentId, agentCommissionPatchDTO);
    }

    //Eliminar agent (deleteAgentById)
    @DeleteMapping("/agents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAgent(@PathVariable(name = "id") Long agentId){
        agentService.deleteAgentById(agentId);
    }

    //Obtener todos los customers (getAllCustomers)
    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    //Obtener un customer concreto (getCustomerById)
    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable(name="id") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    //Obtener todos los hotels (getAllHotels)
    @GetMapping("/hotels")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> getAllHotel() {
        return hotelService.getAllHotel();
    }

    //Obtener todos los flights (getAllFlights)
    @GetMapping("/flights")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlight() {
        return flightService.getAllFlight();
    }

    //Obtener agents por specialization (getAgentsBySpecialization)
    @GetMapping("/agents/specialization")
    @ResponseStatus(HttpStatus.OK)
    public List<Agent> getAgentsBySpecialization(@RequestParam (name = "specialization")String specialization) {
        return agentService.agentsBySpecialization(specialization);
    }

    //Obtener todos los travels de un Agent(getTravelsByAgentId)
    @GetMapping("/travels/agents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Travel> getTravelsByAgentId(@PathVariable(name = "id") Long agentId) {
        return travelService.getTravelsByAgentId(agentId);
    }

    //Obtener todos los customers de un Agent(getCustomersByAgentId)
    @GetMapping("/customers/agents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomersByAgentId(@PathVariable(name = "id") Long agentId) {
        return customerService.getCustomersByAgentId(agentId);
    }

    //Crear nuevos hoteles, vuelos, habitaciones, amenities, etc (post...)

    //Modificar hoteles, vuelos, habitaciones, amenities, etc (update...)

}
