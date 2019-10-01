package test;
import java.util.Random;

public class Dice 
{
    private Random r;
    private String color;
    private String[] values;
    
    public Dice(String[] s, String clr) {
        r = new Random();
        values = s;
        color = clr;
    }
     
    public String roll() {
        int temp = r.nextInt(6);
        return values[temp];
    }

    public String getColor()
    {
        return color;
    }

}
