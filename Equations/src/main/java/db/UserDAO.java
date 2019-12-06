package db;

import db.map.UserMapper;
import entity.User;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDAO {
    // INSERT USER
    @RegisterRowMapper(UserMapper.class)
    @SqlUpdate("INSERT INTO user (user_id, user_name, user_password) VALUES (?,?,?)")
    void insertUser(String userId, String userName, String userPassword);

    @RegisterRowMapper(UserMapper.class)
    @SqlQuery("SELECT * FROM user WHERE user_id = ?")
    User getUser(String userId);

    @RegisterRowMapper(UserMapper.class)
    @SqlUpdate("UPDATE user SET user_name = ?, user_password = ? WHERE user_id = ?")
    void updateUser(String userName, String userPassword, String userId);
}
