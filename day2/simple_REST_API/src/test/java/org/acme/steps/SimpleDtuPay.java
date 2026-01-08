package org.acme.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.acme.Person;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankService_Service;

public class SimpleDtuPay {

    private final Map<String, String> userIdToBankAccountId = new HashMap<>();
    private final Map<String, String> userIdToName = new HashMap<>();
    // public List<String> customerIds = new ArrayList<>();
    public List<Payment> payments = new ArrayList<>();
    // private List<String> merchantIds = new ArrayList<>();
    private final BankService bank = new BankService_Service().getBankServicePort();

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Payment> listPayments() {
        return payments;
    }

    public String register(String name, String accountId) {
        String id = UUID.randomUUID().toString();
        userIdToName.put(id, name);
        userIdToBankAccountId.put(id, accountId);
        return id;
    }

    public Boolean pay(int amount, String customerId, String merchantId) {
        errorMessage = null;

        if (!userIdToBankAccountId.containsKey(customerId)) {
            errorMessage = "customer with id \"" + customerId + "\" is unknown";
            return false;
        }
        if (!userIdToBankAccountId.containsKey(merchantId)) {
            errorMessage = "merchant with id \"" + merchantId + "\" is unknown";
            return false;
        }

        String customerAccount = userIdToBankAccountId.get(customerId);
        String merchantAccount = userIdToBankAccountId.get(merchantId);
        String description = "Payment from " + userIdToName.get(customerId) + " to " + userIdToName.get(merchantId);

        try {
            bank.transferMoneyFromTo(customerAccount, merchantAccount, BigDecimal.valueOf(amount), description);
            Payment p = new Payment();
            p.setAmount(amount);
            p.setCustomerId(customerId);
            p.setMerchantId(merchantId);
            p.setDescription(merchantAccount);
            payments.add(p);

            return true;
        } catch (BankServiceException_Exception e) {
            errorMessage = e.getMessage();
            return false;
        }
        
    }

    public void unregister(String userId) {
        userIdToName.remove(userId);
        userIdToBankAccountId.remove(userId);
    }

}
