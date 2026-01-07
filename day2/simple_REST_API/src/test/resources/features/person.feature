Feature: Rest API

  Scenario: Retrive people by their id
    Given the system is running
    When i hit the endpoint student id "1"
    Then it should return student with id "1"


  Scenario: Update a student and verify
    Given the system is running
    When I update the student with id "1" with name "Alice" and address "Italy"
    Then retrieving the student with id "1" should return name "Alice" and address "Italy"


  Scenario: Create and update a person
    Given the system is running
    When i create a person with name "Bob" and country "Sweden"
    And i update the person with id "1"
    Then retrieving the person with id "1" should return name "Karlos" and country "Bulgaria"
