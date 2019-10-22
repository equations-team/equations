package solver;
import javax.script.ScriptException;

import solver.Algebra;

public class Main {

	public static void main(String[] args) throws ScriptException {

		Algebra a = new Algebra("1.0/2.0","2.0/5.0");
		
		Solver manage = new Solver(a);
		
		System.out.println("Your answer equals: " + manage.calculate(a.getEquation()));
		System.out.println("The real answer equals: " + manage.calculate(a.getSolution()));	
		System.out.println("Your answer is: " + manage.checkAnswer(a.getEquation()));
		
		System.out.println("This equation is valid: " + manage.validEquation(a.getEquation()));
		
		System.out.println(manage.checkSolution());
		
	}

}
