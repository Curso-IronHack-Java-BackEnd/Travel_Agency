package com.miguelprojects.travel_agency.Service;

import com.miguelprojects.travel_agency.DTOs.ReservationCreateDTO;
import com.miguelprojects.travel_agency.Enums.Promotions;
import com.miguelprojects.travel_agency.Models.*;
import com.miguelprojects.travel_agency.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private FlightBookingRepository flightBookingRepository;
    @Autowired
    private HotelBookingRepository hotelBookingRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    // Crear nueva Bill (create/post)
    public Bill createBill (Long travelId){

        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "travelId not found"));

        List <FlightBooking> flightBookings = travel.getFlightBookings();
        List <HotelBooking> hotelBookings = travel.getHotelBookings();
        BigDecimal totalFlightPrice = BigDecimal.ZERO;
        BigDecimal totalHotelPrice = BigDecimal.ZERO;



        for (FlightBooking flightBooking : flightBookings){
            totalFlightPrice = totalFlightPrice.add(flightBooking.getFlightBookingPrice());
        }

        for (HotelBooking hotelBooking : hotelBookings){
            totalHotelPrice = totalHotelPrice.add(hotelBooking.getHotelBookingPrice());
        }

        Bill newBill = new Bill();
        newBill.setTravel(travel);
        newBill.setFlightBookingPrice(totalFlightPrice);
        newBill.setHotelBookingPrice(totalHotelPrice);
        newBill.setTotalBookingPrice(totalFlightPrice.add(totalHotelPrice));


        return billRepository.save(newBill);
    }

    public Bill createFullBill (Long travelId){
        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "travelId not found"));

        Reservation reservation = travel.getReservation();

        List<HotelBooking> hotelBookings = travel.getHotelBookings();
        List <FlightBooking> flightBookings = travel.getFlightBookings();
        BigDecimal totalFlightsPrice = BigDecimal.ZERO;
        BigDecimal totalHotelsPrice = BigDecimal.ZERO;

        for (FlightBooking flightBooking : flightBookings){
            totalFlightsPrice = totalFlightsPrice.add(totalPriceFlightBooking(flightBooking));
        }
        for (HotelBooking hotelBooking : hotelBookings){
            totalHotelsPrice = totalHotelsPrice.add(totalPriceHotelBooking(hotelBooking));
        }

        Bill newBill = new Bill();
        newBill.setTravel(travel);
        newBill.setFlightBookingPrice(totalFlightsPrice);
        newBill.setHotelBookingPrice(totalHotelsPrice);

        if (reservation.getPromotions() != Promotions.NONE ){
            newBill.setDiscount(totalFlightsPrice.multiply(BigDecimal.valueOf(0.2)).add(totalHotelsPrice.multiply(BigDecimal.valueOf(0.2))));
        }
        newBill.setTotalBookingPrice((newBill.getFlightBookingPrice().add(newBill.getHotelBookingPrice()).subtract(newBill.getDiscount())));

        return billRepository.save(newBill);
    }

    // Eliminar una Bill concreta (deleteById)
    public void deleteBillById(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill with id: "+ billId + " not found"));

        bill.setTravel(null);
        billRepository.save(bill);
        billRepository.delete(bill);
    }


    public Bill getBillByTravelId(Long travelId) {
        Travel travel = travelRepository.findById(travelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id " +
                        travelId+ " not found"));

        Bill bill = new Bill();

        if(travel.getBill() == null) {
            bill = createFullBill(travelId);
        } else {
            bill = travel.getBill();
        }

        return bill;

    }

    // Funciones que calculan el precio de todos los bookings de hotels y flights
    public BigDecimal hotelBookingsPrice(Travel travel) {
        List<HotelBooking> hotelBookings = travel.getHotelBookings();

        BigDecimal totalHotelBookings = BigDecimal.ZERO;
        for (HotelBooking hotelBooking : hotelBookings) {
            totalHotelBookings = totalHotelBookings.add(totalPriceHotelBooking(hotelBooking));
        }
        return totalHotelBookings;
    }

    public BigDecimal flightBookingsPrice(Travel travel){
        List<FlightBooking> flightBookings = travel.getFlightBookings();
        BigDecimal totalFlightBookings = BigDecimal.ZERO;

        for(FlightBooking flightBooking : flightBookings){
            totalFlightBookings = totalFlightBookings.add(totalPriceFlightBooking(flightBooking));
        }
        return totalFlightBookings;
    }

    public BigDecimal totalPriceFlightBooking(FlightBooking flightBooking){
        Travel travel = flightBooking.getTravel();
        Reservation reservation = travel.getReservation();
        Flight flight = flightBooking.getFlight();
        Integer children = reservation.getChildren();
        Integer adults = reservation.getAdults();
        BigDecimal price = flight.getPrice();
        BigDecimal priceChildren = price.multiply(BigDecimal.valueOf(children));
        BigDecimal priceAdults = price.multiply(BigDecimal.valueOf(adults));
        return priceChildren.add(priceAdults);
    }

    public BigDecimal totalPriceHotelBooking(HotelBooking hotelBooking){
        Travel travel = hotelBooking.getTravel();
        Reservation reservation = travel.getReservation();
        Hotel hotel = hotelBooking.getHotel();
        BigDecimal roomExtrasTotalPrice = BigDecimal.ZERO;
        BigDecimal hotelExtrasTotalPrice = BigDecimal.ZERO;

        List<ExtraHotel> hotelExtras = hotelBooking.getHotelExtras();
        List<ExtraRoom> roomExtras = hotelBooking.getRoomExtras();

        for (ExtraHotel extraHotel : hotelExtras) {
            hotelExtrasTotalPrice = hotelExtrasTotalPrice.add(extraHotel.getExtraPrice());
        }

        for (ExtraRoom extraRoom : roomExtras) {
            roomExtrasTotalPrice = roomExtrasTotalPrice.add(extraRoom.getExtraPrice());
        }

        Integer children = reservation.getChildren();
        Integer adults = reservation.getAdults();
        BigDecimal priceChildren = hotel.getPriceChildren().multiply(BigDecimal.valueOf(children));
        BigDecimal priceAdults = hotel.getPriceAdult().multiply(BigDecimal.valueOf(adults));

        return priceChildren.add(priceAdults).add(hotelExtrasTotalPrice).add(roomExtrasTotalPrice);
    }

}
