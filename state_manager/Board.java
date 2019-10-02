package test;

public class Board
{
    private Dice[] sources;
    private Dice[] forbidden;
    private Dice[] required;
    private Dice[] permitted;
    private Dice[] goal;
    private static final int DICES = 24;
    private static final int AREASIZE = 8; 
    
    public Board(Dice[] d){
        sources = d;
        forbidden = new Dice[AREASIZE];
        required = new Dice[AREASIZE];
        permitted = new Dice[AREASIZE];
        goal = new Dice[5];

    }

    public Dice[] getForbidden()
    {
        return forbidden;
    }

    public void addForbidden(Dice d)
    {
        for(int i = 0; i < forbidden.length; i++) {
            if (forbidden[i] == null) {
                forbidden[i] = d;
                i = forbidden.length;
            }
        };
    }

    public Dice[] getRequired()
    {
        return required;
    }

    public void addRequired(Dice d)
    {
        for(int i = 0; i < required.length; i++) {
            if (required[i] == null) {
                required[i] = d;
                i = required.length;
            }
        };  
     }

    public Dice[] getPermitted()
    {
        return permitted;
    }

    public void addPermitted(Dice d)
    {
        for(int i = 0; i < permitted.length; i++) {
            if (permitted[i] == null) {
                permitted[i] = d;
                i = permitted.length;
            }
        };  
    }

    public Dice[] getGoal()
    {
        return goal;
    }

    public void setGoal(Dice[] goal)
    {
        this.goal = goal;
    }
    
    
    
}
