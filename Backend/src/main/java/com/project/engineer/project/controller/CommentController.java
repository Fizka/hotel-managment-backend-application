package com.project.engineer.project.controller;

import com.project.engineer.project.model.Comment;
import com.project.engineer.project.model.Customer;
import com.project.engineer.project.model.Room;
import com.project.engineer.project.repository.CommentRepository;
import com.project.engineer.project.repository.CustomerRepository;
import com.project.engineer.project.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping()
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comment = new ArrayList<>();
        try {
            commentRepository.findAll().forEach(comment::add);
            if (comment.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{idRoom}")
    public ResponseEntity<List<Comment>> getAllReservetbyIdRoom(@PathVariable long idRoom) {
        List<Comment> comment = new ArrayList<>();
        try {
            Optional<Room> _room = roomRepository.findById(idRoom);
            commentRepository.findCommentsByRoomComment(_room.get()).forEach(comment::add);
            if (comment.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/new/{idRoom}/{idCustomer}")
    public ResponseEntity<Comment> postReservation(@PathVariable("idRoom") long idRoom, @PathVariable("idCustomer") long idCustomer,
                                                   @RequestBody Comment comment) {
        try {
            log.info("Creating reservation: {}", comment);
            Optional<Room> roomData = roomRepository.findById(idRoom);
            Optional<Customer> customerData = customerRepository.findById(idCustomer);
            commentRepository.save(new Comment(comment.getMark(), comment.getTextValue(), roomData.get(), customerData.get()));
            return new ResponseEntity<>(commentRepository.save(new Comment(comment.getMark(), comment.getTextValue(),
                    roomData.get(), customerData.get())), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
