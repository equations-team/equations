package solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SolverTests {

	@Test
	void fractionTest() {
		
		Algebra a = new Algebra("1.0/2.0","2.0/4.0");
		
		Solver manage = new Solver(a);
		
		assertEquals(0.5, manage.calculate(a.getEquation()), 0.0);
		
	}
	
	@Test
	void multiplyTest() {
		
		Algebra a = new Algebra("1.0*2.0","2.0");
		
		Solver manage = new Solver(a);
		
		assertEquals(2.0, manage.calculate(a.getEquation()), 0.0);
		
	}
	
	@Test
	void additionTest() {
		
		Algebra a = new Algebra("1.0+2.0","3.0");
		
		Solver manage = new Solver(a);
		
		assertEquals(3.0, manage.calculate(a.getEquation()), 0.0);
		
	}
	
	@Test
	void subtractionTest() {
		
		Algebra a = new Algebra("3.0-2.0","1.0");
		
		Solver manage = new Solver(a);
		
		assertEquals(1.0, manage.calculate(a.getEquation()), 0.0);
		
	}
	
	@Test
	void invalidDoubleDigitTest() {
		
		Algebra a = new Algebra("15.0+2.0","17.0");
		
		Solver manage = new Solver(a);
		
		assertFalse(manage.validEquation(a.getEquation()));
		
	}
	
	@Test
	void invalidOperandTest() {
		
		Algebra a = new Algebra("5.0++2.0","17.0");
		
		Solver manage = new Solver(a);
		
		assertFalse(manage.validEquation(a.getEquation()));
		
	}
	
	@Test
	void wrongAnswerTest() {
		
		Algebra a = new Algebra("1.0+2.0","5.0");
		
		Solver manage = new Solver(a);
		
		assertFalse(manage.checkAnswer(a.getEquation()));
		
	}
	
	@Test
	void orderOfOpsTest() {
		
		Algebra a = new Algebra("(3.0*2.0)+1.0","7.0");
		
		Solver manage = new Solver(a);
		
		assertEquals(7.0, manage.calculate(a.getEquation()), 0.0);
		
	}


}
