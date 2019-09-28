package fundementalgamemechanics;

//This is the green die it contains a *, ×, -, 6, 5, and 4 sides.

public class GreenDie extends Die{
	
	public final static char[] GREENSIDES = new char[] {'*','×','-','6','5','4'};
	
	GreenDie() {
		roll();
	}
	
	public void roll() {
		roll(GREENSIDES);
	}
}
