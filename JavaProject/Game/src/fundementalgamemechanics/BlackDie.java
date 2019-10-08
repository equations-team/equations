package fundementalgamemechanics;

//This is the green die it contains a '√','÷','+','7','8', and '9' sides.

public class BlackDie extends Die{
	
	public final static String[] BLACKSIDES = new String[] {"√","÷","+","7","8","9"};
	
	BlackDie() {
		roll();
	}
	
	public void roll() {
		roll(BLACKSIDES);
	}
}