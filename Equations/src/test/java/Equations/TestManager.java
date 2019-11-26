package Equations;

import state_manager.*;

import org.junit.Before;
import org.junit.Test;

import fundementalgamemechanics.*;

import static org.junit.Assert.*;

public class TestManager {
	private Player[] players;
	private Manager manager;

	@Before
	public void SetUp() throws Exception {
		players = new Player[3];
		players[0] = new Player("Ruby");
		players[1] = new Player("James");
		players[2] = new Player("Nicola");

		CommonDice[] testDie = new CommonDice[24];
		for (int i = 0; i < testDie.length; i++) {
			testDie[i] = new CommonDice();
		}

		manager = new Manager(players, testDie);

		manager.getMyResources().getMyMat().elementAt(0).setMyUpSide(DiceFace.ONE);
		manager.getMyResources().getMyMat().elementAt(1).setMyUpSide(DiceFace.ADDITION);
		manager.getMyResources().getMyMat().elementAt(2).setMyUpSide(DiceFace.TWO);
		manager.getMyResources().getMyMat().elementAt(3).setMyUpSide(DiceFace.MULTIPLICATION);
		manager.getMyResources().getMyMat().elementAt(4).setMyUpSide(DiceFace.FOUR);

		int[] goals = { 0, 1, 2, 3, 4 };
		manager.setFirstPlayer();
		manager.setGoal(goals);

		/*
		 * for(int i = 0; i < players.length; i ++) {
		 * 
		 * manager.setGoalSetter(players[i]); for(int j = 0; j < i;j++) {
		 * while(players[i].getRedDice() == players[j].getRedDice()) {
		 * manager.setGoalSetter(players[i]); }; }
		 * 
		 * }
		 */

		manager.moveDie(0, GameMove.ADDFORBIDDEN);
		manager.moveDie(1, GameMove.ADDPERMITTED);
		manager.moveDie(2, GameMove.ADDREQUIRED);
		manager.moveDie(3, GameMove.ADDREQUIRED);
		manager.moveDie(4, GameMove.ADDPERMITTED);
		manager.moveDie(5, GameMove.ADDFORBIDDEN);
		manager.moveDie(6, GameMove.ADDPERMITTED);
		manager.moveDie(7, GameMove.ADDREQUIRED);
		manager.moveDie(8, GameMove.ADDREQUIRED);
		manager.moveDie(9, GameMove.ADDPERMITTED);

		manager.getMyForbidden().getMyMat().elementAt(0).setMyUpSide(DiceFace.EIGHT);
		manager.getMyPermitted().getMyMat().elementAt(0).setMyUpSide(DiceFace.ADDITION);
		manager.getMyRequired().getMyMat().elementAt(0).setMyUpSide(DiceFace.DIVISION);
		manager.getMyRequired().getMyMat().elementAt(1).setMyUpSide(DiceFace.FIVE);
		manager.getMyPermitted().getMyMat().elementAt(1).setMyUpSide(DiceFace.FIVE);
		manager.getMyForbidden().getMyMat().elementAt(1).setMyUpSide(DiceFace.MULTIPLICATION);
		manager.getMyPermitted().getMyMat().elementAt(2).setMyUpSide(DiceFace.ONE);
		manager.getMyRequired().getMyMat().elementAt(2).setMyUpSide(DiceFace.ONE);
		manager.getMyRequired().getMyMat().elementAt(3).setMyUpSide(DiceFace.NINE);
		manager.getMyPermitted().getMyMat().elementAt(3).setMyUpSide(DiceFace.SIX);

		manager.getMyResources().getMyMat().elementAt(0).setMyUpSide(DiceFace.FIVE);
		manager.getMyResources().getMyMat().elementAt(1).setMyUpSide(DiceFace.SIX);
		manager.getMyResources().getMyMat().elementAt(2).setMyUpSide(DiceFace.SUBTRACTION);

	}

	@Test
	public void testSetFirstPlayer() {
		assertFalse(manager.getGoalSetter() == null);
	}

	@Test
	public void testGameSetup() {

		assertFalse(manager.getCurrentPlayer() == null);
		assertTrue(manager.getGoalEquation().compareTo("1.0+2.0*4.0") == 0);
	}

	@Test
	public void testMoveDie() {
		manager.getMyResources().getMyMat().elementAt(0).setMyUpSide(DiceFace.FOUR);
		manager.moveDie(0, GameMove.ADDFORBIDDEN);
		assertTrue(manager.getMyForbidden().getMyMat().lastElement().getMyUpSide() == DiceFace.FOUR);
	}

	@Test
	public void testChallengeWithNullEq() {
		assertFalse(manager.challenge(1, null, null));
	}

	@Test
	public void testChallengeWithInvalidFlag() {
		assertFalse(manager.challenge(-1, "1+1", null));

	}

