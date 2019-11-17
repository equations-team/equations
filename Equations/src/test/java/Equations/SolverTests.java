package Equations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Solver.*;

public class SolverTests {
	
	public SolverTests() {
		
	}
	
	//√
	
	@Test
	public void operationsCheck() {
		
		Algebra a = new Algebra("5.0*2.0+1.0", "5.0+6.0");
		
		Solver s = new Solver(a);
		
		assertEquals("Does not equal 11.", 11.0, s.calculate(a.getSolution()), 0.0);
		
	}
	
	@Test
	public void operationsWithSpaceCheck() {
		
		Algebra a = new Algebra("5.0* 2.0+1.0", "7.0+8.0");
		
		Solver s = new Solver(a);
		
		assertEquals("Does not equal 15.", 15.0, s.calculate(a.getSolution()), 0.0);
		
	}
	
	@Test
	public void operationsWithSecondSpaceCheck() {
		
		Algebra a = new Algebra("5.0*2.0+ 1.0", "9.0+2.0");
		
		Solver s = new Solver(a);
		
		assertEquals("Does not equal 11.", 11.0, s.calculate(a.getSolution()), 0.0);
		
	}
	
	@Test
	public void divisonOperationsCheck() {
		
		Algebra a = new Algebra("4.0/3.0", "1.0+2.0/6.0");
		
		Solver s = new Solver(a);
		
		assertEquals("Does not equal 4/3.", 4.0/3.0, s.calculate(a.getSolution()), 0.0);
		
	}
	
	@Test
	public void rootCheck() {
		
		Algebra a = new Algebra("?4.0", "2.0");
		
		Solver s = new Solver(a);
		
		double answer = s.calculate(a.getSolution());
		
		assertTrue(answer == 2.0);
		
	}
	
	@Test
	public void cubeRootCheck() {
		
		Algebra a = new Algebra("3?8.0", "2.0");
		
		Solver s = new Solver(a);
		
		double answer = s.calculate(a.getSolution());
		
		assertTrue(answer == 2.0);
		
	}
	
	@Test
	public void rootMultCheck() {
		
		Algebra a = new Algebra("?2.0", "?6.0*3.0/3.0");
		
		Solver s = new Solver(a);
		
		double answer = s.calculate(a.getSolution());
		
		assertTrue(answer == Math.pow(2.0, 1.0/2.0));
		
	}
	
	@Test
	public void rootMultSpaceCheck() {
		
		Algebra a = new Algebra("?2.0", "?6.0*3.0/ 3.0");
		
		Solver s = new Solver(a);
		
		double answer = s.calculate(a.getSolution());
		
		assertTrue(answer == Math.pow(2.0, 1.0/2.0));
		
	}
	
	@Test
	public void rootSixSpaceCheck() {
		
		Algebra a = new Algebra("?6.0", "?6.0* 3.0/3.0 ");
		
		Solver s = new Solver(a);
		
		double answer = s.calculate(a.getSolution());
		
		assertTrue(answer == Math.pow(6.0, 1.0/2.0));
		
	}
	
	@Test
	public void doubleOps() {
		
		Algebra a = new Algebra("6.0++6.0", "12.0");
		
		Solver s = new Solver(a);
		
		assertFalse(s.validEquation(a.getEquation()));
		
	}
	

}

