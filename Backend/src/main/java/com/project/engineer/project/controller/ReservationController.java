package com.project.engineer.project.controller;

import com.project.engineer.project.model.*;
import com.project.engineer.project.repository.CustomerRepository;
import com.project.engineer.project.repository.ReservationRepository;
import com.project.engineer.project.repository.ReservetRepository;
import com.project.engineer.project.repository.RoomRepository;
import com.project.engineer.project.service.ReservetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Optional;

@Slf4j
@RequestMapping("/reservation")
@RestController
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReservetRepository reservetRepository;

    @Autowired
    ReservetService reservetService;

    /*

     */
    @GetMapping()
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            reservationRepository.findAll().forEach(reservations::add);
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservet/{idRoom}")
    public ResponseEntity<List<Reservet>> getAllReservetbyIdRoom(@PathVariable long idRoom) {
        List<Reservet> reservations = new ArrayList<>();
        try {
            Optional<Room> _room = roomRepository.findById(idRoom);
            reservetRepository.findReservetByRoomReservation(_room.get()).forEach(reservations::add);
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    /*

     */
    @PostMapping(value = "/new/{idRoom}/{idCustomer}")
    public ResponseEntity<Reservation> postReservation(@PathVariable("idRoom") long idRoom, @PathVariable("idCustomer") long idCustomer,
                                                       @RequestBody Reservation reservation) {
        try {
            log.info("Creating reservation: {}", reservation);
            Optional<Room> roomData = roomRepository.findById(idRoom);
            Optional<Customer> customerData = customerRepository.findById(idCustomer);
            Reservation reservation_ = new Reservation(reservation.isStatus(), reservation.getStartData(), reservation.getEndData(), reservation.getHowLong(),
                    roomData.get(), customerData.get());
            log.info(customerData.get().getFirstname() + " " + customerData.get().getLastname());
            if (customerData.isPresent())
                reservation_.setCustomer(customerData.get().getFirstname() + " " + customerData.get().getLastname());
            log.info("Piętro: " + roomData.get().getFloor() + " Numer: " + roomData.get().getNumberRM());
            if (roomData.isPresent())
                reservation_.setRoom("Piętro: " + roomData.get().getFloor() + " Numer: " + roomData.get().getNumberRM());
            log.info("Saving reservation: {}", reservation_);
            Reservation _reservation = reservationRepository.save(reservation_);
            log.info("Saving reservet");
            reservetService.setReservation(_reservation);


            return new ResponseEntity<>(_reservation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @GetMapping("/reservation/{idReservation}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("idReservation") long idReservation) {
        Optional<Reservation> reservationData = reservationRepository.findById(idReservation);
        if (reservationData.isPresent()) {
            return new ResponseEntity<>(reservationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*

     */
    @GetMapping("/roomId/{roomId}")
    public ResponseEntity<List<Reservation>> getReservationByroomId(@PathVariable("roomId") long roomId) {

        Optional<Room> room = roomRepository.findById(roomId);
        //zmienic polaczenie na numerpokoju
        List<Reservation> reservationData = reservationRepository.findByRoomReservation(room.get());
        if (reservationData != null) {
            return new ResponseEntity<>(reservationData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*

     */
    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<List<Reservation>> getReservationBycustomerId(@PathVariable("customerId") long customerId) {

        //zmienic polaczenie na numerpokoju
        Optional<Customer> customer = customerRepository.findById(customerId);
        List<Reservation> reservationData = reservationRepository.findByCustomerReservation(customer.get());
        if (reservationData != null) {
            return new ResponseEntity<>(reservationData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*

     */
    @DeleteMapping("/reservation/{idReservation}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("idReservation") long idReservation) {
        try {
            reservationRepository.deleteById(idReservation);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*

     */
    @DeleteMapping("/reservations")
    public ResponseEntity<HttpStatus> deleteAllReservations() {
        try {
            reservationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @PutMapping("/change/{idReservation}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("idReservation") long idReservation, @RequestBody Reservation reservation) {
        Optional<Reservation> reservationData = reservationRepository.findById(idReservation);

        if (reservationData.isPresent()) {
            Reservation _reservation = reservationData.get();
            _reservation.setEndData(reservation.getEndData());
            _reservation.setStartData(reservation.getStartData());
            _reservation.setHowLong(reservation.getHowLong());
            _reservation.setStatus(reservation.isStatus());

            return new ResponseEntity<>(reservationRepository.save(_reservation), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //servis +
    @GetMapping("/reservet/{dateStart}/{idRoom}")
    public ResponseEntity<Reservet> checkOneReservation(@PathVariable("dateStart") LocalDate dateStart, @PathVariable("idRoom") long idRoom) {
        try {
            Optional<Room> roomData = roomRepository.findById(idRoom);
            Reservet reservetData = reservetRepository.findByDateReservationAndRoomReservation(dateStart, roomData.get());
            if (reservetData == null) return new ResponseEntity<>(reservetData, HttpStatus.OK);
            else return new ResponseEntity<>(null, HttpStatus.IM_USED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("reservations")
    public ResponseEntity<List<Reservet>> getAllReservationsByDays() {
        List<Reservet> reservations = new ArrayList<>();
        try {
            reservetRepository.findAll().forEach(reservations::add);
            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
