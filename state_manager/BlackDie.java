package state_manager;

//This is the green die it contains a '¡Ì','¡Â','+','7','8', and '9' sides.

public class BlackDie extends Die{
	
	public final static String[] BLACKSIDES = new String[] {"¡Ì","¡Â","+","7","8","9"};
	
	BlackDie() {
		roll();
	}
	
	public void roll() {
		roll(BLACKSIDES);
	}
}