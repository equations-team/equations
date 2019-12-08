package db.map;

import entity.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        return User.newBuilder()
                .setUserId(rs.getString("user_id"))
                .setUserName(rs.getString("user_name"))
                .setUserPassword(rs.getString("user_password"))
                .build();
    }
}
