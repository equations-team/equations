package state_manager;

import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import fundementalgamemechanics.*;
import solver.*;

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
	private Player currentPlayer;
	private Player nextPlayer;
	private Player lastPlayer;
	private Player goalSetter;
	private int count;
	private int numPlayers;

	/**
	 * Constructor of GSM
	 */
	public Manager(Player[] p) {
		myResources = new Mat();
		myGoal = new Goal();
		myForbidden = new Mat();
		myPermitted = new Mat();
		myRequired = new Mat();
		timer = new GameTimer(1);
		engine = new ScriptEngineManager().getEngineByName("js");

		players = p;
		numPlayers = players.length;

		for (int i = 0; i < DICENUMBER; i++) {
			if (i < REDNUMBER) {
				myResources.addToMyMat(new RedDie());
			} else if (i < REDNUMBER + BLUENUMBER) {
				myResources.addToMyMat(new BlueDie());
			} else if (i < REDNUMBER + BLUENUMBER + GREENNUMBER) {
				myResources.addToMyMat(new GreenDie());
			} else {
				myResources.addToMyMat(new BlackDie());
			}
		}

	}

	/**
	 * Roll a red dice till it is a integer number
	 * 
	 * @param p
	 */
	public void setGoalSetter(Player p) {
		p.setRedDice(-1);
		while (p.getRedDice() == -1) {
			this.rollRedDice(p);
		}

	}

	/**
	 * After the players rolled their red dices. Compare their results to decide a
	 * player to set the goal.
	 */
	public void setFirstPlayer() {
		int max = -1;
		for (int i = 0; i < players.length; i++) {
			if (players[i].getRedDice() > max) {
				max = players[i].getRedDice();
				goalSetter = players[i];
				currentPlayer = goalSetter;
				count = i;
			}
		}

		System.out.println("Goal setter is:" + goalSetter.getName());

		for (int i = 0; i < myResources.getMyMat().size(); i++) {
			myResources.getMyMat().elementAt(i).roll();
		}

	}

	/**
	 * Using for the goal setter to set the goal mat, and roll the dices
	 */
	public boolean setGoal(int[] goals) {
		if(goals.length != 5) 
			return false;
		
		try {
			StringBuilder str = new StringBuilder();

			for (int i = 0; i < goals.length; i++) {
				myGoal.addToMyMat(myResources.getMyMat().get(goals[i]));
			}

			myGoal.Read();

			for (int i = 0; i < myGoal.getMyGoal().size(); i++) {
				str.append(this.dieFaceTranslator(myGoal.getMyGoal().elementAt(i)));
			}
			goalEquation = str.toString();
			int answer = (int) engine.eval(goalEquation);
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
	 * @param player
	 * @param index    of dice in the resources mat, -1 for challenge
	 * @param gamemove decision
	 * @return
	 */
	public boolean moveDie(Player p, int index, GameMove decision) {
		Die moved;
		Player p1;

		if (index != -1) {
			if (p.getName() != currentPlayer.getName())
				return false;

			moved = myResources.getMyMat().get(index);
			if (moved == null)
				return false;

		} else {
			moved = new SpecialDie(1);
		}

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

		case CHALLENGEIMPOSSIBLE:

			if (myRequired.checkEmpty() && myPermitted.checkEmpty() && myForbidden.checkEmpty())
				return false;
			p1 = this.lastPlayer();
			// For test purpose
			System.out.println("Enter your equation according to the rule:");
			Scanner sc = new Scanner(System.in);
			String eq = sc.nextLine();

			if (this.checkInput(1, eq)) {
				this.enteringEquation(p1, sc.nextLine());
			} else {
				System.out.println("Please use the dices on the mats properly!");
			}
			;

			if (this.doingMath(p1.getEquation())) {
				System.out.println(p1.getName() + "wins!");
			} else {
				System.out.print(p.getName() + "wins!");
			}
			break;

		case CHALLENGENOW:
			if (myRequired.checkEmpty() && myPermitted.checkEmpty() && myForbidden.checkEmpty())
				return false;

			p1 = this.lastPlayer();

			// For test purpose
			System.out.println("Enter your equation according to the rule:");
			Scanner sc1 = new Scanner(System.in);
			String eq1 = sc1.nextLine();

			if (this.checkInput(2, eq1)) {
				this.enteringEquation(p1, sc1.nextLine());
			} else {
				System.out.println("Please use the dices on the mats properly!");
			}
			;

			this.enteringEquation(p, eq1);

			if (this.doingMath(p.getEquation())) {
				System.out.println(p.getName() + "wins!");
			} else {
				System.out.print(p1.getName() + "wins!");
			}

			break;

		default:
			throw new AssertionError("Please make a valid move!");
		}

		currentPlayer = this.nextPlayer();
		return true;
	}

	/**
	 * Let a player to roll a red dice before the game started.
	 */
	private void rollRedDice(Player p) {
		int redDice;
		RedDie die = new RedDie();
		die.roll();
		try {
			redDice = Integer.parseInt(this.dieFaceTranslator(die.getMyUpSide()));
			p.setRedDice(redDice);
			;
		} catch (Exception e) {
			System.out.println(" ");
		}
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

	/**
	 * 
	 * @return last player
	 */
	private Player lastPlayer() {
		count--;
		if (count < 0) {
			count = numPlayers - 1;
		}
		lastPlayer = players[count];
		return lastPlayer;
	}

	/**
	 * To check the input valid.
	 * 
	 * @param "1"      for challenge impossible, "2" for challenge now
	 * @param player's equation
	 * @return whether it obeys the rule or not
	 */
	private boolean checkInput(int flag, String str) {

		for (int i = 0; i < myForbidden.getMyMat().size(); i++) {
			if (str.contains(this.dieFaceTranslator(myForbidden.getMyMat().elementAt(i).getMyUpSide()))) {
				return false;
			}
		}

		for (int i = 0; i < myRequired.getMyMat().size(); i++) {
			if (str.contains(this.dieFaceTranslator(myRequired.getMyMat().elementAt(i).getMyUpSide()))) {
				for (int j = 0; j < str.length() - 1; j++) {
					if (str.substring(j, j + 1) == this
							.dieFaceTranslator(myRequired.getMyMat().elementAt(i).getMyUpSide())) {
						str = str.substring(0, j) + str.substring(j + 1);
						break;
					}
				}
			} else {
				return false;
			}

		}
		
		for (int i = 0; i < myPermitted.getMyMat().size(); i++) {
			if (str.contains(this.dieFaceTranslator(myPermitted.getMyMat().elementAt(i).getMyUpSide()))) {
				for (int j = 0; j < str.length() - 1; j++) {
					if(str.substring(j, j + 1) == "(" || str.substring(j, j + 1) == ")") {
						str = str.substring(0, j) + str.substring(j + 1);
					}
						
					else if (str.substring(j, j + 1) == this
							.dieFaceTranslator(myPermitted.getMyMat().elementAt(i).getMyUpSide())) {
						str = str.substring(0, j) + str.substring(j + 1);
						break;
					}
				}
			}

		}
		
		

		switch (flag) {
		case 1:
			for (int i = 0; i < myResources.getMyMat().size(); i++) {
				if (str.contains(this.dieFaceTranslator(myResources.getMyMat().elementAt(i).getMyUpSide()))) {
					for (int j = 0; j < str.length() - 1; j++) {
						if (str.substring(j, j + 1) == this
								.dieFaceTranslator(myResources.getMyMat().elementAt(i).getMyUpSide())) {
							str = str.substring(0, j) + str.substring(j + 1);
							break;
						}
					}
				}
			}
			
			if(str.length() != 0) return false;

		case 2:
			if(str.length() != 1) return false;
			
			
			for (int i = 0; i < myResources.getMyMat().size(); i++) {
				if(str == this
						.dieFaceTranslator(myResources.getMyMat().elementAt(i).getMyUpSide())){
					return true;
				}
			}
			
			return false;
		}
		
		return true;

			
		
	}

	/**
	 * Translate a dice face to string 
	 * TODO: Need to figure out the root symbol which solver can use
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

	/**
	 * Enable the player to enter their eqaution
	 * 
	 * @param player
	 * @param equation
	 */
	private void enteringEquation(Player p, String eq) {
		p.setEquation(eq);
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

}
