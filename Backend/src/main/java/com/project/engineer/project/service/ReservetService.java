package com.project.engineer.project.service;

import com.project.engineer.project.model.Reservation;
import com.project.engineer.project.model.Reservet;
import com.project.engineer.project.repository.ReservetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class ReservetService {

    @Autowired
    ReservetRepository reservetRepository;

    @Transactional
    public void setReservation(Reservation reservation){
        for (int i = 0; i < reservation.getHowLong(); i++) {
            log.info("getting StartDate: {}", reservation.getStartData());
            LocalDate newDate = reservation.getStartData().plus(i, ChronoUnit.DAYS);
            Reservet _reservet = new Reservet(newDate, reservation.getCustomerReservation(),
                    reservation.getRoomReservation());
            log.info("Saving reservet: {}", _reservet);
            reservetRepository.save(_reservet);
        }
    }

    public ResponseEntity<Reservation> checkerReservation(Reservation reservation){
        for (int i = 0; i < reservation.getHowLong(); i++) {
            LocalDate newDate = reservation.getStartData().plus(i, ChronoUnit.DAYS);
            Reservet _reservat = reservetRepository.findByDateReservationAndRoomReservation(newDate, reservation.getRoomReservation());
            if(_reservat != null){
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }


}
