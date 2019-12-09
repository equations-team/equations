package gamestatemanager;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import fundementalgamemechanics.*;
import Solver.*;

/**
 * GSM of equations game
 * 
 * @author Ruby
 *
 */
public class Manager implements Manger_Reader {
	// Constants
	public static final int DICENUMBER = 24;
	public static final int REDNUMBER = 6;
	public static final int GREENNUMBER = 6;
	public static final int BLACKUMBER = 6;
	public static final int BLUENUMBER = 6;

	// Data members
	private Solver solver;
	private GameTimer timer;
	private Player[] players;
	private ScriptEngine engine;

	private Mat myResources;
	private Goal myGoal;
	private Mat myForbidden;
	private Mat myPermitted;
	private Mat myRequired;

	private String goalEquation;
	private String checkEquation;
	private Player currentPlayer;
	private Player nextPlayer;
	private Player lastPlayer;
	private Player goalSetter;
	private int count;
	private int numPlayers;
	private boolean gameEnd;

	/**
	 * Constructor of GSM
	 */
	public Manager(Player[] p, Dice[] dice) {
		myResources = new Mat();
		myGoal = new Goal();
		myForbidden = new Mat();
		myPermitted = new Mat();
		myRequired = new Mat();
		gameEnd = false;
//		timer = new GameTimer(1);
		engine = new ScriptEngineManager().getEngineByName("js");

		players = p;
		numPlayers = players.length;

		for (int i = 0; i < dice.length; i++) {
			myResources.addToMyMat(dice[i]);
		}
	}


	/**
	 * After the players rolled their red dices. Compare their results to decide a
	 * player to set the goal.
	 */
	public void setFirstPlayer() {
		int first = new Random().nextInt(players.length);
		for (int i = 0; i < players.length; i++) {
			
			if(i == first) {
				goalSetter = players[i];
				currentPlayer = goalSetter;
				count = i;
			}
			
		}

		for (int i = 0; i < myResources.getMyMat().size(); i++) {
			myResources.getMyMat().elementAt(i).roll();
		}

	}

	/**
	 * Using for the goal setter to set the goal mat, and roll the dices
	 */
	public boolean setGoal(int[] goals) {
		if (goals.length != 5)
			return false;

		try {
			StringBuilder str = new StringBuilder();

			for (int i = 0; i < goals.length; i++) {
				myGoal.addToMyMat(myResources.getMyMat().get(goals[i]));
			}

			myGoal.Read();

			for (int i = 0; i < myGoal.getMyGoal().size(); i++) {
				str.append(this.dieFaceTranslatorGoal(myGoal.getMyGoal().elementAt(i)));
			}
			goalEquation = str.toString();
			double answer = (double) engine.eval(goalEquation);
			lastPlayer = currentPlayer;
			currentPlayer = this.nextPlayer();
			return true;

		} catch (ScriptException e) {
			System.out.println("ERROR not a valid equation -- Please set a valid goal");
			return false;
		}

	}

	/**
	 * Initialize a solver with player's equation and goal.
	 */
	public void setSolver(String equation) {

		solver = new Solver(new Algebra(equation, goalEquation));
	}

	/**
	 * Basic move dice operation.
	 * 
	 * @param index of dice in the resources mat
	 * @param gamemove decision
	 * @return
	 */
	public boolean moveDie(int index, GameMove decision) {

		Dice moved = myResources.getMyMat().get(index);
		if (moved == null)
			return false;

		switch (decision) {
		case ADDFORBIDDEN:
			if (moved.getMyUpSide() == null)
				return false;
			myForbidden.addToMyMat(moved);
			myResources.removeDie(moved);
			break;
		case ADDREQUIRED:
			if (moved.getMyUpSide() == null)
				return false;
			myRequired.addToMyMat(moved);
			myResources.removeDie(moved);

			break;
		case ADDPERMITTED:
			if (moved.getMyUpSide() == null)
				return false;
			myPermitted.addToMyMat(moved);
			myResources.removeDie(moved);

			break;

		default:
			throw new AssertionError("Please make a valid move!");
		}

		lastPlayer = currentPlayer;
		currentPlayer = this.nextPlayer();
		return true;
	}

