package test;
import java.util.Random;

public class Dice 
{
    private Random r;
    private String color;
    private String[] values;
    private String top;
     
    public Dice(String[] s, String clr) {
        r = new Random();
        values = s;
        color = clr;
        top = values[0];
    }
     
    public void roll() {
        int temp = r.nextInt(6);
        top = values[temp];
    }

    public String getTop()
    {
        return top;
    }

    public String getColor()
    {
        return color;
    }

}
