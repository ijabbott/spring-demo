package com.pillar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@RunWith(SpringRunner.class)
public class TestCustomerApiController {
    private CustomerApiController controller;
    Customer customer = new Customer("Awesome", "Customer");

    @Autowired
    private CustomerRepository repo;

    @Before
    public void setup() {
        repo = mock(CustomerRepository.class);
        when(repo.findByFirstName("Awesome")).thenReturn(Optional.of(customer));
        when(repo.save(any())).thenReturn(customer);

        controller = new CustomerApiController(repo);
    }

    @Test
    public void givenCreatingCustomerWhenGoodCustomerRequestThenReturnsOk() {
        Customer customer = new Customer("Awesome", "Customer");
        ResponseEntity<Customer> response = controller.addCustomer(customer);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    public void givenCreatingCustomerWhenGoodCustomerRequestThenReturnsCustomer() {
        Customer customer = new Customer("Awesome", "Customer");
        ResponseEntity<Customer> response = controller.addCustomer(customer);
        Customer actualCustomer = response.getBody();
        assertEquals(customer.id, actualCustomer.id);
        assertEquals(customer.firstName, actualCustomer.firstName);
        assertEquals(customer.lastName, actualCustomer.lastName);

    }

    @Test
    public void givenCreatingCustomerWhenNullThenReturnsBadRequest() {
        ResponseEntity<Customer> response = controller.addCustomer(null);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
    }

    @Test
    public void givenCreatingCustomerWhenEmptyThenReturnsBadRequest() {
        Customer customer = new Customer("", "");
        ResponseEntity<Customer> response = controller.addCustomer(customer);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
    }
}
