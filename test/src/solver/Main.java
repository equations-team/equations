package solver;

import javax.script.ScriptException;
import solver.Algebra;
//import fundementalgamemechanics.*;
//import turnsystems.GameTurns;

public class Main {

	public static void main(String[] args) throws ScriptException {
		
		//Game g = new Game();
		
		Algebra a = new Algebra("5+6","10+1");
		
		Solver manage = new Solver(a);
		
		System.out.println("Your answer equals: " + manage.calculate(a.getEquation()));
		System.out.println("The real answer equals: " + manage.calculate(a.getSolution()));		
	}

}
