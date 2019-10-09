package test;

public class Player
{
    private String name;
    private boolean checkTurn;
    private boolean checkwin;
    private boolean lastMove;
    private int score;
    private String equation;
    

    public Player(String n) {
        this.name = n;
        checkTurn = false;
        checkwin = false;
        lastMove = false;
        
    }

    public String getName()
    {
        return name;
    }

    public boolean isLastMove()
    {
        return lastMove;
    }

    public void setLastMove(boolean lastMove)
    {
        this.lastMove = lastMove;
    }

    public boolean getCheckTurn()
    {
        return checkTurn;
    }

    public void setCheckTurn(boolean checkTurn)
    {
        this.checkTurn = checkTurn;
    }

    public boolean getCheckwin()
    {
        return checkwin;
    }

    public void setCheckwin(boolean checkwin)
    {
        this.checkwin = checkwin;
    }
    public String getEquation()
    {
        return equation;
    }

    public void setEquation(String equation)
    {
        this.equation = equation;
    }

   
    
    

}
