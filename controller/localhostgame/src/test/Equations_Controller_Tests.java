package test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Solver.Algebra;
import dice.DiceFace;
import localhostgame.DieIcon;
import localhostgame.Equations_Controller;

public class Equations_Controller_Tests {
	
	/**
	 * Tests if any die can be moved, regardless of mat/index.
	 */
	@Test
	public void testMoveDie() {
		Equations_Controller e = new Equations_Controller();
		e.moveDie(1, 1);
		assertTrue(e.getManager().getMyRequired().checkEmpty()==false);
	}
	
	/**
	 * Tests if any die can be moved to forbidden.
	 */
	@Test
	public void testMoveForbidden() {
		Equations_Controller e = new Equations_Controller();
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
		Equations_Controller e = new Equations_Controller();
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
		Equations_Controller e = new Equations_Controller();
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
		Equations_Controller e = new Equations_Controller();
		assertTrue(e.getDieImages()[0]!=null);
		assertTrue(e.getDieImages()[10]!=null);
		assertTrue(e.getDieImages()[20]!=null);

	}
}

