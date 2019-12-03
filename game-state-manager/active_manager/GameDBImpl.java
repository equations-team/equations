package active_manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GameDBImpl implements IGameDB, IPlayerDB {

    ResultSet rs = null;
    PreparedStatement ps;
    Connection conn = null;
    String query = "";

    public GameDBImpl() {
        conn = Database.getConnection();
    }
    
    
     // This method takes game id and returns backs all the players in the game as vector.
     
    public Vector getPlayers(int gameID) {
        Vector players = new Vector();
        query = "Select * from gamedb where gameID = " + gameID;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                players.add(rs.getInt(2));
                players.add(rs.getInt(3));
                players.add(rs.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return players;
    }
    
   
     //This method returns all the games in database.
    
    
    
    public Vector getGames() {
        Vector games = new Vector();
        query = "Select * from gamedb";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
           while (rs.next()) {
                Vector game = new Vector();
                game.add(rs.getInt(1));
                game.add(rs.getInt(2));
                game.add(rs.getInt(3));
                game.add(rs.getInt(4));
                games.add(game);
            }
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return games;
    }
    
    //  This method adds new game to the database and returns the game id.
  
    public int makeGame() {
        try {
            query = "insert into gamedb(gameID)"
                    + "Values(null)";
            ps = conn.prepareStatement(query);

            if (ps.executeUpdate() > 0) {
                query = "SELECT * FROM `gamedb` order by gameID DESC limit 1";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return -1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return -1;
    }
    
     // This method takes game id and player id as input and adds a player to that game and  it allows max 3 players each game and they are unique ones.
    
    public void addPlayerToGame(int GameID, int PlayerID) {
        try {
            query = "SELECT * FROM `gamedb` where gameID=" + GameID;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                int playerId1 = rs.getInt(2);
                int playerId2 = rs.getInt(3);
                int playerId3 = rs.getInt(4);

                if (playerId1 == -1) {
                    query = "update gameDB set player_1_id=" + PlayerID + " where gameID= " + GameID;
                    ps = conn.prepareStatement(query);
                    if (ps.executeUpdate() > 0) {
                        // System.out.println("Visit Counts updated for" + id1);
                    }
                } else if (playerId2 == -1) {
                    if (PlayerID != playerId1) {
                        query = "update gameDB set player_2_id=" + PlayerID + " where gameID= " + GameID;
                        ps = conn.prepareStatement(query);
                        if (ps.executeUpdate() > 0) {
                            // System.out.println("Visit Counts updated for" + id1);
                        }
                    } else {
                        System.out.println("player id : " + PlayerID + " is already added to this game.");
                    }
                } else if (playerId3 == -1) {
                    if (PlayerID != playerId1 && PlayerID != playerId2) {
                        query = "update gameDB set player_3_id=" + PlayerID + " where gameID= " + GameID;
                        ps = conn.prepareStatement(query);
         
                        if (ps.executeUpdate() > 0) {
                            // System.out.println("Visit Counts updated for" + id1);
                        }
                    } else {
                        System.out.println("player id : " + PlayerID + " is already added to this game.");
                    }
                } else {
                    System.out.println("Already 3 players are added to this game.");

                }

            } else {

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
    // This method take username and returns the id of it.
     
    public int getID(String username) {

        query = "Select id from accounts where username = '" + username + "'";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    
//     * This method takes player id and returns the username of it.

    public String getName(int playerID) {

        query = "Select username from accounts where id = " + playerID;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Cannot connect to server right now, try again later.");
        }
        return null;
    }
    
     // This method takes player id and returns score of it.
    
    public int getScore(int playerID) {

        query = "Select points from accounts where id = " + playerID;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println("Cannot connect to server right now, try again later.");
        }
        return -1;
    }
    
     // This method sets the score to given player id.
   
    public void setScore(int score, int PlayerID) {
        query = "update accounts set points=" + score + " where id= " + PlayerID;
        try {
            ps = conn.prepareStatement(query);
            if (ps.executeUpdate() > 0) {
                // System.out.println("Visit Counts updated for" + id1);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }
    
     // This method delete the game with given id.
     
    public void deleteGame(int gameID) {
        query = "DELETE FROM `gamedb` WHERE gameID=" + gameID ;
        try {
            ps = conn.prepareStatement(query);
            if (ps.executeUpdate() > 0) {
                // System.out.println("Visit Counts updated for" + id1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
