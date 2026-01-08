package org.acme.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.acme.Person;

public class SimpleDtuPay {

    private final Map<String, String> userIdToBankAccountId = new HashMap<>();
    private final Map<String, String> userIdToName = new HashMap<>();
    // public List<String> customerIds = new ArrayList<>();
    public List<Payment> payments = new ArrayList<>();
    // private List<String> merchantIds = new ArrayList<>();

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
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setCustomerId(customerId);
        payment.setMerchantId(merchantId);
        payments.add(payment);
        return true;
    }

}
