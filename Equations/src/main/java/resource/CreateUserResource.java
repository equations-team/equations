package resource;

import db.UserDAO;
import entity.User;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.CreateUserRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;
import java.util.UUID;

@Path("/api/CreateUser")
@Produces(MediaType.APPLICATION_JSON)
public class CreateUserResource {
    private final Jdbi jdbi;
    private final UserDAO userDAO;
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateUserResource.class);

    public CreateUserResource(Jdbi jdbi, UserDAO userDAO) {
        this.jdbi = jdbi;
        this.userDAO = userDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserRequest createUserRequest) {
        User user = createUserRequest.getUser();
        String guid = UUID.randomUUID().toString();
        Response response;
        try {
            userDAO.insertUser(guid, user.getUserName(), user.getUserPassword());
            User success = User.newBuilder()
                    .setUserId(guid)
                    .setUserName(user.getUserName())
                    .setUserPassword(user.getUserPassword())
                    .build();
            response = Response.status(200).entity(success).build();
        } catch (NoSuchElementException e) {
            String error = e.getMessage() + '\n' + e.getStackTrace();
            LOGGER.error(error);
            response = Response.status(500).entity(error).build();
        }
        return response;
    }

}
