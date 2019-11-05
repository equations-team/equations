package gamestatemanager;

public interface ManagerReader
{
    void setGoalSetter(Player p);
    void setFirstPlayer();
    boolean setGoal(int[] goal_indexes);
    void setSolver(String str);
    boolean moveDie(Player p, int i, GameMove d);

}
