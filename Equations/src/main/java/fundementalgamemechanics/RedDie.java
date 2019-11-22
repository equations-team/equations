package fundementalgamemechanics;

/**
 * 
 * A Red die that has 0, 1, 2, 3, subtraction, addition symbols.
 * @author James P Armstrong IV
 * 
 */

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
