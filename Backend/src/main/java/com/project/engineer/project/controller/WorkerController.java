package com.project.engineer.project.controller;

import com.project.engineer.project.model.Worker;
import com.project.engineer.project.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerRepository workerRepository;

    /*
    Wyswietlanie wszystkich pracownikow
     */
    @GetMapping()
    public ResponseEntity<List<Worker>> getAllWorkers(){
        List<Worker> workers = new ArrayList<>();
        try{
            workerRepository.findAll().forEach(workers::add);
            if(workers.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(workers, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    Dodawanie nowego pracownika
     */
    @PostMapping(value = "/new")
    public ResponseEntity<Worker> postWorker(@RequestBody Worker worker) {
        try{
                Worker worker_ = new Worker( worker.getFirstname(), worker.getLastname(), worker.getLogin(), worker.getPassword(), worker.getEmail(), worker.getResponsibilities(),
                        worker.getPosition(), worker.getDateofemployment(), worker.getDateofbirth(), worker.getSalary(), worker.getActivity(),
                        worker.getPesel(), worker.getWorkinghours(), worker.getPrivileges());
            Worker _worker = workerRepository.save(worker_);
            return new ResponseEntity<>(_worker, HttpStatus.CREATED);
            }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    Pobranie danych pracownika
     */
    @GetMapping("/worker/{idWorker}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable("idWorker") long idWorker ) {
        Optional<Worker> workerData = workerRepository.findById(idWorker);
        if(workerData.isPresent()){
            return new ResponseEntity<>(workerData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    Pobranie pracownika przez login
     */
    @GetMapping("/login/{login}")
    public ResponseEntity<Worker> getWorkerByLogin(@PathVariable("login") String login ){
        Worker workerData = workerRepository.findByLogin(login);
        if(workerData != null){
            return new ResponseEntity<>(workerData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*
    Usuwanie użytkownika
     */
    @DeleteMapping("/{idWorker}")
    public ResponseEntity<HttpStatus> deleteWorker(@PathVariable("idWorker") long idWorker){
        try {
            workerRepository.deleteById(idWorker);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*
    Usuwanie wszystkich użytkownikow
     */
    @DeleteMapping("/workers")
    public ResponseEntity<HttpStatus> deleteAllWorkers(){
        try{
            workerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    Zmiana danych uzytkownika
     */
    @PutMapping("/change/{idWorker}")
    public ResponseEntity<Worker> updateWoker(@PathVariable("idWorker") long idWorker, @RequestBody Worker worker){
        Optional<Worker> workerData = workerRepository.findById(idWorker);
        if(workerData.isPresent()){
            Worker _worker = workerData.get();
            _worker.setActivity(worker.getActivity());
            _worker.setDateofbirth(worker.getDateofbirth());
            _worker.setDateofemployment(worker.getDateofemployment());
            _worker.setEmail(worker.getEmail());
            _worker.setFirstname(worker.getFirstname());
            _worker.setLastname(worker.getLastname());
            _worker.setLogin(worker.getLogin());
            _worker.setPassword(worker.getPassword());
            _worker.setPesel(worker.getPesel());
            _worker.setPosition(worker.getPosition());
            _worker.setPrivileges(worker.getPrivileges());
            _worker.setResponsibilities(worker.getResponsibilities());
            _worker.setSalary(worker.getSalary());
            _worker.setWorkinghours(worker.getWorkinghours());
            return new ResponseEntity<>(workerRepository.save(_worker), HttpStatus.OK);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
