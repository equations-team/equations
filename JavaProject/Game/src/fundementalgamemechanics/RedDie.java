package fundementalgamemechanics;

//This is the red die it contains a -, 0, 1, 2, 3, and + sides.

public class RedDie extends Die{
	
	public final static String[] REDSIDES = new String[] {"0","1","2","3","-","+"};
	
	RedDie() {
		roll();
	}
	
	public void roll() {
		roll(REDSIDES);
	}
}
