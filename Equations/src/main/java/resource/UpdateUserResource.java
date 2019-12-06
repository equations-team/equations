package resource;

import db.UserDAO;
import entity.User;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.UpdateUserRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;

@Path("/api/UpdateUser")
@Produces(MediaType.APPLICATION_JSON)
public class UpdateUserResource {
    private final Jdbi jdbi;
    private final UserDAO userDAO;
    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateUserResource.class);

    public UpdateUserResource(Jdbi jdbi, UserDAO userDAO) {
        this.jdbi = jdbi;
        this.userDAO = userDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UpdateUserRequest updateUserRequest) {
        User user = updateUserRequest.getUser();
        Response response;
        try {
            userDAO.updateUser(user.getUserName(), user.getUserPassword(), user.getUserId());
            response = Response.status(200).entity(user).build();
        } catch (NoSuchElementException e) {
            String error = e.getMessage() + '\n' + e.getStackTrace();
            LOGGER.error(error);
            response = Response.status(500).entity(error).build();
        }
        return response;
    }

}
