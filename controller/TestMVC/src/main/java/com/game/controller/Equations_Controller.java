package com.game.controller;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for equations. It may also be called the presenter or medium for
 * the user to interact. If the user moves a cube, then it is up to the
 * controller to update that move to the model and view.
 * 
 * @author Stephen Mingolelli
 *
 */
@Controller
public class Equations_Controller implements Controller_Manager{

	// private MockView myView;
	private Game myModel;
	private int myTurn;
	private Map myWebModel;
	private String myGoal;
	private String[] myPlayers;
	private String myCurrentPlayerID;

	/**
	 * The Constructor
	 */
	public Equations_Controller() {
		// myView = new MockView(this);
		//myModel = new Game();
		//myTurn = this.determineFirst();
		// myView.setGoalSetter(myTurn);
		//myPlayers = new String[3];
		//myWebModel = new HashMap<String, Object>();
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
		if (high == p1) {
			myTurn = 1;
		} else if (high == p2) {
			myTurn = 2;
		} else if (high == p3) {
			myTurn = 3;
		}
		return myTurn;
	}

	// The controller's interaction with the other player's view. Informs others of
	// new movement.
	//@GetMapping("/View")
	public String apprise(Model model, Die moved) {
		System.out.println(moved.getMyUpSide().toString());
		model.addAttribute("game", moved);
		this.passTurn();
		return "View";

	}

	// Controller setting the goal from input from view.
	//@GetMapping("/View")
	public String goalSet(Model model, String goal, String playerID) {
		myGoal = goal;
		model.addAttribute("game", goal);
		for (int i = 0; i < 2; i++) {
			if (playerID == myPlayers[i])
				myTurn = i;
		}
		myCurrentPlayerID = playerID;
		passTurn();
		return "View";
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
			myCurrentPlayerID = myPlayers[0];
		} else {
			myTurn++;
			myCurrentPlayerID = myPlayers[myTurn-1];
		}
	}

	/**
	 * Moves a die to one of three locations, forbidden, required, or allowed.
	 * 
	 * @param location
	 * @param index
	 */
	//@GetMapping ("/View")
	public void moveDie(Integer mat, Integer index, Model model) {
		mat = 1;
		index = 2;
		switch (mat) {
		case 0:
			Die movedF = myModel.getMyResources().getMyMat().elementAt(index);
			this.apprise(model, movedF);
			//return this.moveToForbidden(index);
		case 1:
			Die movedR = myModel.getMyResources().getMyMat().elementAt(index);
			this.apprise(model, movedR);
			boolean snap = this.moveToRequired(index);
			model.addAttribute("boolean", snap);
			model.addAttribute("mat", mat);
			model.addAttribute("index", index);
			//return snap;
		case 2:
			Die movedA = myModel.getMyResources().getMyMat().elementAt(index);
			this.apprise(model, movedA);
			//return this.moveToAllowed(index);

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
	 * 
	 * @param playerInput
	 */
	public boolean challengeImpossible(String playerInput) {
		Algebra a = new Algebra(playerInput, myGoal);
		Solver s = new Solver(a);
		if (s.validEquation(playerInput) == false)
			return false;

		// If this is true, then the challenge was not impossible.
		if (s.checkAnswer(playerInput) == true)
			return false;

		return true;
	}

	/**
	 * Check if the challenge is possible. The function will return true if the
	 * player's input is correct and valid, else, false.
	 * 
	 * @param playerInput
	 */
	public boolean challengeNow(String playerInput) {
		Algebra a = new Algebra(playerInput, myGoal);
		Solver s = new Solver(a);
		if (s.validEquation(playerInput) == false)
			return false;

		// If this is true, then the player got the correct answer
		if (s.checkAnswer(playerInput) == true)
			return true;

		return true;
	}

//	@GetMapping("/View")
	/**
	 * A method that will get the dice ID from the view. @RequestParam will bind
	 * that dice id to the int diceID. It should also get what mat it is going to
	 * (Forbidden, required, permitted, etc)
	 * @param diceID
	 * @param model
	 * @return /game 
	 */
	public String diceID(@RequestParam(value = "diceID") int diceID, 
			@RequestParam(value = "matID") int matLocation, Model model) {
		
		model.addAttribute("diceID", diceID);
		this.moveDie(matLocation, diceID, model);
		return "View";

	}
	
	/**
	 * This method loads the die face
	 * @param model
	 * @return
	 */
	//RequestMapping("/View")
	//@GetMapping
	public String loadDice(Map<String, Object> model){
	    for(int i = 1; i < 24; i++){
	        model.put("dieId", i);
	        String imageLink = myModel.getMyResources().getMyMat().get(i).getMyUpSide().toString();
	        ImageIcon image = new ImageIcon("/images/Red0.svg");
	        if(imageLink == "SUBTRACTION") {
	        	model.put(image.toString(), image);
	        }
	        model.put("link", imageLink);
	    }
	    return "Base";
	}
	
	@RequestMapping("/")
	public String locatePage() {
		return "TestView.jsp";
	}

	/////////////////////////
	/// GETTERS AND SETTERS///
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

	public String getGoal() {
		return myGoal;
	}

	public void setGoal(String goal) {
		this.myGoal = goal;
	}

	public String getCurrentPlayerID() {
		return myCurrentPlayerID;
	}

	public void setCurrentPlayerID(String currentPlayerID) {
		this.myCurrentPlayerID = currentPlayerID;
	}


}