	public boolean challenge(int flag, String PlayerEq, String ThirdPlayerEq) {
		if (myRequired.checkEmpty() && myPermitted.checkEmpty() && myForbidden.checkEmpty())
			return false;
		if(PlayerEq == null)
			return false;
		if (this.getThirdPlayer() != null) {
			switch (flag) {
			case 1:

				if (ThirdPlayerEq == null) {
						if (this.doingMath(PlayerEq)) {
							this.getLastPlayer().setScore(6);
							this.getCurrentPlayer().setScore(2);
							this.getThirdPlayer().setScore(4);

						} else {
							this.getLastPlayer().setScore(2);
							this.getCurrentPlayer().setScore(6);
							this.getThirdPlayer().setScore(6);
						}
					

				} else {
						if(this.doingMath(PlayerEq) && this.doingMath(ThirdPlayerEq)) {
							this.getCurrentPlayer().setScore(2);
							this.getThirdPlayer().setScore(6);
							this.getLastPlayer().setScore(6);
						}else if(this.doingMath(PlayerEq)&& (!this.doingMath(ThirdPlayerEq))) {
							this.getLastPlayer().setScore(6);
							this.getThirdPlayer().setScore(4);
							this.getCurrentPlayer().setScore(2);
						}else if(this.doingMath(ThirdPlayerEq)&& (!this.doingMath(PlayerEq))) {
							this.getThirdPlayer().setScore(6);
							this.getCurrentPlayer().setScore(2);
							this.getLastPlayer().setScore(2);
						}else {
							this.getCurrentPlayer().setScore(6);
							this.getLastPlayer().setScore(2);
							this.getThirdPlayer().setScore(4);
						}
						
					
				}

				break;

			case 2:

				if (ThirdPlayerEq == null) {
						if (this.doingMath(PlayerEq)) {
							this.getLastPlayer().setScore(2);
							this.getThirdPlayer().setScore(4);
							this.getCurrentPlayer().setScore(6);

						} else {
							this.getLastPlayer().setScore(6);
							this.getThirdPlayer().setScore(6);
							this.getCurrentPlayer().setScore(2);;
						}
					

				} else {
						if (this.doingMath(PlayerEq) && this.doingMath(ThirdPlayerEq)) {
							this.getLastPlayer().setScore(2);
							this.getCurrentPlayer().setScore(6);
							this.getThirdPlayer().setScore(4);
						} else if(this.doingMath(PlayerEq) && (!this.doingMath(ThirdPlayerEq))){
							this.getLastPlayer().setScore(2);
							this.getCurrentPlayer().setScore(6);
							this.getThirdPlayer().setScore(4);
						}else if(this.doingMath(ThirdPlayerEq) && (!this.doingMath(PlayerEq))) {
							this.getThirdPlayer().setScore(6);
							this.getCurrentPlayer().setScore(2);
							this.getLastPlayer().setScore(2);
						}else {
							this.getLastPlayer().setScore(6);
							this.getCurrentPlayer().setScore(2);
							this.getThirdPlayer().setScore(4);
						}
					
				}
				break;
				
				default:
					return false;
			}
		} else {
			switch (flag) {
			case 1:

					if (this.doingMath(PlayerEq)) {
						this.getLastPlayer().setScore(6);
						this.getCurrentPlayer().setScore(2);
					} else {
						this.getLastPlayer().setScore(2);
						this.getCurrentPlayer().setScore(6);
					}
				

				break;

			case 2:
					if (this.doingMath(PlayerEq)) {
						this.getLastPlayer().setScore(2);
						this.getCurrentPlayer().setScore(6);
					} else {
						this.getLastPlayer().setScore(6);
						this.getCurrentPlayer().setScore(2);
					}
				
				
				break;
				
			default:
				return false;
			}

		}
		this.setGameEnd(true);
		return true;
	}
	
	public Vector<Player> getWinner() {
		Vector<Player> temp = new Vector<Player>();
		for(int i = 0; i <players.length; i++) {
			if(players[i].getScore() == 6) {
				temp.add(players[i]);
			}
		}
		
		return temp;
	}

	/**
	 * To check the input valid.
	 * 
	 * @param "1"      for challenge impossible, "2" for challenge now
	 * @param player's equation
	 * @return whether it obeys the rule or not
	 */
	public boolean checkInput(int flag, String str) {
		checkEquation = str;
		if (this.checkForbidden(checkEquation) && this.checkRequired(checkEquation) && this.checkPermitted(checkEquation)
				&& this.checkChallenge(flag, checkEquation))
			return true;

		return false;
	}

	public Player startTurn() {
		return currentPlayer;
	}

	public Player getThirdPlayer() {
		for (int i = 0; i < players.length; i++) {
			if (players[i] != currentPlayer && players[i] != lastPlayer) {
				return players[i];
			}
		}
		return null;
	}

	/**
	 * 
	 * @return next player
	 */
	private Player nextPlayer() {
		count++;
		if (count == numPlayers) {
			count = 0;
		}
		nextPlayer = players[count];
		return nextPlayer;
	}


	private boolean checkForbidden(String str) {
		for (int i = 0; i < myForbidden.getMyMat().size(); i++) {
			if (str.contains(this.dieFaceTranslator(myForbidden.getMyMat().elementAt(i).getMyUpSide()))) {
				return false;
			}
		}
		return true;
	}

	private boolean checkRequired(String str) {
		for (int i = 0; i < myRequired.getMyMat().size(); i++) {
		
			if (str.contains(this.dieFaceTranslator(myRequired.getMyMat().elementAt(i).getMyUpSide()))) {
				for (int j = 0; j < str.length(); j++) {
					
					if (str.substring(j, j + 1).compareTo(this
							.dieFaceTranslator(myRequired.getMyMat().elementAt(i).getMyUpSide())) == 0) {
						str = str.substring(0, j) + str.substring(j + 1);
						break;
					}
				}
			} else {
				return false;
			}

		}
		checkEquation = str;
		return true;
	}

