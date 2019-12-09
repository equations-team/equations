package views;

import fundementalgamemechanics.Game;
import io.dropwizard.views.View;

public class GameView extends View {
    private final Game game;

    public GameView(Game game) {
        super("gameview.ftl");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }


}
