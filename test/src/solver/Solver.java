package solver;
import java.util.regex.Pattern;
import javax.script.*;


interface Calculator{
	public int calculate(String equation);
	public boolean checkAnswer(int input);
}

 	class Solver implements Calculator {
	
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
	
	public int calculate(String eq) {
		
		int playerAnswer = 0;
		
		try {
			
			for(int i = 0; i+1 < eq.length(); i++) {
			    if(eq == a.getEquation() && Character.isDigit(eq.charAt(i)) && Character.isDigit(eq.charAt(i+1)) == true) {
					System.out.println("ERROR not a valid equation -- no two digit numbers in YOUR solution.");
					return 0;
			} 
		}
		
			playerAnswer = (int) en.eval(eq);
		} catch (ScriptException e) {

			System.out.println("ERROR not a valid equation -- Can't evaluate.");
		}
		
		return playerAnswer;
	}
	
	public boolean checkAnswer(int playerInput) {
		
		if(playerInput == calculate(a.getSolution())) {
			return true;
		}
		
		return false;	
	}


	
}
