package test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Solver.Algebra;
import dice.DiceFace;
import localhostgame.DieIcon;
import localhostgame.EquationsController;
import localhostgame.EquationsController;

public class EquationsControllerTests {
	
	/**
	 * Tests if any die can be moved, regardless of mat/index.
	 */
	@Test
	public void testMoveDie() {
		EquationsController e = new EquationsController();
		e.moveDie(1, 1);
		assertTrue(e.getManager().getMyRequired().checkEmpty()==false);
	}
	
	/**
	 * Tests if any die can be moved to forbidden.
	 */
	@Test
	public void testMoveForbidden() {
		EquationsController e = new EquationsController();
		// Get the die face of the moving die
		DiceFace snap = e.getManager().getMyResources().getMyMat().elementAt(0).getMyUpSide();
		e.moveDie(0, 0);
		// Ensure it gets there
		assertTrue(e.getManager().getMyForbidden().checkEmpty()==false);
		assertEquals(snap, e.getManager().getMyForbidden().getMyMat().elementAt(0).getMyUpSide());
	}
	
	/**
	 * Tests if any die can be moved to required.
	 */
	@Test
	public void testMoveRequired() {
		EquationsController e = new EquationsController();
		// Get the die face of the moving die
		DiceFace snap = e.getManager().getMyResources().getMyMat().elementAt(0).getMyUpSide();
		e.moveDie(1, 0);
		// Ensure it gets there
		assertTrue(e.getManager().getMyRequired().checkEmpty()==false);
		assertEquals(snap, e.getManager().getMyRequired().getMyMat().elementAt(0).getMyUpSide());
	}
	
	/**
	 * Tests if any die can be moved to forbidden.
	 */
	@Test
	public void testMovePermitted() {
		EquationsController e = new EquationsController();
		// Get the die face of the moving die
		DiceFace snap = e.getManager().getMyResources().getMyMat().elementAt(0).getMyUpSide();
		e.moveDie(2, 0);
		// Ensure it gets there
		assertTrue(e.getManager().getMyPermitted().checkEmpty()==false);
		assertEquals(snap, e.getManager().getMyPermitted().getMyMat().elementAt(0).getMyUpSide());
	}
	
	
	/**
	 * Load die images
	 */
	@Test
	public void testLoadDieImage() {
		EquationsController e = new EquationsController();
		assertTrue(e.getDieImages()[0]!=null);
		assertTrue(e.getDieImages()[10]!=null);
		assertTrue(e.getDieImages()[20]!=null);

	}
}

