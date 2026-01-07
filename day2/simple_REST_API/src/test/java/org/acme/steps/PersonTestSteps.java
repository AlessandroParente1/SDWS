package org.acme.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.acme.Person;
import org.acme.PersonResource;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

public class PersonTestSteps {

  private final PersonResource resource = new PersonResource();
  private Person person;
  private Client c;
  private WebTarget r;
  String jsonResponse;

  @Given("the system is running")
  public void initializeTarget() {
    c = ClientBuilder.newClient();
    r = c.target("http://localhost:8080/");
  }

  @When("i hit the endpoint student id {string}")
  public void callPersonEndpoint(String id) {
    jsonResponse = r.path("person")
        .path(id)
        .request()
        .get(String.class);

    System.out.println("JSON Response: " + jsonResponse);

  }

  @When("i update person with id {string} to name {string} and country {string}")
  public void updatePerson(String id, String name, String country) {
    person = new Person();
    person.setName(name);
    person.setCountry(country);

    Person updatedPerson = r.path("person")
        .path(id)
        .request()
        .put(Entity.entity(person, MediaType.APPLICATION_JSON), Person.class);

    System.out.println("Updated person: " + updatedPerson);
  }

  @Then("it should return student with id {string}") // There has to be a better way to check the output of the json
  // response but monkey brain cant
  public void verifyStudentResponse(String studentId) {

    if (!jsonResponse.contains("\"id\":\"" + studentId + "\"")) {
      throw new AssertionError("Expected id " + studentId + " in response, but got: " + jsonResponse);
    }
    System.out.println("Verified student ID: " + studentId);
  }

  @When("i create a person with name {string} and country {string}")
  public void createPerson(String name, String country) {
    person = new Person();
    person.setId("10");
    person.setName(name);
    person.setCountry(country);
  }

  @When("i update the person with id {string}")
  public void updatePersonWithId(String id) {
    Person updatedPerson = r.path("person")
        .path(id)
        .request()
        .put(Entity.entity(person, MediaType.APPLICATION_JSON), Person.class);

    System.out.println("Updated person: " + updatedPerson);
  }

  @Then("retrieving the person with id {string} should return name {string} and country {string}")
  public void verifyPersonData(String id, String name, String country) {
    callPersonEndpoint(id);

    if (!jsonResponse.contains("\"name\":\"" + name + "\"") ||
        !jsonResponse.contains("\"country\":\"" + country + "\"")) {
      throw new AssertionError(
          "Expected name " + name + " and country " + country + " in response, but got: " + jsonResponse);
    }
    System.out.println("Verified person: name=" + name + ", country=" + country);
  }

  public static void main(String[] args) {
    PersonTestSteps n = new PersonTestSteps();
    n.initializeTarget();
    n.callPersonEndpoint("1");
  }
}