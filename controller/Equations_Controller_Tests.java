import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Equations_Controller_Tests {
	
	/**
	 * Tests if the controller accurately defines who goes first.
	 */
	@Test
	public void testControllerGoFirst() {
		
		Equations_Controller e = new Equations_Controller();
		int f = e.getTurn();
		assertTrue(f != 0);
	}
	
	/**
	 * Tests if the controller goes from turn 1 turn 2 after next turn.
	 */
	@Test
	public void testTurn2() {

		Equations_Controller e = new Equations_Controller();
		
		e.setTurn(1);
		e.passTurn();
		assertTrue(e.getTurn()==2);
	}
	
	/**
	 * Tests if the controller goes from turn 3 to turn 1 after next turn.
	 */
	@Test
	public void testTurn3() {

		Equations_Controller e = new Equations_Controller();
		
		e.setTurn(3);
		e.passTurn();
		assertTrue(e.getTurn()==1);
	}
	
	/**
	 * Tests if the controller can move a dice to the forbidden mat.
	 */
	@Test
	public void testMoveToForbidden() {

		Equations_Controller e = new Equations_Controller();
		assertTrue(e.moveDie(0,1));
		assertFalse(e.getGame().getMyForbidden().checkEmpty());
	}
	
	/**
	 * Tests if the controller can move a dice to the forbidden mat.
	 */
	@Test
	public void testMoveToRequired() {

		Equations_Controller e = new Equations_Controller();
		assertTrue(e.moveDie(1,2));
		assertFalse(e.getGame().getMyRequired().checkEmpty());

	}
	
	/**
	 * Tests if the controller can move a dice to the forbidden mat.
	 */
	@Test
	public void testMoveToAllowed() {

		Equations_Controller e = new Equations_Controller();
		assertTrue(e.moveDie(2,3));
		assertFalse(e.getGame().getMyAllowed().checkEmpty());

	}
	
	/**
	 * Tests if the view receives who is the goal setter.
	 */
	@Test
	public void testViewGoalSetter() {
		Equations_Controller e = new Equations_Controller();
		MockView v = new MockView(e);
		int first = e.getTurn();
		assertEquals(first, v.getGoalSet());
		
	}
}