package active_manager;

import java.util.Vector;

import fundementalgamemechanics.*;
import state_manager.*;

public interface IActiveManager {
	void addNewGame(int[] players, Dice[] dice);
	Player[] createPlayers(int[] playersID);
	Manager getGame(int gameID);
	void setScoresAfterGameEnd(int gameID);
	int checkActiveGameNum();
	Vector<Player[]> getMyPlayers(int gameID);
	Vector<Manager> getMyGames();
}

