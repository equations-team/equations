package fundementalgamemechanics;

/**
 * A Green die that has 4, 5, 6, subtraction, exponents, multiplication symbol faces.
 * @author James P Armstrong IV
 *
 */

public class GreenDie extends Die{
	
	public final static DiceFace[] GREENSIDES = new DiceFace[] {DiceFace.FOUR,DiceFace.FIVE,DiceFace.SIX,DiceFace.SUBTRACTION,
			DiceFace.POWER,DiceFace.MULTIPLICATION};
	
	public GreenDie() {
		roll();
	}
	
	public void roll() {
		roll(GREENSIDES);
	}
}
