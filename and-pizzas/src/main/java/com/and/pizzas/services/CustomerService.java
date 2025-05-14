package com.and.pizzas.services;

import com.and.pizzas.persistance.entity.Customer;
import com.and.pizzas.persistance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByPhone(String phone){
        return customerRepository.findByPhone(phone);
    }
}
