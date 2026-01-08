package org.acme.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.ws.fastmoney.User;
import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankService_Service;

import java.math.BigDecimal;

public class BankSteps {

    private String apiKey = "peach5593";
    private User customerUser;
    private String dtuPayId;
    private String accountId;
    private BankService bank;
    private SimpleDtuPay dtuPay = new SimpleDtuPay();



@Given("a customer with name {string}, last name {string}, and CPR {string}")
public void a_customer_with_name_last_name_and_cpr(String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
        customerUser = new User();
        customerUser.setFirstName(string);
        customerUser.setLastName(string2);
        customerUser.setCprNumber(string3);
}

@Given("the customer is registered with the bank with an initial balance of {int} kr")
public void the_customer_is_registered_with_the_bank_with_an_initial_balance_of_kr(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    try {
        accountId = bank.createAccountWithBalance(apiKey, customerUser, BigDecimal.valueOf(int1));
    } catch (BankServiceException_Exception e) {
        throw new RuntimeException(e);
    }
    // try {
    //     String accountId = bank.createAccountWithBalance(apiKey, user, BigDecimal.valueOf(int1));
    // } 
    // catch (BankServiceException_Exception e) {
    // throw new RuntimeException(e);
    // }
}

@Given("the customer is registered with Simple DTU Pay using their bank account")
public void the_customer_is_registered_with_simple_dtu_pay_using_their_bank_account(String name) {
    String fullName = customerUser.getFirstName() + " " + customerUser.getLastName();
    dtuPayId = dtuPay.register(fullName, accountId);

}
@Given("a merchant with name {string}, last name {string}, and CPR {string}")
public void a_merchant_with_name_last_name_and_cpr(String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Given("the merchant is registered with the bank with an initial balance of {int} kr")
public void the_merchant_is_registered_with_the_bank_with_an_initial_balance_of_kr(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Given("the merchant is registered with Simple DTU Pay using their bank account")
public void the_merchant_is_registered_with_simple_dtu_pay_using_their_bank_account() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Then("the balance of the customer at the bank is {int} kr")
public void the_balance_of_the_customer_at_the_bank_is_kr(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Then("the balance of the merchant at the bank is {int} kr")
public void the_balance_of_the_merchant_at_the_bank_is_kr(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
}