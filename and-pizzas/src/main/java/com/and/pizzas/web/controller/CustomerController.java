package com.and.pizzas.web.controller;

import com.and.pizzas.persistance.entity.Customer;
import com.and.pizzas.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("phone/{phone}")
    public ResponseEntity<Customer> getByPhone(@PathVariable(name = "phone") String phone){
        return ResponseEntity.ok(customerService.findByPhone(phone));

    }

}
