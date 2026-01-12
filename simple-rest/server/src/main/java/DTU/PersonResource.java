package DTU;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/person")
public class PersonResource {
  @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
        return new Person("John Doe", "30");
    }
}
