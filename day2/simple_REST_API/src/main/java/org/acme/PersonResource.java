package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.ArrayList;
import org.acme.PersonService;

@Path("/person/{id}")
public class PersonResource {

  PersonService service = new PersonService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPerson(@PathParam("id") String id) {
    Person person = service.getPerson(id);
    if (person == null) {
      return Response.status(404).build();
    }
    return Response.ok(person).build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response putPerson(@PathParam("id") String id, Person p) {
    // Check for the forbidden address value
    if ("-none-".equals(p.getCountry())) {
      return Response.status(Response.Status.BAD_REQUEST)
          .entity("Address cannot be \"-none-\"")
          .build(); //
    }

    Person updatedPerson = service.updatePerson(id, p);

    if (updatedPerson == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    return Response.ok(updatedPerson).build(); //
  }

}