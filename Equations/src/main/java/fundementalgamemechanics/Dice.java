package fundementalgamemechanics;

/**
 * Interface that defines the functionality of a die.
 * @author James P Armstrong IV
 *
 */
public interface Dice {

	/**
	 * Changes the up facing Side.
	 */
	public void roll();
	
	/**
	 * @return Face The upfacing Side
	 */
	public DiceFace getMyUpSide();
	
	/**
	 * @return Rotation of the die
	 */
	public int getMyRotation();
	
	/**
	 * Turn the die 45 degrees left.
	 */
	public void rotateLeft();
	
	/**
	 * Turn the die 45 degrees right.
	 */
	public void rotateRight();
	
}
