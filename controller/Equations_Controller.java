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

	private MockView myView;
	private Game myModel;
	private int myTurn;
	private boolean myIsWon;

	/**
	 * The Constructor
	 */
	public Equations_Controller() {
		myView = new MockView(this);
		myModel = new Game();
		// This connects the drop/move methods.
		myTurn = this.determineFirst();
		myIsWon = false;
		myView.setGoalSetter(myTurn);
	}

	/////////////
	///METHODS///
	/////////////
	
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
	
	@GetMapping("/drop")
	public String drop(@RequestParam(name = "die", required = true) Die drop, Model model){
		model.addAttribute("drop", drop);
		return "drop";
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
		myModel.moveDie(index, 0);
		passTurn();

	}

	/**
	 * Skip to the next persons turn. It should be 1 if the last turn is player 3's.
	 */
	public void passTurn() {
		if (myTurn == 3) {
			myTurn = 1;
			myView.setTurn(1);
		} else {
			myTurn++;
			myView.setTurn(myTurn);
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
	
	public void challengeImpossible(Algebra solution) {
		//myGame.Solve...()
	}
	
	public void challengeNow() {
		//myGame.Solve...()
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

	public boolean isWon() {
		return myIsWon;
	}

	public void setIsWon(boolean w) {
		this.myIsWon = w;
	}
	
	public Game getGame() {
		return myModel;
	}
	
	public void setGame(Game g) {
		myModel = g;
	}

}
