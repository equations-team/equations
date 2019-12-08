package resource;

import common.Cryptographer;
import db.UserDAO;
import entity.User;
import org.jdbi.v3.core.Jdbi;
import request.LoginUserRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/LoginUser")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginUserResource {
    private final Jdbi jdbi;
    private final UserDAO userDAO;

    public LoginUserResource(Jdbi jdbi, UserDAO userDAO) {
        this.jdbi = jdbi;
        this.userDAO = userDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginUserRequest loginUserRequest) {
        try {
            String salt = userDAO.getSaltByUsername(loginUserRequest.getUserName());
            String passToCheck = Cryptographer.getSaltedHash(loginUserRequest.getUserPassword(), salt);
            User user = userDAO.getUserByNameAndPassword(loginUserRequest.getUserName(), passToCheck);
            return Response.status(200).entity(user).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getStackTrace().toString()).build();
        }
    }
}
