package resource;

import activegamemanager.ActiveManager;
import db.GameDAO;
import db.helper.PlayerHelper;
import entity.GameRepresentation;
import gamestatemanager.Manager;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.CreateGameRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;
import java.util.UUID;

@Path("/api/CreateGame")
@Consumes(MediaType.APPLICATION_JSON)
public class CreateGameResource {
  private final Jdbi jdbi;
  private final GameDAO gameDAO;
  private final ActiveManager activeManager;
  private final PlayerHelper playerHelper;
  private final static Logger LOGGER = LoggerFactory.getLogger(CreateGameResource.class);

  public CreateGameResource(Jdbi jdbi, GameDAO gameDAO, ActiveManager activeManager,
      PlayerHelper playerHelper) {
    this.jdbi = jdbi;
    this.gameDAO = gameDAO;
    this.activeManager = activeManager;
    this.playerHelper = playerHelper;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createGame(CreateGameRequest createGameRequest) {
    GameRepresentation game = createGameRequest.getGameRepresentation();
    String guid = UUID.randomUUID().toString();
    Response response;
    try {
      gameDAO.insertGame(guid, game.getRefUserIdOne(), game.getRefUserIdTwo(),
          game.getRefUserIdThree());
      activeManager.createGame(playerHelper.getPlayersByGameId(guid));
      response = Response.status(200).entity(guid).build();
    } catch (NoSuchElementException e) {
      String error = e.getMessage() + '\n' + e.getStackTrace();
      LOGGER.error(error);
      response = Response.status(500).entity(error).build();
    }
    return response;
  }
}
