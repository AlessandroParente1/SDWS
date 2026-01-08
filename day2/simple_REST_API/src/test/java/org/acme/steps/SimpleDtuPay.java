package org.acme.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.acme.Person;

public class SimpleDtuPay {

    // public List<String> customerIds = new ArrayList<>();
    public List<Payment> payments = new ArrayList<>();
    // private List<String> merchantIds = new ArrayList<>();

    public List<Payment> listPayments() {
        return payments;
    }

    public String register(Person person) {
        String id = UUID.randomUUID().toString();
        person.setId(id);
        return person.getId();
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
