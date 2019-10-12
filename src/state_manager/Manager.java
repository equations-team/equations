package state_manager;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import fundementalgamemechanics.*;
import solver.*;

/**
 * GSM of equations game
 * @author Ruby
 *
 */
public class Manager
{
    //Constants
    public static final int DICENUMBER  = 24;
    public static final int REDNUMBER   = 6;
    public static final int GREENNUMBER = 6;
    public static final int BLACKUMBER  = 6;
    public static final int BLUENUMBER  = 6;

    //Data members
    private Solver    solver;
    private GameTimer timer;
    private Player[]  players;
    private ScriptEngine engine;

    private Mat  myResources;
    private Goal myGoal;
    private Mat  myForbidden;
    private Mat  myPermitted;
    private Mat  myRequired;

    private String goalEquation;
    private Player currentPlayer;
    private Player nextPlayer;
    private Player lastPlayer;
    private Player goalSetter;
    private int    count;
    private int    numPlayers;

    /**
     * Constructor of GSM
     */
    public Manager(Player[] p)
    {
        myResources = new Mat();
        myGoal = new Goal();
        myForbidden = new Mat();
        myPermitted = new Mat();
        myRequired = new Mat();
        timer = new GameTimer(1);
        engine = new ScriptEngineManager().getEngineByName("js");

        players = p;
        numPlayers = players.length;

        for (int i = 0; i < DICENUMBER; i++)
        {
            if (i < REDNUMBER)
            {
                myResources.addToMyMat(new RedDie());
            } else if (i < REDNUMBER + BLUENUMBER)
            {
                myResources.addToMyMat(new BlueDie());
            } else if (i < REDNUMBER + BLUENUMBER + GREENNUMBER)
            {
                myResources.addToMyMat(new GreenDie());
            } else
            {
                myResources.addToMyMat(new BlackDie());
            }
        }

    }

    /**
     * Let a player to roll a red dice before the game started.
     */
    public void rollRedDice(Player p)
    {
        int redDice;
        RedDie die = new RedDie();
        die.roll();
        try
        {
            redDice = Integer
                    .parseInt(this.dieFaceTranslator(die.getMyUpSide()));
            p.setRedDice(redDice);;
        } catch (Exception e)
        {
            System.out.println("Not a number. Please roll again!");
        }
    }

