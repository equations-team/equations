package localhostgame;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import Solver.Algebra;
import Solver.Solver;
import dice.Dice;
import dice.DiceFace;
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
public class EquationsController {

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
	private Die[] myDice;
	private ImageIcon[] myDieImages;
	private int myGoalCounter;
	private int[] myGoalIndex;
	private GameView myGameView;

	/**
	 * The Constructor
	 */	public EquationsController() {
		myDieImages = new ImageIcon[24];
		myGoalCounter = 0; // This is to stop players from adding too many dice to the goal.
		myGoalIndex = new int[5];
		myModel = new Game();
		myDice = new Die[24];
		myPlayerCount = 2;
		myPlayers = new Player[myPlayerCount];
		this.createPlayerArray("P1", "P2", "P3");
		myManager = new Manager(myPlayers, myModel.getMyResources().getMyMat());
		this.determineFirst(myPlayers);
		for (int i = 0; i < 23; i++) {
			myDice[i] = myManager.getMyResources().getMyMat().elementAt(i);
		}
		myDieImages = this.loadDice();
		myGameView = new GameView(this);
	}

	public static void main(String[] args) {
		new EquationsController();
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
	}

	/**
	 * Roll dice and get the highest one. Each player rolls a die, the highest goes
	 * first.
	 * 
	 * @return high, the highest number.
	 */

	private void determineFirst(Player[] players) {
		myManager.setFirstPlayer();
		if (players[0] == myManager.getCurrentPlayer()) {
			myCurrentTurn = 1;
			myCurrentPlayerID = players[0].getName();
		}
		if (players[1] == myManager.getCurrentPlayer()) {
			myCurrentPlayerID = players[1].getName();
			myCurrentTurn = 2;
		}
		if (myPlayerCount > 2) {
			if (players[2] == myManager.getCurrentPlayer())
				myCurrentTurn = 3;
			myCurrentPlayerID = players[2].getName();

		}
	}

	/**
	 * This takes in view knowledge of the mat. More specifically, what the player
	 * is requesting to be put on the 'goal' portion of the mat. Update the model
	 * with this information. Index is the position of the die in a list of die.
	 * 
	 * @param index
	 */
	public void goalSet(int index) {
		// Send die to goal
		if (myGoalCounter == 5) {
			System.out.println("You have reached the maximum number of dice. ");
			this.finalizeGoal(myGoalIndex);
			return;
		}

		myGoalIndex[myGoalCounter] = index;
		myGoalCounter++;
		int remainingDice = 6 - myGoalCounter;
		System.out.println("Added die. You can add " + remainingDice + " more dice to the goal. ");
	}

	// Finalizes the goal. Selected indices will be used for the rest of the game.
	public void finalizeGoal(int[] goal) {
		myManager.setGoal(goal);
	}

	/**
	 * Skip to the next persons turn. It should be 1 if the last turn is player 3's.
	 */
	public void passTurn() {
		if (myCurrentTurn != 3) {
			myCurrentTurn++;
			myManager.setCurrentPlayer(myPlayers[myCurrentTurn]);
		} else {
			myCurrentTurn = 1;
			myManager.setCurrentPlayer(myPlayers[myCurrentTurn]);
		}
	}

	/**
	 * Moves a die to one of three locations, forbidden, required, or allowed.
	 * 
	 * @param location
	 * @param index
	 * @return true if the die was moved properly, false if something bad happened
	 *         along the way.
	 */
	// @GetMapping ("/View")
	public boolean moveDie(Integer mat, Integer index) {
		switch (mat) {
		case 0:
			// Forbidden
			myManager.moveDie(index, GameMove.ADDFORBIDDEN);
			return true;
		case 1:
			// Required
			myManager.moveDie(index, GameMove.ADDREQUIRED);
			return true;
		case 2:
			// Allowed
			myManager.moveDie(index, GameMove.ADDPERMITTED);
			return true;
		}
		// If we're lucky, this shouldn't occur.
		System.out.println("ERROR");
		return false;
	}

