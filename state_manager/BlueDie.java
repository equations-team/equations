package test;

//This is the red die it contains a '0','1','2','3','¡Â', and '¡Á' sides.

public class BlueDie extends Die{
	
	public final static String[] BLUESIDES = new String[] {"0","1","2","3","¡Â","¡Á"};
	
	BlueDie() {
		roll();
	}
	
	public void roll() {
		roll(BLUESIDES);
	}
}

