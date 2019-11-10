import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.awt.List;
import java.awt.Point;
import java.util.Map;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.junit.jupiter.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fundementalgamemechanics.Game;

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
		Model mockModel = mock(Model.class);
		Equations_Controller e = new Equations_Controller();
		assertTrue(e.moveDie(0,1, mockModel));
		assertFalse(e.getGame().getMyForbidden().checkEmpty());
	}
	
	/**
	 * Tests if the controller can move a dice to the forbidden mat.
	 */
	@Test
	public void testMoveToRequired() {

		Model mockModel = mock(Model.class);
		Equations_Controller e = new Equations_Controller();
		assertTrue(e.moveDie(1,2, mockModel));
		assertFalse(e.getGame().getMyRequired().checkEmpty());

	}
	
	/**
	 * Tests if the controller can move a dice to the forbidden mat.
	 */
	@Test
	public void testMoveToAllowed() {
		Model mockModel = mock(Model.class);
		Equations_Controller e = new Equations_Controller();
		assertTrue(e.moveDie(2,3, mockModel));
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
	
	/**
	 * Tests if someone can challenge now.
	 */
	@Test
	public void testChallengeNow() {
		Equations_Controller e = new Equations_Controller();
		e.setGoal("4");
		e.challengeNow("3+1");
		assertTrue(e.challengeNow("4"));
		
	}
	
	/**
	 * Tests if someone can challenge impossible.
	 */
	@Test
	public void testChallengeImpossible() {
		Equations_Controller e = new Equations_Controller();
		e.setGoal("4");
		e.challengeNow("3+1");
		assertTrue(e.challengeImpossible("4"));
		
	}
	
	/**
	 * Tests if view can load dice correctly.
	 */
	@Test
	public void testLoadDice() {
		java.util.Map<String, Object> mockMap = (java.util.Map<String, Object>) mock(Map.class);
		Equations_Controller e = new Equations_Controller();
		e.loadDice(mockMap);
		
	}
	
}