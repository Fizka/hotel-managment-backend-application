package com.project.engineer.project.controller;

import com.project.engineer.project.model.Customer;
import com.project.engineer.project.repository.CustomerRepository;
import com.project.engineer.project.service.CustomerServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceTest customerServiceTest;

    @Test
    void savedCustomerIsNotBlocked() {
        Customer _customerTest = new Customer("loginTest", "hasloTest", 2,"email@test.com");
        when(customerRepository.save(any(Customer.class))).then(returnsFirstArg());
        Customer savedCustomerTest = customerServiceTest.saveCustomer(_customerTest);
        assertThat(savedCustomerTest.getPrivileges()).isGreaterThan(0);
    }
}