	/**
	 * Check if the challenge is impossible. The function will return false if the
	 * equation is not valid. 2 for now 1 for impossible
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

	/**
	 * This method will load dice based on what the die face in resources is.
	 */
	public ImageIcon[] loadDice() {
		for (int i = 0; i < 23; i++) {

			// Zero, one, two, and three can only be red or blue.

			if (myDice[i].getMyUpSide() == DiceFace.ONE) {
				if (myDice[i].getColor() == "RED") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Red1.png"));
				}

				if (myDice[i].getColor() == "BLUE") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Blue1.png"));
				}
			}

			if (myDice[i].getMyUpSide() == DiceFace.TWO) {
				if (myDice[i].getColor() == "RED") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Red2.png"));
				}

				if (myDice[i].getColor() == "BLUE") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Blue2.png"));
				}
			}

			if (myDice[i].getMyUpSide() == DiceFace.THREE) {
				if (myDice[i].getColor() == "RED") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Red3.png"));
				}

				if (myDice[i].getColor() == "BLUE") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Blue3.png"));
				}
			}

			// Four is either blue or green
			if (myDice[i].getMyUpSide() == DiceFace.FOUR) {
				if (myDice[i].getColor() == "GREEN") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Green4.png"));
				}

				if (myDice[i].getColor() == "BLUE") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Blue4.png"));
				}
			}

			// Five and six are green

			if (myDice[i].getMyUpSide() == DiceFace.FIVE) {
				myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Green5.png"));
			}

			if (myDice[i].getMyUpSide() == DiceFace.SIX) {
				myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Green6.png"));
			}

			// Seven, eight, and nine are black

			if (myDice[i].getMyUpSide() == DiceFace.SEVEN) {
				myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Black7.png"));
			}

			if (myDice[i].getMyUpSide() == DiceFace.EIGHT) {
				myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Black8.png"));
			}

			if (myDice[i].getMyUpSide() == DiceFace.NINE) {
				myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Black9.png"));
			}

			// Zero can be blue or red

			if (myDice[i].getMyUpSide() == DiceFace.ZERO) {
				if (myDice[i].getColor() == "RED") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Red0.png"));
				}

				if (myDice[i].getColor() == "BLUE") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/Blue0.png"));
				}
			}

			// Addition can be black or red
			if (myDice[i].getMyUpSide() == DiceFace.ADDITION) {
				if (myDice[i].getColor() == "RED") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/RedPlus.png"));
				}

				if (myDice[i].getColor() == "BLACK") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/BlackPlus.png"));
				}
			}

			// Multiplication can be blue or green
			if (myDice[i].getMyUpSide() == DiceFace.MULTIPLICATION) {
				if (myDice[i].getColor() == "GREEN") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/GreenMulti.png"));
				}

				if (myDice[i].getColor() == "BLUE") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/BlueMulti.png"));
				}
			}

			// Subtraction can be red or green
			if (myDice[i].getMyUpSide() == DiceFace.SUBTRACTION) {
				if (myDice[i].getColor() == "RED") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/RedMinus.png"));
				}

				if (myDice[i].getColor() == "GREEN") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/GreenMinus.png"));
				}
			}

			// Division can be black or blue
			if (myDice[i].getMyUpSide() == DiceFace.DIVISION) {
				if (myDice[i].getColor() == "BLACK") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/BlackDivision.png"));
				}

				if (myDice[i].getColor() == "BLUE") {
					myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/BlueDivision.png"));
				}
			}

			// Powers are green
			if (myDice[i].getMyUpSide() == DiceFace.POWER) {
				myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/GreenPower.png"));

			}

			// Roots are black
			if (myDice[i].getMyUpSide() == DiceFace.ROOT) {
				myDieImages[i] = new ImageIcon(getClass().getResource("/myImages/BlackRoot.png"));
			}

		}
		return myDieImages;
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

	public Manager getManager() {
		return myManager;
	}

	public ImageIcon[] getDieImages() {
		return myDieImages;
	}

	public int[] getGoalIndex() {
		return myGoalIndex;
	}

	public void setGoalIndex(int[] goal) {
		myGoalIndex = goal;
	}

	public int getGoalCounter() {
		return myGoalCounter;
	}

	public void setGoalCounter(int counter) {
		myGoalCounter = counter;
	}

}