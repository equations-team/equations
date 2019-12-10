package activegamemanager;

import gamestatemanager.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import fundementalgamemechanics.*;

import java.util.List;


public class ActiveManager {

	private Vector<Manager> games;
	private Map<Integer, Websocket> players;
	
	public ActiveManager() {
		games = new Vector<Manager>();
		players  = new HashMap<Integer, Websocket>();
	}
	
	public void addPlayertoWebsocket(int playerID, Websocket web) {
		players.put(playerID, web);
	}
	
	public void removePlayer(int playerID) {
		players.remove(playerID);
	}
	
	public Websocket getWeb(int playerID) {
		return players.get(playerID);
	}
	

	public Vector<Manager> getGames() {
		return games;
	}

	public Map<Integer, Websocket> getPlayers() {
		return players;
	}
		
	public void createGame(List<Player> p, Dice[] dice) {
		games.add(new Manager((Player[])p.toArray(),dice));
	}
	
}
