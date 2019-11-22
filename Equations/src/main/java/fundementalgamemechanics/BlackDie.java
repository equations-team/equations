package fundementalgamemechanics;

/**
 * The Black Die that has 7, 8, 0, addition, division, and root symbol sides. Can be rolled to change the up facing side.
 * @author James P Armstrong IV
 *
 */

public class BlackDie extends Die{
	
	public final static DiceFace[] BLACKSIDES = new DiceFace[] {DiceFace.SEVEN,DiceFace.EIGHT,DiceFace.NINE,DiceFace.ADDITION,
			DiceFace.DIVISION,DiceFace.ROOT};
	
	public BlackDie() {
		roll();
	}
	
	/**
	 * Changes the Showing die face when called.
	 */
	public void roll() {
		roll(BLACKSIDES);
	}
}