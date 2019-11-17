package Solver;
import javax.script.ScriptException;

import fundementalgamemechanics.Goal;
import gamestatemanager.*;

public class Main {

	public static void main(String[] args) throws ScriptException {

		
		Algebra a = new Algebra(" ?2.0", "?6.0*3.0/ 3.0 ");
	 
		Solver manage = new Solver(a);
		
		System.out.println(a.getEquation());
		
		System.out.println(manage.calculate(a.getSolution()));

	}

}
