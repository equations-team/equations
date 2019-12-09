package fundementalgamemechanics;

/**
 * A Blue Die that contains the 0, 1, 2, 3, Addition, Division, and Multiplication Symbols. Can be rolled to change the face.
 * @author James P Armstrong IV
 *
 */

public class BlueDie extends Die{
	
	public final static DiceFace[] BLUESIDES = new DiceFace[] {DiceFace.ZERO,DiceFace.ONE,DiceFace.TWO,DiceFace.THREE,
			DiceFace.DIVISION,DiceFace.MULTIPLICATION};
	
	public BlueDie() {
		roll();
	}
	
	/**
	 * Changes the showing face when called.
	 */
	public void roll() {
		roll(BLUESIDES);
	}
}

