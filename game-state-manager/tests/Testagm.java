package tests;

import active_manager.*;
import fundementalgamemechanics.CommonDice;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Testagm {
	private GameDBImpl DB;
	private ActiveManager AGM;
	private int[] players = {1,2,3};
	CommonDice[] testDie;
	
	@Before
	public void SetUp() throws Exception{
		DB = new GameDBImpl();
		AGM = new ActiveManager(DB);
	
		testDie = new CommonDice[24];
		for (int i = 0; i < testDie.length; i++) {
			testDie[i] = new CommonDice();
		}
		
	}
	
	
	@Test
	public void testAddNewGame() {
		assertTrue(AGM.checkActiveGameNum() == 0);
		AGM.addNewGame(players, testDie);
		assertTrue(AGM.checkActiveGameNum() == 1);
	}
	
	@Test
	public void testGetGame() {
		AGM.addNewGame(players, testDie);
		int tempID = AGM.getMyGameIDs().firstElement();
		assertTrue(AGM.getGame(tempID).getPlayers()[0].getName().compareTo(DB.getName(1)) == 0);
	}
	
	
}
