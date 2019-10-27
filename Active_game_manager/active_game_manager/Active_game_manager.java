package active_game_manager;

import game_state_manager.*;
import java.util.Vector;

public class Active_game_manager {

	private Vector<Manager> myGames;
	private Vector<Player[]> myPlayers;
	
	public Active_game_manager() {
		myGames = new Vector<Manager>();
		myPlayers = new Vector<Player[]>();
	}
	
	public void addNewGame(Player[] players) {
		myPlayers.add(players);
		myGames.add(new Manager(players));
	}
	
	public Manager getGame(Player p) {
		for(int i = 0; i < myGames.size(); i++) {
			for(int j = 0; j < myPlayers.elementAt(i).length; j++) {
				if (p.getName() == myPlayers.elementAt(i)[j].getName()) {
					return myGames.elementAt(i);
				}
			}
		}
		return null;
	}
	
	public Player getWinner(Manager game) {
		for(int i = 0; i < myGames.size(); i++) {
			if(game == myGames.elementAt(i)) {
				for(int j = 0; j <myPlayers.elementAt(i).length; j++) {
					if(myPlayers.elementAt(i)[j].getCheckwin()) {
						return myPlayers.elementAt(i)[j];
					}
				}
			}
		}
		
		return null;
	}
	
	public int checkActiveGameNum() {
		return myGames.size();
	}
	
	public boolean checkIfNoPlayers() {
		return myPlayers.size()== 0;
	}

	public Vector<Player[]> getMyPlayers() {
		return myPlayers;
	}

	public Vector<Manager> getMyGames() {
		return myGames;
	}
	
	
	
}
