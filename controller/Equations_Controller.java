import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fundementalgamemechanics.Die;
import fundementalgamemechanics.Game;

/**
 * Controller for equations. It may also be called the presenter or medium for
 * the user to interact. If the user moves a cube, then it is up to the
 * controller to update that move to the model and view.
 * 
 * @author Stephen Mingolelli
 *
 */

@Controller
public class Equations_Controller {

	//private MockView myView;
	private Game myModel;
	private int myTurn;
	private Model myWebModel;
	private String myGoal;
	private String[] myPlayers;
	private String myCurrentPlayerID;

	/**
	 * The Constructor
	 */
	public Equations_Controller() {
		//myView = new MockView(this);
		myModel = new Game();
		myTurn = this.determineFirst();
		//myView.setGoalSetter(myTurn);
		myPlayers = new String[3];
	}

	/////////////
	///METHODS///
	/////////////
	
	/**
	 * Takes in 3 player IDs ands puts them in the array.
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public void createPlayerArray(String p1, String p2, String p3) {
		myPlayers[0] = p1;
		myPlayers[1] = p2;
		myPlayers[2] = p3;
	}


	/**
	 * Roll dice and get the highest one. Each player rolls a die, the highest goes
	 * first.
	 * 
	 * @return high, the highest number.
	 */
	
	private int determineFirst() {
		int p1 = 0;
		int p2 = 0;
		int p3 = 0;
		Random random = new Random();
		p1 = random.nextInt(10 - 1 + 1) + 1;
		p2 = random.nextInt(10 - 1 + 1) + 1;
		p3 = random.nextInt(10 - 1 + 1) + 1;
		int high = Math.max(p1, Math.max(p2, p3));
		myTurn = high;
		if(high == p1) {
			myTurn = 1;
		}else if(high == p2){
			myTurn = 2;
		}else if(high == p3) {
			myTurn = 3;
		}
		return myTurn;
	}
	
	// The controller's interaction with the other player's view. Informs others of new movement.
	@GetMapping("/game")
	public String apprise(Model model, Die moved) {
		model.addAttribute("game", moved);
		this.passTurn();
		return "game";
		
	}
	
	// Controller setting the goal from input from view.
	@GetMapping("/game")
	public String goalSet(Model model, String goal, String playerID) {
		myGoal = goal;
		model.addAttribute("game", goal);
		for(int i = 0; i < 2; i++) {
			if(playerID == myPlayers[i])
				myTurn = i;
		}
		myCurrentPlayerID = playerID;
		passTurn();
		return "game";
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
		if (myTurn == 3) {
			myTurn = 1;
			myCurrentPlayerID = myPlayers[1];
		} else {
			myTurn++;
			myCurrentPlayerID = myPlayers[myTurn];
		}
	}

	/**
	 * Moves a die to one of three locations, forbidden, required, or allowed.
	 * 
	 * @param location
	 * @param index
	 */
	public boolean moveDie(int location, int index) {
		switch (location) {
		case 0:
			Die moved = myModel.getMyForbidden().getMyMat().elementAt(index);
			this.apprise(myWebModel, moved);
			return this.moveToForbidden(index);
		case 1:
			return this.moveToRequired(index);
		case 2:
			return this.moveToAllowed(index);

		}

		// If we're lucky, this shouldn't occur.
		System.out.println("ERROR");
		return false;

	}

	/**
	 * Move die to forbidden, uses die from resources at the index position.
	 * 
	 * @param index
	 * @return true, if successful.
	 */
	private boolean moveToForbidden(int index) {
		myModel.moveDie(index, 1);
		if(myModel.moveDie(index, 1) == -1) { return false; }
		 
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
		 if(myModel.moveDie(index, 2) == -1) { return false; }
		 
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
		 if(myModel.moveDie(index, 3) == -1) { return false; }
		 
		return true;
	}
	
	/**
	 * Check if the challenge is impossible. The function will return false if the equation is not valid.
	 * @param playerInput
	 */
	public boolean challengeImpossible(String playerInput) {
		Algebra a = new Algebra(playerInput, myGoal);
		Solver s = new Solver(a);
		if(s.validEquation(playerInput)==false)
			return false;
		
		// If this is true, then the challenge was not impossible.
		if(s.checkAnswer(playerInput)==true)
			return false;
		
		return true;
	}
	
	/**
	 * Check if the challenge is possible. The function will return true if the player's
	 * input is correct and valid, else, false.
	 * @param playerInput
	 */
	public boolean challengeNow(String playerInput) {
		Algebra a = new Algebra(playerInput, myGoal);
		Solver s = new Solver(a);
		if(s.validEquation(playerInput)==false)
			return false;
		
		// If this is true, then the player got the correct answer
		if(s.checkAnswer(playerInput)==true)
			return true;
		
		return true;
	}
	
	
	
	/////////////////////////
	///GETTERS AND SETTERS///
	/////////////////////////

	public int getTurn() {
		return myTurn;
	}

	public void setTurn(int t) {
		this.myTurn = t;
	}
	
	public Game getGame() {
		return myModel;
	}
	
	public void setGame(Game g) {
		myModel = g;
	}
	
	public String[] getPlayers() {
		return myPlayers;
	}

	public void setPlayers(String[] players) {
		this.myPlayers = players;
	}
	
	public String getCurrentPlayer() {
		return myCurrentPlayerID;
	}
	
	public void setCurrentPlayer(String currentPlayerID) {
		myCurrentPlayerID = currentPlayerID;
	}

}
