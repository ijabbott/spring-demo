package com.pillar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
        if(customer == null || (customer.firstName.isEmpty() && customer.lastName.isEmpty())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customer, HttpStatus.CREATED);

    }

    @RequestMapping(path = "/health", method = {RequestMethod.GET})
    public ResponseEntity<String> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
