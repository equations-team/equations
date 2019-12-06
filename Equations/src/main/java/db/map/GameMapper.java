package db.map;

import entity.GameRepresentation;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<GameRepresentation> {
    @Override
    public GameRepresentation map(ResultSet rs, StatementContext ctx) throws SQLException {
        return GameRepresentation.newBuilder()
                .setGameId(rs.getString("game_id"))
                .setRefUserIdOne(rs.getString("ref_user_id_one"))
                .setRefUserIdTwo(rs.getString("ref_user_id_two"))
                .setRefUserIdThree(rs.getString("ref_user_id_three"))
                .setUserOneScore(rs.getInt("user_one_score"))
                .setUserTwoScore(rs.getInt("user_two_score"))
                .setUserThreeScore(rs.getInt("user_three_score"))
                .build();
    }
}
