package entity;

public class GameRepresentation {
    public static class Builder {
        private GameRepresentation gameRepresentation = new GameRepresentation();

        public Builder setGameId(String gameId) {
            gameRepresentation.gameId = gameId;
            return this;
        }

        public Builder setRefUserIdOne(String refUserIdOne) {
            gameRepresentation.refUserIdOne = refUserIdOne;
            return this;
        }

        public Builder setRefUserIdTwo(String refUserIdTwo) {
            gameRepresentation.refUserIdTwo = refUserIdTwo;
            return this;
        }

        public Builder setRefUserIdThree(String refUserIdThree) {
            gameRepresentation.refUserIdThree = refUserIdThree;
            return this;
        }

        public Builder setUserOneScore(Integer userOneScore) {
            gameRepresentation.userOneScore = userOneScore;
            return this;
        }

        public Builder setUserTwoScore(Integer userTwoScore) {
            gameRepresentation.userTwoScore = userTwoScore;
            return this;
        }

        public Builder setUserThreeScore(Integer userThreeScore) {
            gameRepresentation.userThreeScore = userThreeScore;
            return this;
        }

        public GameRepresentation build() {
            return gameRepresentation;
        }
    }
    private String gameId;
    private String refUserIdOne;
    private String refUserIdTwo;
    private String refUserIdThree;
    private Integer userOneScore;
    private Integer userTwoScore;
    private Integer userThreeScore;

    public String getGameId() {
        return gameId;
    }

    public String getRefUserIdOne() {
        return refUserIdOne;
    }

    public String getRefUserIdTwo() {
        return refUserIdTwo;
    }

    public String getRefUserIdThree() {
        return refUserIdThree;
    }

    public Integer getUserOneScore() {
        return userOneScore;
    }

    public Integer getUserTwoScore() {
        return userTwoScore;
    }

    public Integer getUserThreeScore() {
        return userThreeScore;
    }

    public static Builder newBuilder() {
        return new GameRepresentation.Builder();
    }

    public Builder toBuilder() {
        return new GameRepresentation.Builder()
                .setGameId(gameId)
                .setRefUserIdOne(refUserIdOne)
                .setRefUserIdTwo(refUserIdTwo)
                .setRefUserIdThree(refUserIdThree)
                .setUserOneScore(userOneScore)
                .setUserTwoScore(userTwoScore)
                .setUserThreeScore(userThreeScore);
    }
}
