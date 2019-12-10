package views;

import entity.GameRepresentation;
import fundementalgamemechanics.Game;
import io.dropwizard.views.View;

public class GameView extends View {
    private final GameRepresentation game;

    public GameView(GameRepresentation game) {
        super("gameview.ftl");
        this.game = game;
    }

    public GameRepresentation getGame() {
        return game;
    }


}
