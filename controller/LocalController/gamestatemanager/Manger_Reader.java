package gamestatemanager;

import java.util.Vector;

public interface Manger_Reader
{
	Player startTurn();
	boolean checkInput(int flag, String str);
	Vector<Player> getWinner();
    void setFirstPlayer();
    boolean setGoal(int[] goal_indexes);
    void setSolver(String str);
    boolean moveDie(int i, GameMove d);
    boolean challenge(int flag, String eq1, String eq2);

}
