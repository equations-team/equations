package fundementalgamemechanics;

//This is the blue die it contains a '0','1','2','3','÷', and '×' sides.

public class BlueDie extends Die{
	
	public final static String[] BLUESIDES = new String[] {"0","1","2","3","÷","×"};
	
	public BlueDie() {
		roll();
	}
	
	public void roll() {
		roll(BLUESIDES);
	}
}

