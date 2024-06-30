package com.miguelprojects.travel_agency.Controller;

import com.miguelprojects.travel_agency.DTOs.*;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import com.miguelprojects.travel_agency.Service.*;
import jakarta.validation.Valid;
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
    private BillRepository billRepository;

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
    @Autowired
    private BillService billService;

    //Obtener un agent concreto (getAgentById) (SOLO A SI MISMO)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agent getAgentById(@PathVariable(name="id") Long agentId) {
        return agentService.getAgentById(agentId);
    }

    //Crear un nuevo agent (create/post) (SOLO A SI MISMO)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Agent selfCreateAgent(@Valid @RequestBody AgentSelfCreateDTO agentDTO) {
        return agentService.selfCreateAgent(agentDTO);
    }

    //Modificar agent concreto(updateById) (SOLO A SI MISMO)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAgent(@PathVariable(name="id") Long agentId, @Valid @RequestBody AgentUpdateDTO agentDTO) {
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
    public Customer createCustomer(@Valid @RequestBody CustomerCreateDTO customerDTO) {
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
    @GetMapping("/reservations/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservationsByCustomerId(@PathVariable(name="id") Long customerId){
        return reservationService.getReservationsByCustomerId(customerId);
    }

    //Crear una nueva reservation (create/post)
    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@Valid @RequestBody ReservationCreateDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }

    //Modificar una reservation concreta(updateById)
    @PutMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReservation(@PathVariable(name="id") String reservationCode, @Valid @RequestBody ReservationUpdateDTO reservationDTO) {
        reservationService.updateReservation(reservationCode, reservationDTO);
    }

    //Eliminar reservation (deleteReservationById)
    @DeleteMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathVariable(name = "id") String reservationCode){
        reservationService.deleteReservationById(reservationCode);
    }

    //Obtener todos los travels(getAllTravels)
    @GetMapping("/travels")
    @ResponseStatus(HttpStatus.OK)
    public List<Travel> getAllTravels() {
        return travelService.getAllTravel();
    }

    //Obtener una travel concreta(getTravelById)
    @GetMapping("/travels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Travel getTravelById(@PathVariable(name="id") Long travelId){
        return travelService.getTravelById(travelId);
    }

    //Obtener una lista de travels por customerId(getTravelByCustomerId)
    @GetMapping("/travels/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Travel> getTravelsByCustomerId(@PathVariable(name="id") Long customerId){
        return travelService.getTravelsByCustomerId(customerId);
    }

    //Crear una nueva travel (create/post)
    @PostMapping("/travels")
    @ResponseStatus(HttpStatus.CREATED)
    public Travel createTravel(@Valid @RequestBody TravelCreateDTO travelDTO) {
        return travelService.createTravel(travelDTO);
    }

    //Modificar una travel concreta(updateById)
    @PutMapping("/travels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTravel(@PathVariable(name="id") Long travelId, @Valid @RequestBody TravelUpdateDTO travelDTO) {
        travelService.updateTravel(travelId, travelDTO);
    }

    //Eliminar travel (deleteTravelById)
    @DeleteMapping("/travels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTravel(@PathVariable(name = "id") Long travelId){
        travelService.deleteTravelById(travelId);
    }

    //Obtener todos los hotels(getAllHotels)
    @GetMapping("/hotels")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotel();
    }

    //Obtener todos los flights(getAllFlights)
    @GetMapping("/flights")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlights() {
        return flightService.getAllFlight();
    }


    //Obtener todos los Flights de un travel (getFlightsByTravelId)
    @GetMapping("/travels/{id}/flights")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getFlightsByTravelId(@PathVariable(name = "id") Long travelId) {
        return travelService.getFlightsByTravelId(travelId);
    }

    //Obtener todos los Hotels de un travel (getHotelsByTravelId)
    @GetMapping("/travels/{id}/hotels")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> getHotelsByTravelId(@PathVariable(name = "id") Long travelId) {
        return travelService.getHotelsByTravelId(travelId);
    }

    //Crear una nueva Bill (create/post)
    @PostMapping("/travels/{id}/bill")
    @ResponseStatus(HttpStatus.CREATED)
    public Bill createBill(@PathVariable(name = "id") Long travelId) {
        return billService.createBill(travelId);
    }

    //Crear una nueva Bill con extras(create/post)
    @PostMapping("/travels/{id}/fullBill")
    @ResponseStatus(HttpStatus.CREATED)
    public Bill createBillWithExtras(@PathVariable(name = "id") Long travelId) {
        return billService.createBill2(travelId);
    }

//    //Obtener Bill por travelId (GetBillByTravelId)
//    @GetMapping("/travels/{id}/bills")
//    @ResponseStatus(HttpStatus.OK)
//    public Bill getBillByTravelId(@PathVariable(name = "id") Long travelId) {
//        return billService.getBillByTravelId(travelId);
//    }


//    //Obtener todos los amenities de un HotelBooking(getAllAmenities)
//    @GetMapping("/hotels/{id}/amenities")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Amenity> getAllAmenities(@PathVariable(name="id") Long hotelId) {
//        Hotel hotel = hotelRepository.findById(hotelId).
//                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel with id: "+  hotelId + " not found"));
//
//        HotelBooking hotelBooking = hotel.getHotelBooking();
//        return hotelBooking.getAmenities();
//    }


}
