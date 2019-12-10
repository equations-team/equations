package db.helper;

import db.GameDAO;
import db.UserDAO;
import entity.GameRepresentation;
import gamestatemanager.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerHelper {
    private final GameDAO gameDAO;
    private final UserDAO userDAO;

    public PlayerHelper(GameDAO gameDAO, UserDAO userDAO) {
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }

    public List<Player> getPlayersByGameId(String gameId) {
        List<String> userIds = new ArrayList<>();
        GameRepresentation gameRepresentation = gameDAO.getGame(gameId);
        userIds.add(gameRepresentation.getRefUserIdOne());
        userIds.add(gameRepresentation.getRefUserIdTwo());
        userIds.add(gameRepresentation.getRefUserIdThree());
        List<Player> players = new ArrayList<>();
        for (String userId: userIds) {
            Player p = new Player(userDAO.getUserNameByUserId(userId));
            players.add(p);
        }
        return players;
    }
}
