package active_manager;
public interface IPlayerDB {

    public int getID(String username);

    public String getName(int playerID);

    public int getScore(int playerID);

    public void setScore(int score, int PlayerID);
}
