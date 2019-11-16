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
		
		eq = eq.replace(' ', '(');
		int odd = 0;
		int occur = 0;
		
		for(int i = 0; i < eq.length(); i++){

			if(eq.charAt(i) == '(' && odd == 0) {
				odd++;
				occur++;
			}else if(eq.charAt(i) == '(' && odd == 1) {
				//String n = eq.substring(i,i+1);
				occur++;
				StringBuilder n = new StringBuilder(eq);
				n.setCharAt(i, ')');
				eq = n.toString();
				odd--;
			}
			
		}
		
		if(occur % 2 != 0) {
			eq = eq + ")";
		}
		
		if(eq.contains("?") == true) {
			
			int rootIndex = eq.indexOf("?");
			
			String rootNumber = eq.substring(rootIndex+1);

			eq = "Math.sqrt(" + rootNumber + ")";
		}
		
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
