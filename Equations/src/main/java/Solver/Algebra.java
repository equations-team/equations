package Solver;

import gamestatemanager.*;


public class Algebra {

	private String solution;
	private String equation;
	
	private Manager m;
	
	/**
	 * @param eq
	 * @param goal
	 * 
	 * Takes equation (the player's) and the goal (set by a player at the start).
	 * This simply stores them and returns them when asked.
	 * 
	 */
	
	public Algebra(String eq, String goal){
		
		solution = goal;
		equation = eq;	

	}
	
	
	public String getSolution() {
		return solution;
	}
	private void setSolution(String solution) {
		this.solution = solution;
	}
	
	public String getEquation() {
		return equation;
	}
	private void setEquation(String equation) {
		this.solution = equation;
	}
	
}
