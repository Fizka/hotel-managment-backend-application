package com.project.engineer.project.repository;

import com.project.engineer.project.model.Reservation;
import com.project.engineer.project.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository < Room , Long> {
}
