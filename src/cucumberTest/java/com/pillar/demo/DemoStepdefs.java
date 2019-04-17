package com.pillar.demo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class DemoStepdefs {

    private int firstInput = 0;
    private int secondInput = 0;

    @Given("First input is one")
    public void firstInput() {
        firstInput = 1;
    }

    @When("Second input is one")
    public void secondInput() {
        secondInput = 1;
    }

    @Then("Output is two")
    public void aNewAccountIsCreated() {
        assertEquals(firstInput + secondInput , 2);
    }


}

