package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetGameRequest {
    private String gameId;

    public GetGameRequest() {

    }

    public GetGameRequest(String gameId) {
        this.gameId = gameId;
    }

    @JsonProperty
    public String getGameId() {
        return this.gameId;
    }
}
