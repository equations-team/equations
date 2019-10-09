package state_manager;


public class Algebra {

	private String solution;
	private String equation;
	
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
