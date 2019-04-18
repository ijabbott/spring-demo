package com.pillar;

import com.pillar.demo.Customer;
import com.pillar.demo.CustomerApiController;
import com.pillar.demo.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TestCustomerApiController {
    private CustomerApiController controller;

    @Before
    public void setup() {
        CustomerRepository repo = mock(CustomerRepository.class);
        controller = new CustomerApiController(repo);
    }

    @Test
    public void givenCreatingCustomerWhenGoodCustomerRequestThenReturnsOk() {
        Customer customer = new Customer("Awesome", "Customer");
        ResponseEntity<Customer> response = controller.create(customer);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    public void healthEndpointReturnsOk() {
        ResponseEntity<String> response = controller.health();
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.OK, statusCode);
    }
}
