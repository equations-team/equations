package Solver;
import javax.script.ScriptException;

import fundementalgamemechanics.Goal;
import gamestatemanager.*;

public class Main {

	public static void main(String[] args) throws ScriptException {

		
		Algebra a = new Algebra("?6.0", "?6.0* 3.0/3.0 ");
		
		Solver manage = new Solver(a);
		
		System.out.println(a.getEquation());
		
		System.out.println(a.getSolution());
		
		System.out.println(manage.calculate(a.getSolution()));
		
		System.out.println(manage.validEquation(a.getEquation()));

	}

}
