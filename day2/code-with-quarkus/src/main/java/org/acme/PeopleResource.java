package org.acme;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/person/")
public class PeopleResource {

  PersonService service = new PersonService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> findPerson() {
    return service.getPeople();
  }

}