package com.miguelprojects.travel_agency.Controller;

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


    //Obtener todos los customers (getAllCustomers)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return agentService.getAllCustomer();
    }

    //Obtener un customer concreto (getCustomerById)
    @GetMapping("{/id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable(name="id") Long customerId) {
        return agentService.getCustomerById(customerId);
    }

    //Eliminar customer (deleteCustomerById)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable(name = "id") Long customerId){
        agentService.deleteCustomerById(customerId);
    }

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
