package resource;

import common.Cryptographer;
import db.UserDAO;
import entity.User;
import org.jdbi.v3.core.Jdbi;
import request.RegisterUserRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/api/RegisterUser")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterUserResource {
    private final Jdbi jdbi;
    private final UserDAO userDAO;

    public RegisterUserResource(Jdbi jdbi, UserDAO userDAO) {
        this.jdbi = jdbi;
        this.userDAO = userDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(RegisterUserRequest registerUserRequest) {
        User user = registerUserRequest.getUser();
        String guid = UUID.randomUUID().toString();
        String salt = Cryptographer.generateSalt(32).get().toString();
        try {
            String password = Cryptographer.getSaltedHash(user.getUserPassword(), salt);
            userDAO.insertUser(guid, user.getUserName(), password, salt);
            User success = User.newBuilder()
                    .setUserId(guid)
                    .setUserName(user.getUserName())
                    .setUserPassword(password)
                    .setUserSalt(salt)
                    .build();
            return Response.status(200).entity(success).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e.getStackTrace().toString()).build();
        }
    }
}
