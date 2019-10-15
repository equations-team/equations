package test;

import state_manager.*;

import org.junit.Before;
import org.junit.Test;

import fundementalgamemechanics.DiceFace;

import static org.junit.Assert.*;

public class TestManager
{
    private Player[] players;
    private Manager manager;
    
    @Before
    public void SetUp() throws Exception{
        players = new Player[3];
        players[0] = new Player("Ruby");
        players[1] = new Player("James");
        players[2] = new Player("Nicola");
        
        int[] goals = new int[5];
        goals[0] = 20;
        goals[1] = 12;
        goals[2] = 9;
        goals[3] = 2;
        goals[4] = 23;
        
        manager =  new Manager(players);
        
        
        for(int i = 0; i < players.length; i ++) {  
           
                manager.setGoalSetter(players[i]);
                for(int j = 0; j < i;j++) {
                    while(players[i].getRedDice() == players[j].getRedDice()) {
                        manager.setGoalSetter(players[i]);
                    };
                }
            
        }
        
        
        
        manager.setFirstPlayer();
        manager.setGoal(goals);
        
        
    }
    
    
    @Test
    public void testRollRedDice() {
        assertTrue(players[0].getRedDice() != players[1].getRedDice());
        assertTrue(players[1].getRedDice() != players[2].getRedDice());
        assertTrue(players[0].getRedDice() != players[2].getRedDice());

        
    }
    
    @Test
    public void testSetFirstPlayer() {
        assertFalse(manager.getGoalSetter() == null);
    }
    
    @Test
    public void testGameSetup() {
        assertFalse(manager.getCurrentPlayer() == null);
       // assertTrue(manager.getGoalEquation().compareTo("7*3-7") == 0);
    }
    
    @Test
    public void testMoveDie() {
        DiceFace d = manager.getMyResources().getMyMat().elementAt(0).getMyUpSide();
        manager.moveDie(manager.getCurrentPlayer(), 0, GameMove.ADDFORBIDDEN);
        assertTrue(manager.getMyForbidden().getMyMat().lastElement().getMyUpSide() == d);
        assertTrue(manager.getMyForbidden().getMyMat().size() == 1);
        assertTrue(manager.getMyPermitted().getMyMat().size() == 0);
        assertTrue(manager.getMyPermitted().getMyMat().size() == 0);
        assertTrue(manager.getMyResources().getMyMat().size() ==23);

    }
    
    @Test
    public void testChallengeAtBeginning() {
        assertFalse(manager.moveDie(manager.getCurrentPlayer(), -1, GameMove.CHALLENGENOW));
    }
    
    @Test
    public void testChallengeWithInvalidIndex() {
        assertFalse(manager.moveDie(manager.getCurrentPlayer(), 1, GameMove.CHALLENGEIMPOSSIBLE));
    }

    @Test
    public void testMoveDieWithInvalidIndex() {
        assertFalse(manager.moveDie(manager.getCurrentPlayer(), -1, GameMove.ADDPERMITTED));
    }
    
    @Test
    public void testSetSolver() {
        manager.setSolver("1+1");
        assertFalse(manager.getSolver().checkAnswer(manager.getSolver().calculate("1+1")));

    }
}
