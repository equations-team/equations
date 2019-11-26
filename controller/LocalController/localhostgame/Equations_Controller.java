package localhostgame;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import Solver.Algebra;
import Solver.Solver;
import dice.Die;
import gamestatemanager.GameMove;
import gamestatemanager.Manager;
import gamestatemanager.Player;

/**
 * Controller for equations. It may also be called the presenter or medium for
 * the user to interact. If the user moves a cube, then it is up to the
 * controller to update that move to the model and view.
 * 
 * @author Stephen Mingolelli
 *
 */
public class Equations_Controller {

	// private MockView myView;
	private Manager myManager;
	private Game myModel;
	private Map myWebModel;
	private Algebra myGoal;
	private Player[] myPlayers;
	private String myCurrentPlayerID;
	private int myPlayerCount;
	private GameTurns myTurns;
	private int myCurrentTurn;

	/**
	 * The Constructor
	 */
	public Equations_Controller() {
		// myView = new MockView(this);
		myModel = new Game();
		myManager = new Manager(myPlayers, myModel.getMyResources().getMyMat());
		// myView.setGoalSetter(myTurn);
		myPlayers = new Player[3];
		// myWebModel = new HashMap<String, Object>();
	}

	/////////////
	/// METHODS///
	/////////////

	/**
	 * Takes in 3 player IDs ands puts them in the array.
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public void createPlayerArray(String p1, String p2, String p3) {
		myPlayers[0] = new Player(p1);
		myPlayers[1] = new Player(p2);
		if (myPlayerCount > 2)
			myPlayers[2] = new Player(p3);
		myTurns = new GameTurns(myPlayerCount);
		this.determineFirst(myPlayers);
	}

	/**
	 * Roll dice and get the highest one. Each player rolls a die, the highest goes
	 * first.
	 * 
	 * @return high, the highest number.
	 */

	private void determineFirst(Player[] players) {
		myManager.setFirstPlayer();
		if(players[0] == myManager.getCurrentPlayer())
			myCurrentTurn = 1;
		if(players[1] == myManager.getCurrentPlayer())
			myCurrentTurn = 2;
		if(players[2] == myManager.getCurrentPlayer())
			myCurrentTurn = 3;
		
	}

	// Controller setting the goal from input from view.
	public void goalSet(String goal, String playerID) {
		myGoal = new Algebra(goal, playerID);
	}

	/**
	 * This takes in view knowledge of the mat. More specifically, what the player
	 * is requesting to be put on the 'goal' portion of the mat. Update the model
	 * with this information. Index is the position of the die in a list of die.
	 * 
	 * @param index
	 */
	public void goalSet(int index, Die moved) {
		// Send die to goal
		myModel.moveDie(index, 0);
	}

	/**
	 * Skip to the next persons turn. It should be 1 if the last turn is player 3's.
	 */
	public void passTurn() {
		if(myCurrentTurn != 3) {
			myCurrentTurn++;
			myManager.setCurrentPlayer(myPlayers[myCurrentTurn]);
		}else {
			myCurrentTurn = 1;
			myManager.setCurrentPlayer(myPlayers[myCurrentTurn]);
		}
	}

	/**
	 * Moves a die to one of three locations, forbidden, required, or allowed.
	 * 
	 * @param location
	 * @param index
	 */
	// @GetMapping ("/View")
	public void moveDie(Integer mat, Integer index) {
		switch (mat) {
		case 0:
			// Forbidden
			myManager.moveDie(index, GameMove.ADDFORBIDDEN);
		case 1:
			// Required
			myManager.moveDie(index, GameMove.ADDREQUIRED);
		case 2:
			// Allowed
			myManager.moveDie(index, GameMove.ADDPERMITTED);
		}
		// If we're lucky, this shouldn't occur.
		System.out.println("ERROR");
		return;
	}

	/**
	 * Move die to forbidden, uses die from resources at the index position.
	 * 
	 * @param index
	 * @return true, if successful.
	 */
	private boolean moveToForbidden(int index) {
		myModel.moveDie(index, 1);
		if (myModel.moveDie(index, 1) == -1) {
			return false;
		}

		return true;

	}

	/**
	 * Move die to required, uses die from resources at the index position.
	 * 
	 * @param index
	 * @return true, if successful.
	 */
	private boolean moveToRequired(int index) {
		myModel.moveDie(index, 2);
		if (myModel.moveDie(index, 2) == -1) {
			return false;
		}

		return true;
	}

	/**
	 * Move die to allowed, uses die from resources at the index position.
	 * 
	 * @param index
	 * @return true, if successful.
	 */
	private boolean moveToAllowed(int index) {
		myModel.moveDie(index, 3);
		if (myModel.moveDie(index, 3) == -1) {
			return false;
		}

		return true;
	}

	/**
	 * Check if the challenge is impossible. The function will return false if the
	 * equation is not valid.
	 * 2 for now
	 * 1 for impossible
	 * 
	 * @param playerInput
	 */
	public void challengeImpossible(String firstPlayerEquations, String thirdPlayerEquation) {
		myManager.challenge(1, firstPlayerEquations, thirdPlayerEquation);
	}

	/**
	 * Check if the challenge is possible. The function will return true if the
	 * player's input is correct and valid, else, false.
	 * 
	 * @param playerInput
	 */
	public void challengeNow(String firstPlayerEquations, String thirdPlayerEquation) {
		myManager.challenge(2, firstPlayerEquations, thirdPlayerEquation);
	}

	/////////////////////////
	/// GETTERS AND SETTERS///
	/////////////////////////

	public int getPlayerCount() {
		return myPlayerCount;
	}

	public void setPlayercount(int p) {
		myPlayerCount = p;
	}

	public Game getGame() {
		return myModel;
	}

	public void setGame(Game g) {
		myModel = g;
	}

	public Player[] getPlayers() {
		return myPlayers;
	}

	public void setPlayers(Player[] players) {
		this.myPlayers = players;
	}

	public String getCurrentPlayer() {
		return myCurrentPlayerID;
	}

	public void setCurrentPlayer(String currentPlayerID) {
		myCurrentPlayerID = currentPlayerID;
	}

	public Game getModel() {
		return myModel;
	}

	public void setModel(Game model) {
		this.myModel = model;
	}

	public Map getWebModel() {
		return myWebModel;
	}

	public void setWebModel(Map webModel) {
		this.myWebModel = webModel;
	}

	public Algebra getGoal() {
		return myGoal;
	}

	public void setGoal(Algebra goal) {
		this.myGoal = goal;
	}

	public String getCurrentPlayerID() {
		return myCurrentPlayerID;
	}

	public void setCurrentPlayerID(String currentPlayerID) {
		this.myCurrentPlayerID = currentPlayerID;
	}

}