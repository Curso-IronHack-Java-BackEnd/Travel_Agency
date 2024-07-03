package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.TravelCreateDTO;
import com.miguelprojects.travel_agency.DTOs.TravelUpdateDTO;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private HotelBookingRepository hotelBookingRepository;


    // Obtener todos los Travels (getAll)
    public List<Travel> getAllTravel() {
        return travelRepository.findAll();
    }

    // Obtener un Travel concreto (getById)
    public Travel getTravelById(Long travelId) {
        Travel travel = travelRepository.findById(travelId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id: "+ travelId + " not found"));

        return travel;
    }

    // Eliminar un Travel concreto (deleteById) (EL TRAVEL NO SE DEBE BORRAR, SE BORRA AL ELIMINAR LA RESERVATION)
    public void deleteTravelById(Long travelId) {
        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id: "+  travelId + " not found"));

        travel.setCustomer(null);
        travel.setBill(null);
        travel.setReservation(null);

        for (HotelBooking hotelbooking : travel.getHotelBookings()) {
            hotelbooking.getAmenities().clear();
            hotelbooking.getHotelExtras().clear();
            hotelbooking.getRoomExtras().clear();
            hotelBookingRepository.save(hotelbooking);
        }
        travel.getHotelBookings().clear();
        travel.getFlightBookings().clear();
        travelRepository.save(travel);

        travelRepository.delete(travel);
    }

    // Crear nuevo Travel (create/post)
    public Travel createTravel (TravelCreateDTO travelDTO){

        Reservation reservation = reservationRepository.findById(travelDTO.getReservationCode()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Travel does not have any assigned reservation"));

        Customer customer = customerRepository.findById(travelDTO.getCustomerId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Travel does not have any assigned customer"));

        Travel newTravel = new Travel();
        newTravel.setDestination(travelDTO.getDestination());
        newTravel.setDuration(travelDTO.getDuration());
        newTravel.setFinalPrice(travelDTO.getFinalPrice());
        newTravel.setReservation(reservation);
        newTravel.setCustomer(customer);

        return travelRepository.save(newTravel);
    }

    // Modificar un Travel concreto (update/ById)
    public Travel updateTravel (Long travelId, TravelUpdateDTO travelDTO){

        Travel updatedTravel = travelRepository.findById(travelId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel " + travelId +" not found"));

        if (travelDTO.getDestination() != null){
            updatedTravel.setDestination(travelDTO.getDestination());
        }
        if (travelDTO.getDuration() != null){
            updatedTravel.setDuration(travelDTO.getDuration());
        }
        if (travelDTO.getFinalPrice() != null){
            updatedTravel.setFinalPrice(travelDTO.getFinalPrice());
        }
        travelRepository.save(updatedTravel);

        return updatedTravel;
    }

    // Obtener todos los travels de un customer (getTravelByCustomerId)
    public List <Travel> getTravelsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " +
                        customerId+ " not found"));

        List<Travel> travels = customer.getTravels();

        return travels;
    }

    // Obtener todos los Flights de un Travel (getFlightsByTravelId)
    public List<Flight> getFlightsByTravelId(Long travelId){

        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id " +
                        travelId+ " not found"));

        List<FlightBooking> flightBookings = travel.getFlightBookings();
        List<Flight> flights = new ArrayList<>();

        for (FlightBooking flightBooking : flightBookings){
            flights.add(flightBooking.getFlight());
        }

        return flights;
    }

    //Obtener todos los travels de un Agent(getTravelsByAgentId)
    public List<Travel> getTravelsByAgentId(Long agentId) {
        Agent agent = agentRepository.findById(agentId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent with id " +
                        agentId+ " not found"));

        List<Travel> travels = new ArrayList<>();

        List<Reservation> reservations = agent.getReservations();
        for (Reservation reservation : reservations){
            travels.add(reservation.getTravel());
        }
        return travels;
    }



    // Obtener todos los Hotels de un Travel (getHotelsByTravelId)
    public List<Hotel> getHotelsByTravelId(Long travelId){

        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id " +
                        travelId+ " not found"));

        List<HotelBooking> hotelBookings = travel.getHotelBookings();
        List<Hotel> hotels = new ArrayList<>();

        for (HotelBooking hotelBooking : hotelBookings){
            hotels.add(hotelBooking.getHotel());
        }
        return hotels;
    }

}
