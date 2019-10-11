package fundementalgamemechanics;

//This is the blue die it contains a '0','1','2','3','÷', and '×' sides.

public class BlueDie extends Die{
	
	public final static DiceFace[] BLUESIDES = new DiceFace[] {DiceFace.ZERO,DiceFace.ONE,DiceFace.TWO,DiceFace.THREE,
			DiceFace.DIVISION,DiceFace.MULTIPLICATION};
	
	public BlueDie() {
		roll();
	}
	
	public void roll() {
		roll(BLUESIDES);
	}
}

