package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.Models.Agent;
import com.miguelprojects.travel_agency.Models.Customer;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.TravelRepository;
import com.miguelprojects.travel_agency.Service.AgentService;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.ReservationService;
import com.miguelprojects.travel_agency.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TravelService travelService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private AgentService agentService;


    //Obtener todos los agents (getAllAgents)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Agent> getAllAgent() {
        return agentService.getAllAgent();
    }

    //Obtener un agent concreto (getAgentById)
    @GetMapping("{/id}")
    @ResponseStatus(HttpStatus.OK)
    public Agent getAgentById(@PathVariable(name="id") Long agentId) {
        return agentService.getAgentById(agentId);
    }

    //Eliminar agent (deleteAgentById)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAgent(@PathVariable(name = "id") Long agentId){
        agentService.deleteAgentById(agentId);
    }

    //Crea un nuevo agent (createAgent
    // )







    //Obtener todas las reservations(getAllReservations)
    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservations() {
        return agentService.getAllReservation();
    }

    //Obtener una reservation concreta(getReservationById)
    @GetMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservationById(@PathVariable(name="id") String reservationCode){
        return agentService.getReservationById(reservationCode);
    }

    //Obtener una lista de reservations por customerId(getReservationByCustomerId)
    @GetMapping("/reservations/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservationsByCustomerId(@PathVariable(name="id") Long customerId){
        return agentService.getReservationsByCustomerId(customerId);
    }




    //Crear nueva reservation (postReservation)


    //Modificar una reservation(updateReservation) ***********

    //Eliminar una reservation(deleteReservation)

    //Modificar su propio perfil(updateAgentById) ***********

    //Obtener todos los travels(getAllTravels)


    //Obtener un travel concreta(getTravelById)


    //Crear nuevo travel (postTravel)


    //Modificar un travel(updateTravel) ************

    //Eliminar un travel(deleteTravel)



}
