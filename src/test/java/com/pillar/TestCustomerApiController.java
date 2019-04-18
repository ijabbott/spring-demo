package com.pillar;

import com.pillar.Customer;
import com.pillar.CustomerApiController;
import com.pillar.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@RunWith(SpringRunner.class)
public class TestCustomerApiController {
    private CustomerApiController controller;

    @Autowired
    private CustomerRepository repo;

    @Before
    public void setup() {
        repo = mock(CustomerRepository.class);
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

    @Test
    public void givenCreatingCustomerWhenGoodCustomerRequestThenReturnsCustomer() {
        Customer customer = new Customer("Awesome", "Customer");
        ResponseEntity<Customer> response = controller.create(customer);
        Customer actualCustomer = response.getBody();
        assertEquals(customer, actualCustomer);
    }

    @Test
    public void givenCreatingCustomerWhenNullThenReturnsBadRequest() {
        ResponseEntity<Customer> response = controller.create(null);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
    }

    @Test
    public void givenCreatingCustomerWhenEmptyThenReturnsBadRequest() {
        Customer customer = new Customer("", "");
        ResponseEntity<Customer> response = controller.create(customer);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
    }
}
