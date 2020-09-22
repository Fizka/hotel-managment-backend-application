package com.project.engineer.project.controller;

import com.project.engineer.project.model.Customer;
import com.project.engineer.project.model.Reservation;
import com.project.engineer.project.model.Room;
import com.project.engineer.project.model.Worker;
import com.project.engineer.project.repository.CustomerRepository;
import com.project.engineer.project.repository.ReservationRepository;
import com.project.engineer.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/reservation")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    CustomerRepository customerRepository;

    /*

    */
    @GetMapping()
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations = new ArrayList<>();
        try{
            reservationRepository.findAll().forEach(reservations::add);
            if(reservations.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*

     */
    @PostMapping(value = "/new/{idRoom}/{idCustomer}")
    public ResponseEntity<Reservation> postReservation(@PathVariable("idRoom") long idRoom,@PathVariable("idCustomer") long idCustomer, @RequestBody Reservation reservation) {
        try{

            Optional <Room> roomData = roomRepository.findById(idRoom);
            Optional <Customer> customerData = customerRepository.findById(idCustomer);
            Reservation reservation_ = new Reservation( reservation.getStartData(), reservation.getEndData());
            reservation_.setRoomReservation(roomData.get());
            reservation_.setCustomerReservation(customerData.get());

            Reservation _reservation = reservationRepository.save(reservation_);

            return new ResponseEntity<>(_reservation, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @GetMapping("/reservation/{idReservation}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("idReservation") long idReservation ) {
        Optional<Reservation> reservationData = reservationRepository.findById(idReservation);
        if(reservationData.isPresent()){
            return new ResponseEntity<>(reservationData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*

     */
    @GetMapping("/roomId/{roomId}")
    public ResponseEntity< List<Reservation>> getReservationByroomId(@PathVariable("roomId") long roomId ){

        Optional<Room> room = roomRepository.findById(roomId);
        //zmienic polaczenie na numerpokoju
        List<Reservation> reservationData = reservationRepository.findByRoomReservation(room.get());
        if(reservationData != null){
            return new ResponseEntity<>(reservationData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*

     */
    @GetMapping("/customerId/{customerId}")
    public ResponseEntity< List<Reservation>> getReservationBycustomerId(@PathVariable("customerId") long customerId ){

        //zmienic polaczenie na numerpokoju
        Optional<Customer> customer = customerRepository.findById(customerId);
        List<Reservation> reservationData = reservationRepository.findByCustomerReservation(customer.get());
        if(reservationData != null){
            return new ResponseEntity<>(reservationData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*

     */
    @DeleteMapping("/reservation/{idReservation}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("idReservation") long idReservation){
        try {
            reservationRepository.deleteById(idReservation);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*

     */
    @DeleteMapping("/reservations")
    public ResponseEntity<HttpStatus> deleteAllReservations(){
        try{
            reservationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @PutMapping("/change/{idReservation}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("idReservation") long idReservation, @RequestBody Reservation reservation){
        Optional<Reservation> reservationData = reservationRepository.findById(idReservation);

        if(reservationData.isPresent()){
            Reservation _reservation = reservationData.get();
            _reservation.setEndData(reservation.getEndData());
            _reservation.setStartData(reservation.getStartData());

            return new ResponseEntity<>(reservationRepository.save(_reservation), HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
