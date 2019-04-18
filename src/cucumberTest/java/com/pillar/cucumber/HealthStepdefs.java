package com.pillar.cucumber;

import com.pillar.Customer;
import com.pillar.CustomerApiController;
import com.pillar.CustomerRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;


public class HealthStepdefs {
    private final WebClient client;

    @Autowired
    CustomerRepository repo;

    private Customer customerOne;

    private HttpStatus status;
    private Map body;

    public HealthStepdefs() {
        final String endpoint = System.getProperty("unit-endpoint", "http://localhost:8080");
        client = WebClient.create(endpoint);
    }


    @Given("a customer to add lastName: \\{string}, firstName: \\{string}")
    public void aCustomerToAddLastNameFirstName() {
        customerOne = new Customer("Awesome", "Customer");
        customerOne.id = "CustomerOne";
    }

    @When("customer is added to database")
    public void addCustomer() {
        requestAddCustomer("Awesome", "Customer");
    }

    @Then("database contains customer")
    public void getCustomer() {
        assertTrue(body.containsKey(Customer.firstName));
    }

    private void requestAddCustomer(String customerFirstName, String customerLastName){
        final HashMap<String, String> payload = new HashMap<>();
        payload.put(CustomerApiController.FIRST_NAME, customerFirstName);
        payload.put(CustomerApiController.LAST_NAME, customerLastName);


        final ClientResponse response = client
                .post()
                .uri(CustomerApiController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(payload))
                .exchange()
                .block();

        status = response.statusCode();
        body = response.bodyToMono(Map.class).block();
        System.out.println(body);
    }

}

