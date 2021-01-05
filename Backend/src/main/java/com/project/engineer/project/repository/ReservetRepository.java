package com.project.engineer.project.repository;

import com.project.engineer.project.model.Reservet;
import com.project.engineer.project.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservetRepository extends CrudRepository<Reservet, Long> {
    Reservet findByDateReservationAndRoomReservation (LocalDate DateReservation, Room RoomReservation );
    List<Reservet> findReservetByRoomReservation (Room roomReservation);

}
