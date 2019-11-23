package com.game.controller;
import java.util.regex.Pattern;

import javax.script.*;

public class Solver {
	
	private Algebra a;
	
	private ScriptEngineManager man = new ScriptEngineManager();
	private ScriptEngine en = man.getEngineByName("js");

	public Solver(Algebra a2) {
		a = a2;
	}


	public Algebra getA() {
		return a;
	}

	private void setA(Algebra a) {
		this.a = a;
	}
	
	public double calculate(String eq) {
		
		double playerAnswer = 0;
		
		/**
		 * 
		 * This loop in the try catch block checks to see if the equation doesn't violate one of the rules of the game:
		 * no double digits in a submitted equation. If it does, the equation is deemed not valid and returns 0.
		 */
		
		try {
			
			for(int i = 0; i+1 < eq.length(); i++) {
			    if(eq == a.getEquation() && Character.isDigit(eq.charAt(i)) && Character.isDigit(eq.charAt(i+1)) == true) {
					System.out.println("ERROR not a valid equation -- no two digit numbers in YOUR solution.");
					return 0;
			} 
		}
		
			playerAnswer = (double) en.eval(eq);
			
		} catch (ScriptException e) {

			System.out.println("ERROR not a valid equation -- Can't evaluate.");
		}
		
		return playerAnswer;
	}
	
	/**
	 * @param playerInput
	 * @return boolean
	 * 
	 * This takes in the player's input and checks if it is equal to the goal. If not, returns false, otherwise true.
	 * 
	 */
	
	public boolean checkAnswer(String playerInput) {
		
		double checkValue = 0.0;
		
		try {
			checkValue = (double) en.eval(playerInput);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		if(checkValue == calculate(a.getSolution())) {
			return true;
		}
		
		return false;	
	}
	
	/**
	 * @param eq
	 * @return boolean
	 * 
	 * Like checkAnswer, this returns a boolean, but also like calculate, this solves the equation.
	 * The real goal is to determine if the equation can be evaluated. If yes, then return true. If not, return false.
	 * 
	 */
	
	public boolean validEquation(String eq) {
		
		try {
			
			for(int i = 0; i+1 < eq.length(); i++) {
			    if(eq == a.getEquation() && Character.isDigit(eq.charAt(i)) && Character.isDigit(eq.charAt(i+1)) == true) {
					System.out.println("ERROR not a valid equation -- no two digit numbers in YOUR solution.");
					return false;
			} 
		}
			
			en.eval(eq);
		} catch (ScriptException e) {

			return false;
		}
		
		return true;
	}
	
}
