package state_manager;

import test.GameMove;
import test.Manager;
import test.Player;

public class Main1
{

    public static void main(String[] args)
    {
        
        // TODO Auto-generated method stub
        Player[] test1 = new Player[3];
        test1[0] = new Player("Ruby");
        test1[1] = new Player("James");
        test1[2] = new Player("Nicola");
        
        int[] nums = new int[5];
        nums[1] = 1;
        nums[2] = 5;
        nums[3] = 4;
        nums[4] = 3;
        nums[0] = 2;

        
        Manager m = new Manager(test1);
        
        m.setFirstPlayer(test1[2]);
        m.gameSetup(test1[2], nums);
        
        m.moveDie(test1[0], 1, GameMove.ADDREQUIRED);
//        m.moveDie(test1[1], 1, GameMove.CHALLENGENOW);
        
        /*
        try {
            int n = Integer.parseInt("+");
            System.out.println(n);


        }catch(Exception e){
            System.out.println("Not int!");
        }
        
        */
        
        String str1 = "abc";
        String str2 = "a";
        
        System.out.println(!str1.contains(str2));

    }

}
