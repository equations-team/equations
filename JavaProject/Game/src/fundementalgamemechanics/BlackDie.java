package fundementalgamemechanics;

//This is the green die it contains a '√','÷','+','7','8', and '9' sides.

public class BlackDie extends Die{
	
	public final static char[] BLACKSIDES = new char[] {'√','÷','+','7','8','9'};
	
	BlackDie() {
		roll();
	}
	
	public void roll() {
		roll(BLACKSIDES);
	}
}