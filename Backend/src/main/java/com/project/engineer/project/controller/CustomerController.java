package com.project.engineer.project.controller;

import com.project.engineer.project.model.Customer;
import com.project.engineer.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllUser(){
        List<Customer> customers = new ArrayList<>();
        try {
            customerRepository.findAll().forEach(customers::add);
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }

    /*

   */
    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<Customer> getUserById(@PathVariable("idCustomer") long idCustomer) {
        Optional<Customer> userData = customerRepository.findById(idCustomer);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*

     */
    @GetMapping("/customer/login/{login}")
    public ResponseEntity<Customer> getUserBylogin(@PathVariable("login") String login) {

        Customer userData = customerRepository.findByLogin(login);

        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/new")
    public ResponseEntity<Customer> postUser(@RequestBody Customer customer) {
        try {
            Customer _customer = customerRepository.save(new Customer(customer.getFirstname(),
                    customer.getLastname(), customer.getLogin(), customer.getPassword(), customer.getPrivileges(), customer.getEmail()));
            return new ResponseEntity<>(_customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @DeleteMapping("/customer/{idCustomer}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("idCustomer") long idCustomer) {
        try {
            customerRepository.deleteById(idCustomer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            customerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }


    /*

     */
    @GetMapping(value = "customers/mail/{mail}")
    public ResponseEntity<List<Customer>> findBymail(@PathVariable String mail) {
        try {
            List<Customer> customers = customerRepository.findByEmail(mail);

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*

     */
    @PutMapping("/customer/{idCustomer}")
    public ResponseEntity<Customer> updateUser(@PathVariable("idCustomer") long idCustomer, @RequestBody Customer customer) {
        Optional<Customer> userData = customerRepository.findById(idCustomer);

        if (userData.isPresent()) {

            Customer _customer = userData.get();
            _customer.setEmail(customer.getEmail());
            _customer.setFirstname(customer.getFirstname());
            _customer.setLastname(customer.getLastname());
            _customer.setLogin(customer.getLogin());
            _customer.setPassword(customer.getPassword());
            _customer.setPrivileges(customer.getPrivileges());

            return new ResponseEntity<>(customerRepository.save(_customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
