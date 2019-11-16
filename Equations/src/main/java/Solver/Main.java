package Solver;
import javax.script.ScriptException;

import fundementalgamemechanics.Goal;
import gamestatemanager.*;

public class Main {

	public static void main(String[] args) throws ScriptException {

		
		Algebra a = new Algebra("5.0*2.0+1.0", "7.0+8.0");
	 
		Solver manage = new Solver(a);
		
		System.out.println(a.getEquation());
		
		System.out.println(manage.calculate(a.getEquation()));
		
	}

}
