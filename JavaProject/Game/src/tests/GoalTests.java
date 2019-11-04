package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;

import fundementalgamemechanics.DiceFace;
import fundementalgamemechanics.Die;
import fundementalgamemechanics.Goal;
import fundementalgamemechanics.Mat;
import fundementalgamemechanics.RedDie;
import fundementalgamemechanics.SpecialDie;

class GoalTests {
	
	@Test
	public void removeDieTest() {
		// Arrange
	    final Goal testGoal = new Goal();

	    // Act
	    testGoal.addToMyMat(new RedDie());
	    boolean testFalse = testGoal.removeDieByIndex(0);

	    // Assert
		assertEquals(false,testFalse);
	}

	@Test
	public void addPerenthisesTest() {
		// Arrange
	    final Goal testGoal = new Goal();

	    // Act
	    testGoal.addPerenthises();

	    // Assert
		assertEquals(2,testGoal.getMyMat().size());
	}

}
