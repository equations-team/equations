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
		
		if(eq.contains(" ") == true) {
		
		eq = eq.replace(' ', '(');
		
		int odd = 0;
		int occur = 0;
		
		for(int i = 0; i < eq.length(); i++){

			if(eq.charAt(i) == '(' && odd == 0) {
				odd++;
				occur++;
			}else if(eq.charAt(i) == '(' && odd == 1) {
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
		
		String rootNumber = eq.substring(rootIndex + 1);
		
		eq = eq.replace("?" + rootNumber, "Math.sqrt(" + rootNumber + ")");
		}
	}
		
		//Goal
		
		if(goal.contains(" ") == true) {
			
			goal = goal.replace(' ', '(');
			
			int odd = 0;
			int occur = 0;
			
			for(int i = 0; i < goal.length(); i++){

				if(goal.charAt(i) == '(' && odd == 0) {
					odd++;
					occur++;
				}else if(goal.charAt(i) == '(' && odd == 1) {
					occur++;
					StringBuilder n = new StringBuilder(goal);
					n.setCharAt(i, ')');
					goal = n.toString();
					odd--;
				}
				
			}
			
			if(occur % 2 != 0) {
				goal = goal + ")";
			}
			
			if(goal.contains("?") == true) {
				
			int rootIndex = goal.indexOf("?");
			
			String rootNumber = goal.substring(rootIndex + 1);
			
			goal = goal.replace("?" + rootNumber, "Math.sqrt(" + rootNumber + ")");
			}
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
