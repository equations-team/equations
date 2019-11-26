package localhostgame;

public class GameTurns {
	
	private Game myGame;
	private int numPlayers;
	private int currentPlayer;
	
	GameTurns(int players) {
		numPlayers = players;
		myGame = new Game();
	}
	
	public boolean doAction(int playerNum,int action,int index,int destination) {
		if(playerNum != currentPlayer) return false;
		if(action==1) {
			myGame.moveDie(index, destination);
		}else {
			challange(lastPlayer());
		}
		currentPlayer = nextPlayer();
		return true;
	}

	private void challange(int player) {
		
	}

	public int nextPlayer() {
		int player = currentPlayer;
		player++;
		if(player == numPlayers) {
			player = 0;
		}
		return player;
	}

	public int lastPlayer() {
		int player = currentPlayer;
		player--;
		if(player < 0) {
			player = numPlayers;
		}
		return player;
	}
	
}
