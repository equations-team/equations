package fundementalgamemechanics;

//This is the green die it contains a *, ×, -, 6, 5, and 4 sides.

public class GreenDie extends Die{
	
	public final static String[] GREENSIDES = new String[] {"*","×","-","6","5","4"};
	
	public GreenDie() {
		roll();
	}
	
	public void roll() {
		roll(GREENSIDES);
	}
}
