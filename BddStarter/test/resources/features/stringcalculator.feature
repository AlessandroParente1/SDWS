Feature: String Calculator

  Scenario: Add empty string returns 0
    Given I have a string calculator
    When I add an empty string
    Then the result should be 0

  Scenario: Add one number returns same number
    Given I have a string calculator
    When I add "5"
    Then the result should be 5

  Scenario: Add two numbers return same number
    Given I have a string calculator
    When I add "5,5"
    Then the result should be 10

  Scenario: Use new line delimeter
    Given I have a string calculator
    When I add "2\n2,2"
    Then the result should be 6

  Scenario: Use my own delimeter
    Given I have a string calculator
    When I add "//k\n2k2k2\n3"
    Then the result should be 9

  Scenario: Feed negative number throws exception
    Given I have a string calculator
    When I add "-1,-1,-1"
    Then an exception should be thrown