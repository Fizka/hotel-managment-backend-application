package com.project.engineer.project.controller;

import com.project.engineer.project.model.Room;
import com.project.engineer.project.model.Worker;
import com.project.engineer.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/room")
@RestController
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    /*

         */
    @GetMapping()
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        try{
            roomRepository.findAll().forEach(rooms::add);
            if(rooms.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*

     */
    @PostMapping(value = "/new")
    public ResponseEntity<Room> postRoom(@RequestBody Room room) {
        try{
            int random= (int)Math.floor(Math.random() *83);

            Room room_ = new Room(room.getUrlImages(), room.getFloor(),room.getNumberRM(),  room.getPrice(), room.getDescription(), room.getMaxCapacity(), room.getTitle());
            Room _room = roomRepository.save(room_);
            return new ResponseEntity<>(_room, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @GetMapping("/room/{idRoom}")
    public ResponseEntity<Room> getRoomById(@PathVariable("idRoom") long idRoom ) {
        Optional<Room> roomData = roomRepository.findById(idRoom);
        if(roomData.isPresent()){
            return new ResponseEntity<>(roomData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*

     */
    @DeleteMapping("/{idRoom}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable("idRoom") long idRoom){
        try {
            roomRepository.deleteById(idRoom);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*

     */
    @DeleteMapping("/rooms")
    public ResponseEntity<HttpStatus> deleteAllRooms(){
        try{
            roomRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @PutMapping("/change/{idRoom}")
    public ResponseEntity<Room> updateRoom(@PathVariable("idRoom") long idRoom, @RequestBody Room room){
        Optional<Room> roomData = roomRepository.findById(idRoom);

        if(roomData.isPresent()){
            Room _room = roomData.get();
            _room.setDescription(room.getDescription());
            _room.setFloor(room.getFloor());
            _room.setMaxCapacity(room.getMaxCapacity());
            _room.setNumberRM(room.getNumberRM());
            _room.setPrice(room.getPrice());
            _room.setTitle(room.getTitle());
            return new ResponseEntity<>(roomRepository.save(_room), HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
