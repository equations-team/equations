package tests;

import junit.framework.TestCase;
import fundementalgamemechanics.Mat;

import org.junit.jupiter.api.Test;

import fundementalgamemechanics.*;

public class MatTests extends TestCase {
	
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
	public void reorderDice() {
		//Arrange
		final Die testDie = new RedDie();
		final Die testBlackDie = new BlackDie();
		final Mat testMat = new Mat();
		
		//Act
		final String firstOrder = testDie.getMyUpSide() + testBlackDie.getMyUpSide();
		final String secondOrder = testBlackDie.getMyUpSide() + testDie.getMyUpSide();
		testMat.addToMyMat(testDie);
		testMat.addToMyMat(testBlackDie);
		final String firstRealOrder = testMat.getMyMat().get(0).getMyUpSide()+testMat.getMyMat().get(1).getMyUpSide();
		testMat.reorderDice(0, 1);
		final String secondRealOrder = testMat.getMyMat().get(0).getMyUpSide()+testMat.getMyMat().get(1).getMyUpSide();
		
		//Assert
		assertEquals(firstOrder, firstRealOrder);		
		assertEquals(secondOrder, secondRealOrder);		
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
