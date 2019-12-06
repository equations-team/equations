package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.GameRepresentation;

public class CreateGameRequest {
    private GameRepresentation gameRepresentation;

    public CreateGameRequest() {

    }

    public CreateGameRequest(GameRepresentation gameRepresentation) {
        this.gameRepresentation = gameRepresentation;
    }

    @JsonProperty
    public GameRepresentation getGameRepresentation() {
        return this.gameRepresentation;
    }
}
