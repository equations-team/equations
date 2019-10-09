package test;
import java.util.Scanner;
public class manager
{
    private Solver s;
    // private GameLogic gl;
    private Dice[]   d;
    private Board    b;
    private Player[] p;

    public manager(Solver s, Dice[] d, Player[] p)
    {
        this.s = s;
        this.d = d;
        this.p = p;
        this.b = new Board(d);
    }
    

    /*
     * Basic game move logic, moving a dice or making a challenge.
     */
    // TODO: Need to make sure the player's equation using the dices from the
    // game board
    public void moveDice(Player p, GameMove decision, Dice d)
    {
        if (p.getCheckTurn() == true)
        {
            Player p1;
            switch (decision)
            {
                case ADDREQUIRED :
                    b.addRequired(d);
                    break;
                case ADDPERMITTED :
                    b.addPermitted(d);
                    break;

                case ADDFORBIDDEN :
                    b.addForbidden(d);
                    break;

                case CHALLENGEIMPOSSIBLE :
                    for (int i = 0; i < this.p.length; i++)
                    {
                        if (this.p[i].isLastMove())
                        {
                            p1 = this.p[i];
                            Scanner sc = new Scanner(System.in);
                            this.enteringEquation(p1, sc.nextLine());
                            if (this.doingMath(p1.getEquation()))
                            {
                                System.out.println(p1.getName() + "wins!");
                            } else
                            {
                                System.out.print(p.getName() + "wins!");
                            }
                        } else
                        {
                            System.out.println("Cannot challenge for now!");
                        }
                    }

                    break;
                case CHALLENGENOW :
                    for (int i = 0; i < this.p.length; i++)
                    {
                        if (this.p[i].isLastMove())
                        {
                            p1 = this.p[i];
                            Scanner sc = new Scanner(System.in);
                            this.enteringEquation(p, sc.nextLine());
                            if (this.doingMath(p.getEquation()))
                            {
                                System.out.println(p.getName() + "wins!");
                            } else
                            {
                                System.out.print(p1.getName() + "wins!");
                            }
                        } else
                        {
                            System.out.println("Cannot challenge for now!");
                        }
                    }
                    break;

                default :
                    throw new AssertionError("Please make a valid move!");
            }

            for (int i = 0; i < this.p.length; i++)
            {
                if (this.p[i].isLastMove())
                {
                    this.p[i].setLastMove(false);
                }

                p.setLastMove(true);
            }
        }
    }

    private boolean doingMath(String eq)
    {
        return this.s.checkAnswer(this.s.calculate(eq));
    }

    private void enteringEquation(Player p, String eq)
    {
        p.setEquation(eq);
    }

    public Dice[] getD()
    {
        return d;
    }

    public Board getB()
    {
        return b;
    }


    public Player[] getP()
    {
        return p;
    }

}