    /**
     * After the players rolled their red dices. Compare their results to decide
     * a player to set the goal.
     */
    public void setFirstPlayer()
    {
        int max = -1;
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].getRedDice() > max)
            {
                max = players[i].getRedDice();
                goalSetter = players[i];
                currentPlayer = goalSetter;
                count = i;
            }
        }

    }
    
    /**
     * Using for the goal setter to set the goal mat, and roll the dices
     */
    public void gameSetup(int[] goals)
    {
        try {
            StringBuilder str = new StringBuilder();
            
            for (int i = 0; i < goals.length; i++)
            {
                myGoal.addToMyMat(myResources.getMyMat().get(goals[i]));;
            }
            
            myGoal.Read();
            
            for (int i = 0; i < myGoal.getMyGoal().size(); i++)
            {
                str.append(this.dieFaceTranslator(myGoal.getMyGoal().elementAt(i)));
            }
            goalEquation = str.toString();
            int answer = (int) engine.eval(goalEquation);
            currentPlayer = this.nextPlayer();

            for (int i = 0; i < myResources.getMyMat().size(); i++)
            {
                myResources.getMyMat().elementAt(i).roll();
            }
            
        }catch(ScriptException e) {
            System.out.println("ERROR not a valid equation -- Please set a valid goal");
        }

        
    }

    /**
     * Initialize a solver with player's equation and goal.
     */
    public void setSolver(String equation)
    {
        
        solver = new Solver(new Algebra(equation, goalEquation));
    }

   

    /**
     * Basic move dice operation.
     * @param player
     * @param index of dice in the resources mat
     * @param gamemove decision
     * @return 
     */
    public boolean moveDie(Player p, int index, GameMove decision)
    {

        if (p.getName() != currentPlayer.getName())
            return false;

        Player p1;
        Die moved = myResources.getMyMat().get(index);
        if (moved == null)
            return false;
        myResources.removeDie(moved);
        switch (decision)
        {
            case ADDFORBIDDEN :
                myForbidden.addToMyMat(moved);
                System.out.println(
                        p.getName() + " moves a dice to forbidden mat!");
                break;
            case ADDREQUIRED :
                myRequired.addToMyMat(moved);
                System.out.println(
                        p.getName() + " moves a dice to required mat!");
                break;
            case ADDPERMITTED :
                myPermitted.addToMyMat(moved);
                System.out.println(
                        p.getName() + " moves a dice to permitted mat!");
                break;

            case CHALLENGEIMPOSSIBLE :

                if(myRequired.checkEmpty() && myPermitted.checkEmpty() && myForbidden.checkEmpty())
                    return false;
                p1 = this.lastPlayer();
                // For test purpose
                System.out
                        .println("Enter your equation according to the rule:");
                Scanner sc = new Scanner(System.in);
                String eq = sc.nextLine();

                if (this.checkInput(1, eq))
                {
                    this.enteringEquation(p1, sc.nextLine());
                } else
                {
                    System.out.println(
                            "Please use the dices on the mats properly!");
                } ;

                if (this.doingMath(p1.getEquation()))
                {
                    System.out.println(p1.getName() + "wins!");
                } else
                {
                    System.out.print(p.getName() + "wins!");
                }
                break;

            case CHALLENGENOW :
                if(myRequired.checkEmpty() && myPermitted.checkEmpty() && myForbidden.checkEmpty())
                    return false;
                
                p1 = this.lastPlayer();

                // For test purpose
                System.out
                        .println("Enter your equation according to the rule:");
                Scanner sc1 = new Scanner(System.in);
                String eq1 = sc1.nextLine();

                if (this.checkInput(2, eq1))
                {
                    this.enteringEquation(p1, sc1.nextLine());
                } else
                {
                    System.out.println(
                            "Please use the dices on the mats properly!");
                } ;

                this.enteringEquation(p, eq1);

                if (this.doingMath(p.getEquation()))
                {
                    System.out.println(p.getName() + "wins!");
                } else
                {
                    System.out.print(p1.getName() + "wins!");
                }

                break;

            default :
                throw new AssertionError("Please make a valid move!");
        }

        currentPlayer = this.nextPlayer();
        return true;
    }
    
    
    /**
     * 
     * @return next player
     */
    private Player nextPlayer()
    {
        count++;
        if (count == numPlayers)
        {
            count = 0;
        }
        nextPlayer = players[count];
        return nextPlayer;
    }

    /**
     * 
     * @return last player
     */
    private Player lastPlayer()
    {
        count--;
        if (count < 0)
        {
            count = numPlayers - 1;
        }
        lastPlayer = players[count];
        return lastPlayer;
    }

    /**
     * To check the input valid.
     * @param "1" for challenge impossible, "2" for challenge now
     * @param player's equation
     * @return whether it obeys the rule or not
     */
    private boolean checkInput(int flag, String str)
    {

        int resource_dice_num = 0;
        switch (flag)
        {
            case 1 :
                for (int i = 0; i < myResources.getMyMat().size(); i++)
                {
                    if (!str.contains(this.dieFaceTranslator(
                            myResources.getMyMat().elementAt(i).getMyUpSide())))
                    {
                        return false;
                    }
                }

            case 2 :
                for (int i = 0; i < myResources.getMyMat().size(); i++)
                {
                    if (str.contains(this.dieFaceTranslator(
                            myResources.getMyMat().elementAt(i).getMyUpSide())))
                    {
                        resource_dice_num++;
                    }
                }

                if (resource_dice_num > 1)
                    return false;
        }

        for (int i = 0; i < myForbidden.getMyMat().size(); i++)
        {
            if (str.contains(this.dieFaceTranslator(
                    myForbidden.getMyMat().elementAt(i).getMyUpSide())))
            {
                return false;
            }
        }

        for (int i = 0; i < myRequired.getMyMat().size(); i++)
        {
            if (!str.contains(this.dieFaceTranslator(
                    myRequired.getMyMat().elementAt(i).getMyUpSide())))
            {
                return false;
            }
        }

        return true;

    }

    /**
     * Translate a dice face to string
     * TODO: Need to figure out the root symbol which solver can use
     * @param diceface
     * @return String
     */
    private String dieFaceTranslator(DiceFace d)
    {
        switch (d)
        {
            case ONE :
                return "1";

            case TWO :
                return "2";

            case THREE :
                return "3";

            case FOUR :
                return "4";

            case FIVE :
                return "5";

            case SIX :
                return "6";

            case SEVEN :
                return "7";

            case EIGHT :
                return "8";

            case NINE :
                return "9";

            case ZERO :
                return "0";

            case ADDITION :
                return "+";

            case SUBTRACTION :
                return "-";

            case MULTIPLICATION :
                return "*";

            case DIVISION :
                return "/";

            case POWER :
                return "^";

            case ROOT :
                return "?";

            case LEFT :
                return "(";

            case RIGHT :
                return ")";

        }
        return null;
    }

    /**
     * Once the player gives a valid equation, set the solver to check the answer
     * @param equation
     * @return correct or not
     */
    private boolean doingMath(String eq)
    {
        this.setSolver(eq);
        return solver.checkAnswer(solver.calculate(eq));
    }

    /**
     * Enable the player to enter their eqaution
     * @param player
     * @param equation
     */
    private void enteringEquation(Player p, String eq)
    {
        p.setEquation(eq);
    }

    
    public Player getNextPlayer()
    {
        return nextPlayer;
    }

    public Player getLastPlayer()
    {
        return lastPlayer;
    }

    public Mat getMyResources()
    {
        return myResources;
    }

    public Goal getMyGoal()
    {
        return myGoal;
    }

    public Mat getMyForbidden()
    {
        return myForbidden;
    }

    public Mat getMyPermitted()
    {
        return myPermitted;
    }

    public Mat getMyRequired()
    {
        return myRequired;
    }

}
