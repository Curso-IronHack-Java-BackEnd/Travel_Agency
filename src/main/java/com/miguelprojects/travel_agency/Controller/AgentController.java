package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.DTOs.AgentCreateDTO;
import com.miguelprojects.travel_agency.DTOs.AgentUpdateDTO;
import com.miguelprojects.travel_agency.DTOs.CustomerCreateDTO;
import com.miguelprojects.travel_agency.DTOs.CustomerUpdateDTO;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import com.miguelprojects.travel_agency.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private HotelRepository hotelRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TravelService travelService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private FlightService flightService;

    //Obtener un agent concreto (getAgentById) (SOLO A SI MISMO)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agent getAgentById(@PathVariable(name="id") Long agentId) {
        return agentService.getAgentById(agentId);
    }

    //Crear un nuevo agent (create/post) (SOLO A SI MISMO)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Agent createAgent(@RequestBody AgentCreateDTO agentDTO) {
        return agentService.createAgent(agentDTO);
    }

    //Modificar agent concreto(updateById) (SOLO A SI MISMO)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAgent(@PathVariable(name="id") Long agentId, @RequestBody AgentUpdateDTO agentDTO) {
        agentService.updateAgent(agentId, agentDTO);
    }

    //Obtener todos los customers(getAllCustomers)
    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    //Obtener un customer concreto (getCustomerById)
    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable(name="id") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    //Crear un nuevo customer (create/post)
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody CustomerCreateDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    //Eliminar customer (deleteCustomerById)
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable(name = "id") Long customerId){
        customerService.deleteCustomerById(customerId);
    }


    //Obtener todas las reservations(getAllReservations)
    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservation();
    }

    //Obtener una reservation concreta(getReservationById)
    @GetMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservationById(@PathVariable(name="id") String reservationCode){
        return reservationService.getReservationById(reservationCode);
    }

    //Obtener una lista de reservations por customerId(getReservationByCustomerId)
    @GetMapping("/reservations/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservationsByCustomerId(@PathVariable(name="id") Long customerId){
        return reservationService.getReservationsByCustomerId(customerId);
    }

    //Obtener todos los hotels(getAllHotels)
    @GetMapping("/hotels")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotel();
    }

    //Obtener todos los flights(getAllFlights)
    @GetMapping("/flight")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlights() {
        return flightService.getAllFlight();
    }

    //Obtener todos los amenities(getAllAmenities)
    @GetMapping("/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.OK)
    public List<Amenity> getAllAmenities(@PathVariable(name="id") Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel with id: "+  hotelId + " not found"));

        HotelBooking hotelBooking = hotel.getHotelBooking();
        return hotelBooking.getAmenities();
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
