package entity;

public class User {
    public static class Builder {
        private final User user = new User();
        public Builder setUserId(String userId) {
            user.userId = userId;
            return this;
        }
        public Builder setUserName(String userName) {
            user.userName = userName;
            return this;
        }
        public Builder setUserPassword(String userPassword) {
            user.userPassword = userPassword;
            return this;
        }
        public Builder setUserSalt(String salt) {
            user.userSalt = salt;
            return this;
        }
        public User build() {
            return user;
        }
    }

    private String userId;
    private String userName;
    private String userPassword;
    private String userSalt;

    public String getUserId() { return userId; }
    public String getUserName() {
        return userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserSalt() {
        return userSalt;
    }
    public static Builder newBuilder() {
        return new User.Builder();
    }
    public Builder toBuilder() {
        return new User.Builder()
                .setUserId(userId)
                .setUserName(userName)
                .setUserPassword(userPassword)
                .setUserSalt(userSalt);
    }
}
