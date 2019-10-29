package Equations;

import fundementalgamemechanics.Mat;
import fundementalgamemechanics.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MatTests {
	
	@Test
	public void addDieTest() {
		// Arrange
	    final Mat testMat = new Mat();

	    // Act
	    testMat.addToMyMat(new RedDie());

	    // Assert
	    assertEquals(1, testMat.getMyMat().size());
	}

	@Test
	public void removeDieTest() {
		// Arrange
	    final Mat testMat = new Mat();

	    // Act
	    testMat.addToMyMat(new RedDie());
	    testMat.addToMyMat(new RedDie());
	    testMat.removeDieByIndex(1);

	    // Assert
	    assertEquals(1, testMat.getMyMat().size());
	}
	
	@Test
	public void removeMissingDieTest() {
		// Arrange
	    final Mat testMat = new Mat();

	    // Act
	    final boolean falureTest = testMat.removeDieByIndex(1);

	    // Assert
	    assertEquals(false, falureTest);
		
	}
	
	@Test
	public void removeDieByDie() {
		//Arrange
		final Die testDie = new RedDie();
		final Mat testMat = new Mat();
		
		//Act
		testMat.addToMyMat(testDie);
		final boolean successTest = testMat.removeDie(testDie);
		
		//Assert
		assertEquals(0, testMat.getMyMat().size());
		assertEquals(true, successTest);
	}
	
	@Test
	public void removeMissingDieByDie() {
		//Arrange
		final Die testDie = new RedDie();
		final Mat testMat = new Mat();
		
		//Act
		final boolean successTest = testMat.removeDie(testDie);
		
		//Assert
		assertEquals(false, successTest);
	}
	
	@Test
	public void reorderMissingDice() {
		//Arrange
		final Die testDie = new RedDie();
		final Mat testMat = new Mat();
		
		//Act
		testMat.addToMyMat(testDie);
		final boolean falureTest = testMat.reorderDice(0, 1);
		
		//Assert
		assertEquals(false, falureTest);		
	}
	
}
