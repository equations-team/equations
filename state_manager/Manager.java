package test;
import java.util.Scanner;
public class Manager
{
    public static final int DICENUMBER = 24;
    public static final int REDNUMBER = 6;
    public static final int GREENNUMBER = 6;
    public static final int BLACKUMBER = 6;
    public static final int BLUENUMBER = 6;
    
    private Solver solver;
    private GameTimer timer;
    private Player[] players;
    
    private Mat myResources;
    private Goal myGoal;
    private Mat myForbidden;
    private Mat myPermitted;
    private Mat myRequired; 
    
    private Player currentPlayer;
    private int count;
    private int numPlayers;
//    private int Goalsetter;


    public Manager(Player[] p)
    {
        myResources = new Mat();
        myGoal = new Goal();
        myForbidden = new Mat();
        myPermitted = new Mat();
        myRequired = new Mat();
        timer = new GameTimer(1);
        players = p;
        numPlayers = players.length;
        
        for(int i = 0; i < DICENUMBER; i++) {
            if(i < REDNUMBER) {
                myResources.addToMyMat(new RedDie());
            }else if(i < REDNUMBER + BLUENUMBER) {
                myResources.addToMyMat(new BlueDie());
            }else if(i < REDNUMBER + BLUENUMBER + GREENNUMBER) {
                myResources.addToMyMat(new GreenDie());
            }else {
                myResources.addToMyMat(new BlackDie());
            }
        }
        
    }
    
    
    public void setFirstPlayer(Player greaterDice_player)
    {
        for(int i =0; i < players.length; i++) {
            if (players[i].getName() == greaterDice_player.getName()) {
                count = i;
            };
        }
        currentPlayer = greaterDice_player;
        
    }


    public void setSolver(String equation) {
        myGoal.Read();
        String goal = myGoal.getMyGoal();
        solver = new Solver(new Algebra(equation,goal) );
    }
    
    public void gameSetup(Player p,int[] goals) {
  
        for(int i = 0; i < goals.length; i++) {
            myGoal.addToMyMat(myResources.getMyMat().get(goals[i]));;
        }
        
        currentPlayer = this.nextPlayer();
        for(int i = 0; i< myResources.getMyMat().size(); i++) {
            myResources.getMyMat().elementAt(i).roll();
        }
    }
    
    
    
    
    
    
    

    /*
     * Basic game move logic, moving a dice or making a challenge.
     */
    // TODO: 
    // 1.Need to make sure the player's equation using the dices from the
    // game board
    //
    // 2.Make sure player after the goal setter cannot challenge
    // 3.Need player input information for challenge
    public boolean moveDie(Player p, int index, GameMove decision) {
        
        if(p.getName()!= currentPlayer.getName()) return false;
        
        Player p1;
        Die moved = myResources.getMyMat().get(index);
        if(moved == null) return false;
        myResources.removeDie(moved);
        switch(decision){
            case ADDFORBIDDEN:
                myForbidden.addToMyMat(moved);
                System.out.println(p.getName()+ " moves a dice to forbidden mat!");
                break;
            case ADDREQUIRED:
                myRequired.addToMyMat(moved);
                System.out.println(p.getName()+ " moves a dice to required mat!");
                break;
            case ADDPERMITTED:
                myPermitted.addToMyMat(moved);
                System.out.println(p.getName()+ " moves a dice to permitted mat!");
                break;
                
            case CHALLENGEIMPOSSIBLE :
                    
                        p1 = this.lastPlayer();
                        //For test purpose
                        System.out.println("Enter your equation according to the rule:");
                        Scanner sc = new Scanner(System.in);
                        this.enteringEquation(p1, sc.nextLine());
                        
                        if (this.doingMath(p1.getEquation()))
                        {
                            System.out.println(p1.getName() + "wins!");
                        } else
                        {
                            System.out.print(p.getName() + "wins!");
                        }
                break;
                
            case CHALLENGENOW :
                
                        p1 = this.lastPlayer();
                        
                        //For test purpose
                        System.out.println("Enter your equation according to the rule:");
                        Scanner sc1 = new Scanner(System.in);
                        this.enteringEquation(p, sc1.nextLine());
                        
                        if (this.doingMath(p.getEquation()))
                        {
                            System.out.println(p.getName() + "wins!");
                        } else
                        {
                            System.out.print(p1.getName() + "wins!");
                        }
                    
                
                break;
            
            default:
                throw new AssertionError("Please make a valid move!");
        }
        
        currentPlayer = nextPlayer();
        return true;
    }
    
       
    public Player nextPlayer() {        
        count++;
        if(count == numPlayers) {
            count = 0;
        }
        return players[count];
    }

    public Player lastPlayer() {
        count--;
        if(count < 0) {
            count = numPlayers-1;
        }
        return players[count];
    }
    

    private boolean doingMath(String eq)
    {
        this.setSolver(eq);
        return solver.checkAnswer(solver.calculate(eq));
    }

    private void enteringEquation(Player p, String eq)
    {
        p.setEquation(eq);
    }

    public Mat getMyResources() {
        return myResources;
    }

    public Goal getMyGoal() {
        return myGoal;
    }

    public Mat getMyForbidden() {
        return myForbidden;
    }


    public Mat getMyPermitted()
    {
        return myPermitted;
    }

    public Mat getMyRequired() {
        return myRequired;
    }   

}
