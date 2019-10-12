package test;

//This is the green die it contains a *, x, -, 6, 5, and 4 sides.

public class GreenDie extends Die{
	
	public final static String[] GREENSIDES = new String[] {"*","¡Á","-","6","5","4"};
	
	GreenDie() {
		roll();
	}
	
	public void roll() {
		roll(GREENSIDES);
	}
}
