import config.EquationsConfiguration;
import db.GameDAO;
import db.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.jdbi.v3.core.Jdbi;
import resource.*;

public class EquationsApplication extends Application<EquationsConfiguration> {

    public static void main(String[] args) throws Exception {
        new EquationsApplication().run(args);
    }

    public void initialize(Bootstrap<EquationsConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)
        ));
        bootstrap.addBundle(
                new AssetsBundle("/assets/html", "/html", "index.html", "html")
        );
        bootstrap.addBundle(
                new AssetsBundle("/assets/css", "/css", null, "css")
        );
        bootstrap.addBundle(
                new AssetsBundle("/assets/js", "/js", null, "js")
        );
        bootstrap.addBundle(new JdbiExceptionsBundle());
        bootstrap.addBundle(new ViewBundle<EquationsConfiguration>());
    }

    @Override
    public void run(EquationsConfiguration configuration, Environment environment) throws Exception {
        // Database
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory.build(environment, configuration.getDatabase(), "mysql");

        // DAOs
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final GameDAO gameDAO = jdbi.onDemand(GameDAO.class);

        // Resources
        final RegisterUserResource registerUserResource = new RegisterUserResource(jdbi, userDAO);
        final LoginUserResource loginUserResource = new LoginUserResource(jdbi, userDAO);
        final GetUserResource getUserResource = new GetUserResource(jdbi, userDAO);
        final UpdateUserResource updateUserResource = new UpdateUserResource(jdbi, userDAO);
        final CreateGameResource createGameResource = new CreateGameResource(jdbi, gameDAO);
        final GetGameViewResource getGameViewResource = new GetGameViewResource(gameDAO);

        // Health Checks

        // Registration
        environment.jersey().register(registerUserResource);
        environment.jersey().register(loginUserResource);
        environment.jersey().register(getUserResource);
        environment.jersey().register(updateUserResource);
        environment.jersey().register(createGameResource);
        environment.jersey().register(getGameViewResource);

    }
}
