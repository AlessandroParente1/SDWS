Feature: Payment
  Scenario: Successful Payment
    Given a customer with name "Susane", last name "Bedin", and CPR "092111-2111"
    And the customer is registered with the bank with an initial balance of 1000 kr
    And the customer is registered with Simple DTU Pay using their bank account
    And a merchant with name "Cacilo", last name "Dandi", and CPR "981261-1145"
    And the merchant is registered with the bank with an initial balance of 1000 kr
    And the merchant is registered with Simple DTU Pay using their bank account
    When the merchant initiates a payment for 10 kr by the customer
    Then the payment is successful
    And the balance of the customer at the bank is 990 kr
    And the balance of the merchant at the bank is 1010 kr