	@Test
	public void testCheckInputForbidden() {
		assertFalse(manager.checkInput(1, "8"));
		assertFalse(manager.checkInput(1, "*"));

	}

	@Test
	public void testCheckInputRequired() {
		assertFalse(manager.checkInput(1, "43"));
		assertFalse(manager.checkInput(1, "9"));
		assertFalse(manager.checkInput(1, "1"));
		assertFalse(manager.checkInput(1, "+"));

		assertTrue(manager.checkInput(1, "/519"));
		assertFalse(manager.checkInput(1, "/51"));
		assertFalse(manager.checkInput(1, "/5"));
		assertFalse(manager.checkInput(1, "/"));

	}

	@Test
	public void testCheckInputPermitted() {
		assertTrue(manager.checkInput(1, "/519"));
		assertTrue(manager.checkInput(1, "/5195"));
		assertTrue(manager.checkInput(1, "/5191"));
		assertTrue(manager.checkInput(1, "/5196"));
		assertTrue(manager.checkInput(1, "/519+"));
		assertTrue(manager.checkInput(1, "/519+516"));

	}

	@Test
	public void testCheckInputChallenge() {
		assertTrue(manager.checkInput(1, "/519+51656"));
		assertTrue(manager.checkInput(1, "/519+5165"));
		assertTrue(manager.checkInput(1, "/519+5166"));
		assertFalse(manager.checkInput(2, "/519+51656"));
	}

	@Test
	public void testChallengeNow1() {
		assertTrue(manager.checkInput(2, "9/1+5-5"));
		assertTrue(manager.challenge(2, "9.0/1.0+5.0-5.0", null));
		assertTrue(manager.getCurrentPlayer().getScore() == 6);
		assertTrue(manager.getLastPlayer().getScore() == 2);
		assertTrue(manager.getThirdPlayer().getScore() == 4);
		assertTrue(manager.isGameEnd());
	}

	@Test
	public void testChallengeNow2() {
		assertTrue(manager.challenge(2, "9.0/1.0+5.0-5.0", "1.0+1.0"));
		assertTrue(manager.getCurrentPlayer().getScore() == 6);
		assertTrue(manager.getLastPlayer().getScore() == 2);
		assertTrue(manager.getThirdPlayer().getScore() == 4);
		assertTrue(manager.isGameEnd());
	}

	@Test
	public void testChallengeNow3() {
		assertTrue(manager.challenge(2, "1.0+1.0", "9.0/1.0+5.0-5.0"));
		assertTrue(manager.getCurrentPlayer().getScore() == 2);
		assertTrue(manager.getLastPlayer().getScore() == 2);
		assertTrue(manager.getThirdPlayer().getScore() == 6);
		assertTrue(manager.isGameEnd());
	}
	
	public void testChallengeNow4() {
		assertTrue(manager.challenge(2, "1.0+1.0", "1.0+1.0"));
		assertTrue(manager.getCurrentPlayer().getScore() == 2);
		assertTrue(manager.getLastPlayer().getScore() == 6);
		assertTrue(manager.getThirdPlayer().getScore() == 2);
		assertTrue(manager.isGameEnd());
	}

	@Test
	public void testParenthesis() {
		assertTrue(manager.checkInput(2, "(((9/1)+5)-5)"));
		assertTrue(manager.challenge(2, "(((9.0/1.0)+5.0)-5.0)", null));
		assertTrue(manager.getCurrentPlayer().getScore() == 6);
		assertTrue(manager.getLastPlayer().getScore() == 2);
		assertTrue(manager.getThirdPlayer().getScore() == 4);
		assertTrue(manager.isGameEnd());
	}

	@Test
	public void testChallengeImpossible() {
		assertTrue(manager.checkInput(1, "9/1+5-5"));
		assertTrue(manager.challenge(1, "9.0/1.0+5.0-5.0", null));
		assertTrue(manager.getCurrentPlayer().getScore() == 2);
		assertTrue(manager.getLastPlayer().getScore() == 6);
		assertTrue(manager.getThirdPlayer().getScore() == 4);
		assertTrue(manager.isGameEnd());
	}

	@Test
	public void testStartTurn() {
		Player p = manager.startTurn();
		manager.moveDie(0, GameMove.ADDFORBIDDEN);
		assertFalse(p.getName().compareTo(manager.startTurn().getName()) == 0);

	}

	@Test
	public void testGetThirdPlayer() {
		manager.startTurn();
		manager.moveDie(0, GameMove.ADDFORBIDDEN);
		manager.startTurn();
		Player testThird = manager.getThirdPlayer();
		manager.moveDie(0, GameMove.ADDFORBIDDEN);
		Player third = manager.startTurn();

		assertTrue(testThird.getName().compareTo(third.getName()) == 0);

	}

}