package com.pillar.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ENDPOINT = "/api/customer";

    private final CustomerRepository customerRespository;

    public CustomerApiController(CustomerRepository customerRepository) {
        this.customerRespository = customerRepository;
    }

    @RequestMapping(path = "", method = {RequestMethod.POST})
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
//        final String firstName = customer.firstName;
//        final String lastName = customer.lastName;
//
//        final Customer newCustomer = customerRespository.findByFirstName(firstName).orElseGet(() -> customerRespository.save(new Customer(firstName, lastName)));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(path = "/health", method = {RequestMethod.GET})
    public ResponseEntity<String> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
