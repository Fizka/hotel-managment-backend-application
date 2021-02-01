package com.project.engineer.project.repository;

import com.project.engineer.project.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByLogin(String login);

    List<Customer> findByEmail(String email);

}