	private boolean checkPermitted(String str) {
		for (int i = 0; i < myPermitted.getMyMat().size(); i++) {
			if (str.contains(this.dieFaceTranslator(myPermitted.getMyMat().elementAt(i).getMyUpSide()))) {
				for (int j = 0; j < str.length(); j++) {
					if (str.substring(j, j + 1).compareTo(this
							.dieFaceTranslator(myPermitted.getMyMat().elementAt(i).getMyUpSide())) == 0) {
						str = str.substring(0, j) + str.substring(j + 1);
						break;
					}
				}
			}

		}
		
		for(int i =0; i<str.length(); i++) {
			if (str.substring(i, i + 1).compareTo("(") == 0 || str.substring(i, i + 1).compareTo(")") == 0 ) {
				str = str.substring(0, i) + str.substring(i + 1);
				i--;
				
			}
		}
		
		checkEquation = str;
		return true;
	}

	private boolean checkChallenge(int flag, String str) {
		switch (flag) {
		case 1:
			for (int i = 0; i < myResources.getMyMat().size(); i++) {
				if (str.contains(this.dieFaceTranslator(myResources.getMyMat().elementAt(i).getMyUpSide()))) {
					for (int j = 0; j < str.length(); j++) {
						if (str.substring(j, j + 1).compareTo(this
								.dieFaceTranslator(myResources.getMyMat().elementAt(i).getMyUpSide())) == 0) {
							str = str.substring(0, j) + str.substring(j + 1);
							break;
						}
					}
				}
			}

			if (str.length() != 0)
				return false;
			
			break;

		case 2:
			if (str.length() != 1)
				return false;

			for (int i = 0; i < myResources.getMyMat().size(); i++) {
				if (str.compareTo(this.dieFaceTranslator(myResources.getMyMat().elementAt(i).getMyUpSide())) == 0) {
					return true;
				}
			}

			return false;
		}
		
		

		return true;
	}

	/**
	 * Translate a dice face to string TODO: Need to figure out the root symbol
	 * which solver can use
	 * 
	 * @param diceface
	 * @return String
	 */
	private String dieFaceTranslator(DiceFace d) {
		switch (d) {
		case ONE:
			return "1";

		case TWO:
			return "2";

		case THREE:
			return "3";

		case FOUR:
			return "4";

		case FIVE:
			return "5";

		case SIX:
			return "6";

		case SEVEN:
			return "7";

		case EIGHT:
			return "8";

		case NINE:
			return "9";

		case ZERO:
			return "0";

		case ADDITION:
			return "+";

		case SUBTRACTION:
			return "-";

		case MULTIPLICATION:
			return "*";

		case DIVISION:
			return "/";

		case POWER:
			return "^";

		case ROOT:
			return "?";

		case LEFT:
			return "(";

		case RIGHT:
			return ")";

		}
		return null;
	}
	
	private String dieFaceTranslatorGoal(DiceFace d) {
		switch (d) {
		case ONE:
			return "1.0";

		case TWO:
			return "2.0";

		case THREE:
			return "3.0";

		case FOUR:
			return "4.0";

		case FIVE:
			return "5.0";

		case SIX:
			return "6.0";

		case SEVEN:
			return "7.0";

		case EIGHT:
			return "8.0";

		case NINE:
			return "9.0";

		case ZERO:
			return "0.0";

		case ADDITION:
			return "+";

		case SUBTRACTION:
			return "-";

		case MULTIPLICATION:
			return "*";

		case DIVISION:
			return "/";

		case POWER:
			return "^";

		case ROOT:
			return "?";

		case LEFT:
			return "(";

		case RIGHT:
			return ")";

		}
		return null;
	}

	/**
	 * Once the player gives a valid equation, set the solver to check the answer
	 * 
	 * @param equation
	 * @return correct or not
	 */
	private boolean doingMath(String eq) {
		this.setSolver(eq);
		return solver.checkAnswer(eq);
	}

	public Player getNextPlayer() {
		return nextPlayer;
	}

	public Player getLastPlayer() {
		return lastPlayer;
	}

	public Player getGoalSetter() {
		return goalSetter;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Mat getMyResources() {
		return myResources;
	}

	public String getGoalEquation() {
		return goalEquation;
	}

	public Goal getMyGoal() {
		return myGoal;
	}

	public Mat getMyForbidden() {
		return myForbidden;
	}

	public Mat getMyPermitted() {
		return myPermitted;
	}

	public Mat getMyRequired() {
		return myRequired;
	}

	public Solver getSolver() {
		return solver;
	}


	public boolean isGameEnd() {
		return gameEnd;
	}


	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}

	
	public int[] getScores() {
		int[] scores = new int[numPlayers];
		for(int i = 0;i < players.length;i++)
			scores[i] = players[i].getScore();
		return scores;
	}
}
