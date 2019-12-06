package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.GameRepresentation;

public class UpdateGameRequest {
    private GameRepresentation gameRepresentation;

    public UpdateGameRequest() {

    }

    public UpdateGameRequest(GameRepresentation gameRepresentation) {
        this.gameRepresentation = gameRepresentation;
    }

    @JsonProperty
    public GameRepresentation getGameRepresentation() {
        return this.gameRepresentation;
    }
}
