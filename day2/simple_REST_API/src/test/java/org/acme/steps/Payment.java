package org.acme.steps;


public class Payment {
    private int amount;
    private String customerId;
    private String merchantId;
    private String descString;

    public Payment() {}

    public Payment(int amount, String customerId, String merchantId) {
        this.amount = amount;
        this.customerId = customerId;
        this.merchantId = merchantId;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }  
    
 public String getDescription() {
        return descString;
    }
    public void setDescription(String descString) {
        this.descString = descString;
    }  
}
