package com.project.engineer.project.service;

import com.project.engineer.project.model.Customer;
import com.project.engineer.project.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceTest {

    private final CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        customer.setPrivileges(2);
        return customerRepository.save(customer);
    }

}
