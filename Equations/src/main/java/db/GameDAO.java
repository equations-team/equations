package db;

import db.map.GameMapper;
import entity.GameRepresentation;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface GameDAO {
    // INSERT USER
    @RegisterRowMapper(GameMapper.class)
    @SqlUpdate("INSERT INTO game (game_id, ref_user_id_one, ref_user_id_two, " +
            "ref_user_id_three, user_one_score, user_two_score, user_three_score) " +
            "VALUES (?,?,?,?,?,?,?)")
    void insertGame(String gameId, String refUserIdOne, String refUserIdTwo, String refUserIdThree,
                    Integer userOneScore, Integer userTwoScore, Integer userThreeScore);

    @RegisterRowMapper(GameMapper.class)
    @SqlQuery("SELECT * FROM game WHERE game_id = ?")
    GameRepresentation getGame(String gameId);

    @RegisterRowMapper(GameMapper.class)
    @SqlUpdate("UPDATE game SET ref_user_id_one = ?, ref_user_id_two = ?, ref_user_id_three = ?," +
            "user_one_score = ?, user_two_score = ?, user_three_score = ? WHERE game_id = ?")
    void updateGame(String refUserIdOne, String refUserIdTwo, String refUserIdThree,
                    Integer userOneScore, Integer userTwoScore, Integer userThreeScore,
                    String gameId);
}
