package state_manager;

public interface Manger_Reader
{
    void setGoalSetter(Player p);
    void setFirstPlayer();
    boolean setGoal(int[] goal_indexes);
    void setSolver(String str);
    boolean moveDie(Player p, int i, GameMove d);

}
