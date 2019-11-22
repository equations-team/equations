
import java.util.Vector;

public interface IGameDB {

    public Vector getPlayers(int gameID);

    public Vector getGames();

    public int makeGame();

    public void addPlayerToGame(int GameID, int PlayerID);
    
    public void deleteGame(int gameID);
}
