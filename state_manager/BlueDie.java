package test;

//This is the red die it contains a '0','1','2','3','��', and '��' sides.

public class BlueDie extends Die{
	
	public final static String[] BLUESIDES = new String[] {"0","1","2","3","��","��"};
	
	BlueDie() {
		roll();
	}
	
	public void roll() {
		roll(BLUESIDES);
	}
}

