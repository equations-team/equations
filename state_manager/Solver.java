package state_manager;
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
	
	public int calculate(String eq) {
		
		int playerAnswer = 0;
		
		try {
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
