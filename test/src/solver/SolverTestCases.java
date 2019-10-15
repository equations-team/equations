package solver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolverTestCases {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void fractionTest() {
		
		Algebra a = new Algebra("1+1","1/2");
		Solver s = new Solver(a);
		
		assertEquals(0.5,s.calculate(a.getSolution()), "Should print fraction.");
		
	}
	
	@Test
	void equalsTest() {
		
		Algebra a = new Algebra("1+1","3-1");
		Solver s = new Solver(a);
		
		assertEquals(2,s.calculate(a.getSolution()), "Should equal 2.");
		
	}

}
