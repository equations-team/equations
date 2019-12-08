package resource;

import db.GameDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/{gameId}")
@Produces(MediaType.TEXT_HTML)
public class GetGameViewResource {
    private final GameDAO gameDAO;

    public GetGameViewResource(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    
}
