package com.pillar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ENDPOINT = "/api/customer/";

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerApiController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        if(customer == null || (customer.firstName.isEmpty() && customer.lastName.isEmpty())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final String firstName = customer.firstName;
        final String lastName = customer.lastName;
        final Customer newCustomer = customerRepository.findByFirstName(firstName)
                .orElseGet(() -> customerRepository.save(new Customer(firstName, lastName)));

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);

    }

    @RequestMapping(path = "/{firstName}", method = {RequestMethod.GET})
    public ResponseEntity<Customer> getCustomer(@PathVariable String firstName) {
        return customerRepository.findByFirstName(firstName)
                .map((customer) -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
