package test;

public class Board
{
    private String[] sources;
    private String[] forbidden;
    private String[] required;
    private String[] permitted;
    private String[] goal;
    private static final int DICES = 24;
    private static final int AREASIZE = 8; 
    
    public Board(){
        sources = new String[DICES];
        forbidden = new String[AREASIZE];
        required = new String[AREASIZE];
        permitted = new String[AREASIZE];
        goal = new String[5];

    }

    public String[] getForbidden()
    {
        return forbidden;
    }

    public void addForbidden(String d)
    {
        for(int i = 0; i < forbidden.length; i++) {
            if (forbidden[i] == null) {
                forbidden[i] = d;
                i = forbidden.length;
            }
        };
    }

    public String[] getRequired()
    {
        return required;
    }

    public void addRequired(String d)
    {
        for(int i = 0; i < required.length; i++) {
            if (required[i] == null) {
                required[i] = d;
                i = required.length;
            }
        };  
     }

    public String[] getPermitted()
    {
        return permitted;
    }

    public void addPermitted(String d)
    {
        for(int i = 0; i < permitted.length; i++) {
            if (permitted[i] == null) {
                permitted[i] = d;
                i = permitted.length;
            }
        };  
    }

    public String[] getGoal()
    {
        return goal;
    }

    public void setGoal(String[] goal)
    {
        this.goal = goal;
    }
    
    
    
}
