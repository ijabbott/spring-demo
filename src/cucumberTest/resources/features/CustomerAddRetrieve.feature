Feature: Add and retrieve customer
   Add a new customer to the database and retrieve them

  Scenario: Add a customer
    Given a customer to add firstName: "Awesome", lastName: "Person"
    When a request is made to add the customer
    Then a new customer is added to the database

  Scenario: Retrieve a customer
    Given a customer to add firstName: "Awesome", lastName: "Person"
    When a request is made to add the customer
    And a request is made to get the customer with firstName: "Awesome"
    Then the response contains "Awesome Person"
