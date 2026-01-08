package org.acme;

import java.io.Serializable;

//

public class Person implements Serializable, PersonInterface{ // What does serializeable do?
  private String name;
  private String Country;
  private String id;
  public String lastName;
  public String cpr;
  public String bankAccount;

  public Person() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCpr() {
    return cpr;
  }

  public void setCpr(String cpr) {
    this.cpr = cpr;
  }

  public String getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }

  public String getCountry() {
    return Country;
  }

  public void setCountry(String Country) {
    this.Country = Country;
  }

  public static void main(String[] args) {

    jakarta.ws.rs.client.Client client = jakarta.ws.rs.client.ClientBuilder.newClient();

  }
}