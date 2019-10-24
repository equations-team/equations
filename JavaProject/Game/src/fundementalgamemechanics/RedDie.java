package fundementalgamemechanics;

//This is the red die it contains a -, 0, 1, 2, 3, and + sides.

public class RedDie extends Die{
	
	public final static DiceFace[] REDSIDES = new DiceFace[] {DiceFace.ZERO,DiceFace.ONE,DiceFace.TWO,DiceFace.THREE
			,DiceFace.SUBTRACTION,DiceFace.ADDITION};
	
	public RedDie() {
		roll();
	}
	
	public void roll() {
		roll(REDSIDES);
	}
}
