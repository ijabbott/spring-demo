Feature: Health
   Database operation

  Scenario: Add a customer
    Given a customer to add lastName: {string}, firstName: {string}
    When customer is added to database
    Then database contains customer
