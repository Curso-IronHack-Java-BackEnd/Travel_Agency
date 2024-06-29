package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.DTOs.AgentCreateDTO;
import com.miguelprojects.travel_agency.DTOs.AgentUpdateDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.ManagerUpdateDTO;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import com.miguelprojects.travel_agency.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private CustomerService customerService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private FlightService flightService;


    //Obtener todos los managers (getAllManagers)
    @GetMapping
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

    //Crear un nuevo manager (create/post) (SOLO A SI MISMO)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Manager createManager(@RequestBody ManagerCreateDTO managerDTO) {
        return managerService.createManager(managerDTO);
    }

    //Modificar manager concreto(updateById) (SOLO A SI MISMO)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateManager(@PathVariable(name="id") Long managerId, @RequestBody ManagerUpdateDTO managerDTO) {
        managerService.updateManager(managerId, managerDTO);
    }

    //Eliminar manager (deleteManagerById) (SOLO A SI MISMO)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteManager(@PathVariable(name = "id") Long managerId){
        managerService.deleteManagerById(managerId);
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
    public Agent createAgent(@RequestBody AgentCreateDTO agentDTO) {
        return agentService.createAgent(agentDTO);
    }

    //Modificar agent concreto(updateById)
    @PutMapping("/agents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAgent(@PathVariable(name="id") Long agentId, @RequestBody AgentUpdateDTO agentDTO) {
        agentService.updateAgent(agentId, agentDTO);
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







    //Obtener todos los customers(getAllCustomers)

    //Obtener todas las reservations(getAllReservations)

    //Obtener todas las agents(getAllAgents)

    //Obtener agents por specialization (getAgentsBySpecialization)

    //Crear su perfil(postManager)

    //Modificar su propio perfil(updateManagerById)

    //Crear nuevo Agent(postAgent)

    //Eliminar Agent(deleteAgentById)

    //Modificar comissionRate del agent(patchCommisionAgentById)

    //Obtener todos los hoteles, vuelos, habitaciones, amenities, etc (getAll...)

    //Crear nuevos hoteles, vuelos, habitaciones, amenities, etc (post...)

    //Modificar hoteles, vuelos, habitaciones, amenities, etc (update...)

}
