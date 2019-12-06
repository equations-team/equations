package active_manager;

import state_manager.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import fundementalgamemechanics.*;

public class ActiveManager implements IActiveManager {

	
	private GameDBImpl myDB;
	private Map<Integer, Manager> myGames;
	private Vector<Integer> myGameIDs;
	
	public ActiveManager(GameDBImpl db) {
		myGames = new HashMap<Integer, Manager>();
		myDB = db;
		myGameIDs = new Vector<Integer>();
	}
	
	
	public void addNewGame(int[] players, Dice[] dice) {
		int tempGameId = myDB.makeGame();
		myGameIDs.add(tempGameId);
		Player[] tempPlayers = this.createPlayers(players);
		
		
		myGames.put(tempGameId, new Manager(tempPlayers,dice));
		for(int i =0; i < players.length; i++) {
			myDB.addPlayerToGame(tempGameId, players[i]);

		}
	}
	
	public Player[] createPlayers(int[] ps) {
		Player[] p = new Player[ps.length];
		for(int i = 0; i <p.length; i++) {
			p[i] = new Player(myDB.getName(ps[i]));
		}
		return p;
	}
	/*
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
	*/
	
	public Manager getGame(int gameID) {
		Set<Integer> KeySet = myGames.keySet();
		Iterator<Integer> it = KeySet.iterator();
		while(it.hasNext()) {
			int key = it.next();
			if(gameID == key)
			return myGames.get(key);
		}
		return null;
	}
	
	public void setScoresAfterGameEnd(int gameID) {
		Manager tempGame = this.getGame(gameID);
		if(tempGame.isGameEnd()) {
			myDB.setScore(tempGame.getCurrentPlayer().getScore(), myDB.getID(tempGame.getCurrentPlayer().getName()));
			myDB.setScore(tempGame.getLastPlayer().getScore(), myDB.getID(tempGame.getLastPlayer().getName()));
			myDB.setScore(tempGame.getThirdPlayer().getScore(), myDB.getID(tempGame.getThirdPlayer().getName()));
		}else {
			System.out.println("Game is still going!");
		}
	}
	
	public int checkActiveGameNum() {
		return myGames.size();
	}
	
	
	

	//TODO: Need to check the return type for both functions.
	public Vector<Player[]> getMyPlayers(int gameID) {
		return myDB.getPlayers(gameID);
	}

	public Vector<Manager> getMyGames() {
		return myDB.getGames();
	}
	
	public Map<Integer, Manager> getGame(){
		return myGames;
	}


	public Vector<Integer> getMyGameIDs() {
		return myGameIDs;
	}
	
	
	
}
