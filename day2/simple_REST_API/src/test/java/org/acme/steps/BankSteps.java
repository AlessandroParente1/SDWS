package org.acme.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.ws.fastmoney.User;
import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankService_Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankSteps {

    private String apiKey = "peach5593";
    private User customerUser;
    private User merchantUser;
    private String customerDtuPayId;
    private String merchantDtuPayId;
    private BankService bank = new BankService_Service().getBankServicePort();
    private SimpleDtuPay dtuPay = new SimpleDtuPay();
    private String customerAccountId;
    private String merchantAccountId;
    private final List<String> createdAccounts = new ArrayList<>();
    private boolean paymentSuccessful;

    @Given("a customer with name {string}, last name {string}, and CPR {string}")
    public void a_customer_with_name_last_name_and_cpr(String string, String string2, String string3) {
            customerUser = new User();
            customerUser.setFirstName(string);
            customerUser.setLastName(string2);
            customerUser.setCprNumber(string3);
    }

    @Given("the customer is registered with the bank with an initial balance of {int} kr")
    public void the_customer_is_registered_with_the_bank_with_an_initial_balance_of_kr(Integer int1)
    throws BankServiceException_Exception {
        customerAccountId = bank.createAccountWithBalance(apiKey, customerUser, BigDecimal.valueOf(int1));
        createdAccounts.add(customerAccountId);
    }

    @Given("the customer is registered with Simple DTU Pay using their bank account")
    public void the_customer_is_registered_with_simple_dtu_pay_using_their_bank_account() {
        String fullName = customerUser.getFirstName() + " " + customerUser.getLastName();
        customerDtuPayId = dtuPay.register(fullName, customerAccountId);
    }
    
    @Given("a merchant with name {string}, last name {string}, and CPR {string}")
    public void a_merchant_with_name_last_name_and_cpr(String string, String string2, String string3) {
        merchantUser = new User();
        merchantUser.setFirstName(string);
        merchantUser.setLastName(string2);
        merchantUser.setCprNumber(string3);
    }
    
    @Given("the merchant is registered with the bank with an initial balance of {int} kr")
    public void the_merchant_is_registered_with_the_bank_with_an_initial_balance_of_kr(Integer int1)
    throws BankServiceException_Exception {
        merchantAccountId = bank.createAccountWithBalance(apiKey, merchantUser, BigDecimal.valueOf(int1));
        createdAccounts.add(merchantAccountId);
    }
    
    @Given("the merchant is registered with Simple DTU Pay using their bank account")
    public void the_merchant_is_registered_with_simple_dtu_pay_using_their_bank_account() {
        String fullName = merchantUser.getFirstName() + " " + merchantUser.getLastName();
        merchantDtuPayId = dtuPay.register(fullName, merchantAccountId);
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void merchant_initiates_payment(Integer amount) {
        paymentSuccessful = dtuPay.pay(amount, customerDtuPayId, merchantDtuPayId);
    }

    @Then("the payment is successful")
    public void the_payment_is_successful() {
        assertTrue(paymentSuccessful);
    }

    @Then("a successful payment of {int} kr from the customer to the merchant")
    public void successful_payment(int amount) {
        paymentSuccessful = dtuPay.pay(amount, customerDtuPayId, merchantDtuPayId);
        assertTrue(paymentSuccessful);
    }
    
    @Then("the balance of the customer at the bank is {int} kr")
    public void the_balance_of_the_customer_at_the_bank_is_kr(Integer int1) throws BankServiceException_Exception {
        Account a = bank.getAccount(customerAccountId);
        assertEquals(0, a.getBalance().compareTo(BigDecimal.valueOf(int1)));
    }
    @Then("the balance of the merchant at the bank is {int} kr")
    public void the_balance_of_the_merchant_at_the_bank_is_kr(Integer int1) throws BankServiceException_Exception {
        Account a = bank.getAccount(merchantAccountId);
        assertEquals(0, a.getBalance().compareTo(BigDecimal.valueOf(int1)));

    }
    @After
    public void cleanupBankAccounts() throws BankServiceException_Exception {
        for (String account : createdAccounts) {
            bank.retireAccount(apiKey, account);
        }
        createdAccounts.clear();
    }
}