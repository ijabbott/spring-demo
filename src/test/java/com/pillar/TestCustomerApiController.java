package com.pillar;

import com.pillar.demo.Customer;
import com.pillar.demo.CustomerApiController;
import com.pillar.demo.CustomerRepository;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TestCustomerApiController {

    @Test
    public void givenCreatingCustomerWhenGoodCustomerRequestThenReturnsOk(){
        Customer customer = new Customer("Awesome", "Customer");
        CustomerRepository repo = mock(CustomerRepository.class);
        CustomerApiController controller = new CustomerApiController(repo);
        ResponseEntity<Customer> response = controller.create(customer);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    public void healthEndpointReturnsOk(){
        CustomerRepository repo = mock(CustomerRepository.class);
        CustomerApiController controller = new CustomerApiController(repo);
        ResponseEntity<String> response = controller.health();
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(HttpStatus.OK, statusCode);
    }
}
