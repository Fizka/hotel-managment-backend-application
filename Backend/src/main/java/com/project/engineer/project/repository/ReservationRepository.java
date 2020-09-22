package com.project.engineer.project.repository;

import com.project.engineer.project.model.Customer;
import com.project.engineer.project.model.Reservation;
import com.project.engineer.project.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository <Reservation, Long> {
    List<Reservation> findByCustomerReservation(Customer customerReservation);
    List<Reservation> findByRoomReservation(Room roomReservation);
}
