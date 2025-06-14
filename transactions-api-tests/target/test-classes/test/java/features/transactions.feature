Feature: Transactions API
  The transactions API allows retrieval of bank transactions for a specified customer
  with options to filter the transactions in various ways.

  Scenario: Retrieve all transactions for customer 2
    Given I set the customer ID to "746c51bc-bdb9-44d2-9a3e-c4715bc91ee4"
    When I send a GET request to the transactions endpoint
    Then the response status code should be 200
    And the response should contain 5 transactions
    And transactions should be ordered by status and timestamp

  Scenario: Customer 1 should return no transactions
    Given I set the customer ID to "b3c8f5d2-4a6e-4c0b-9f7d-8f1c2e3a4b5c"
    When I send a GET request to the transactions endpoint
    Then the response status code should be 200
    And the response should contain no transactions

  Scenario: Filter transactions by category
    Given I set the customer ID to "746c51bc-bdb9-44d2-9a3e-c4715bc91ee4"
    And I set the following query parameters:
      | categoryId | 11 |
    When I send a GET request to the transactions endpoint
    Then the response status code should be 200
    And the response should contain 1 transactions
    And all transactions should have category ID 11

  Scenario: Exclude pending transactions
    Given I set the customer ID to "746c51bc-bdb9-44d2-9a3e-c4715bc91ee4"
    And I set the following query parameters:
      | includePending | false |
    When I send a GET request to the transactions endpoint
    Then the response status code should be 200
    And the response should contain 4 transactions
    And all transactions should have status "Booked"

  Scenario: Filter by date range
    Given I set the customer ID to "746c51bc-bdb9-44d2-9a3e-c4715bc91ee4"
    And I set the following query parameters:
      | fromDate | 2025-06-03 |
      | toDate   | 2025-06-05 |
    When I send a GET request to the transactions endpoint
    Then the response status code should be 200
    And the response should contain 4 transactions
    And all transactions should be between "2025-06-03" and "2025-06-05"

  Scenario: Invalid customer ID format
    Given I set the customer ID to "invalid-id"
    When I send a GET request to the transactions endpoint
    Then the response status code should be 400
    And the response should contain an error message "Invalid customerId guid format"

  Scenario: Missing customer ID
    Given I send a GET request to the transactions endpoint
    Then the response status code should be 400
    And the response should contain an error message "customerId is required"