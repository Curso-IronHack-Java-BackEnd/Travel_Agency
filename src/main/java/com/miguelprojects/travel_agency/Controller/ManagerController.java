package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.Repository.CustomerRepository;
import com.miguelprojects.travel_agency.Repository.ReservationRepository;
import com.miguelprojects.travel_agency.Repository.TravelRepository;
import com.miguelprojects.travel_agency.Service.CustomerService;
import com.miguelprojects.travel_agency.Service.ReservationService;
import com.miguelprojects.travel_agency.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

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
