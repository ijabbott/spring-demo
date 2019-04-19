package com.pillar.cucumber;

import com.pillar.Customer;
import com.pillar.CustomerApiController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class CustomerAddRetrieveStepdefs {
    private final WebClient client;

    private HttpStatus status;
    private Map response;
    private Map body;

    private Customer customer;

    public CustomerAddRetrieveStepdefs() {
        String baseUrl = System.getProperty("unit-endpoint", "https://localhost:8080");
        client = WebClient.create(baseUrl);
    }

    @Given("a customer to add firstName: {string}, lastName: {string}")
    public void aCustomerToAddLastNameFirstName(String firstName, String lastName) {
        customer = new Customer(firstName, lastName);
    }

    @When("a request is made to add the customer")
    public void aRequestIsMadeToAddACustomer() {
        requestAddCustomer();
    }

    @Then("a new customer is added to the database")
    public void aNewCustomerIsAddedToTheDatabase() {
        assertEquals(status, HttpStatus.CREATED);
        assertTrue(body.containsKey("firstName"));
        assertTrue(body.containsKey("lastName"));
    }

    @And("a request is made to get the customer with firstName: {string}")
    public void aRequestIsMadeToGetTheCustomer(String firstName) {
        response = getObjectForUrl(CustomerApiController.ENDPOINT + firstName);
    }

    @Then("the response contains {string}")
    public void theResponseContainsCustomerName(String customerName) {
        String returnedCustomerName = response.get("firstName") + " " + response.get("lastName");
        assertEquals(customerName, returnedCustomerName);
    }

    private void requestAddCustomer(){
        final HashMap<String, String> payload = new HashMap<>();
        payload.put(CustomerApiController.FIRST_NAME, customer.firstName);
        payload.put(CustomerApiController.LAST_NAME, customer.lastName);

        final ClientResponse response = client
                .post()
                .uri(CustomerApiController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(payload))
                .exchange()
                .block();

        status = response.statusCode();
        body = response.bodyToMono(Map.class).block();
    }

    private Map getObjectForUrl(String uri) {
        return client
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }






}

