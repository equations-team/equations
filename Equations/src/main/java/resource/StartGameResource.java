package resource;

import gamestatemanager.Manager;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/StartGame")
@Produces(MediaType.APPLICATION_JSON)
public class StartGameResource {
    public StartGameResource(Manager manager) {
    }
}
