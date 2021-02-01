package com.project.engineer.project.controller;
import com.project.engineer.project.model.Responsibility;
import com.project.engineer.project.model.Worker;
import com.project.engineer.project.repository.ResponsibilityRepository;
import com.project.engineer.project.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/responsibility")
public class ResponsibilityController {

    @Autowired
    ResponsibilityRepository responsibilityRepository;

    @Autowired
    WorkerRepository workerRepository;

    @GetMapping("")
    public ResponseEntity<List<Responsibility>> getAllResponsibilities() {
        List<Responsibility> responsibilities = new ArrayList<>();
        try {
            responsibilityRepository.findAll().forEach(responsibilities::add);
            if (responsibilities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(responsibilities, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error: " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/new/{idWorker}")
    public ResponseEntity<Responsibility> postResponsibility(@PathVariable long idWorker, @RequestBody Responsibility responsibility) {
        try {
            log.info("idWorker and responsibility {} and {}", idWorker, responsibility);
            Optional<Worker> _worker = workerRepository.findById(idWorker);
            String workerdata = _worker.get().getFirstname() + " " + _worker.get().getLastname();
            Responsibility _responsibility = responsibilityRepository.save(new Responsibility(
                    responsibility.getNameResponsibility(), responsibility.getDescription(),
                    workerdata, responsibility.getStatus(),
                    idWorker));
            return new ResponseEntity<>(_responsibility, HttpStatus.CREATED);
        } catch (Exception e) {
            log.info("Error: " + e);
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{idResponsibility}")
    public ResponseEntity<HttpStatus> deleteResponsibility(@PathVariable("idResponsibility") long idResponsibility) {
        try {
            responsibilityRepository.deleteById(idResponsibility);
            log.info("Removed responsibilty with id:", idResponsibility);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


    @PutMapping("/change/{idResponsibility}")
    public ResponseEntity<Responsibility> updateResponsibility(@PathVariable("idResponsibility") long idResponsibility,
                                                               @RequestBody Responsibility responsibility) {
        Optional<Responsibility> responsibilityData = responsibilityRepository.findById(idResponsibility);
        log.info("Update responsibility with id: ", idResponsibility);
        if (responsibilityData.isPresent()) {

            Responsibility _customer = responsibilityData.get();
            _customer.setDescription(responsibility.getDescription());
            _customer.setNameResponsibility(responsibility.getNameResponsibility());
            _customer.setResponsible(responsibility.getResponsible());
            _customer.setIdworkerResponsibility(responsibility.getIdworkerResponsibility());
            _customer.setStatus(responsibility.getStatus());
            log.info("Update responsibility with: ", responsibilityData);
            return new ResponseEntity<>(responsibilityRepository.save(_customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
