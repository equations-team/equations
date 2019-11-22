package fundementalgamemechanics;

import java.util.Random;

/**
 * An abstracts that implements the functions from Dice that will be used the same by all dice.
 * @author James P Armstrong IV
 *
 */

public abstract class Die implements Dice{
	
	private DiceFace myUpSide;
	private int myRotation;
	
	public static final Random diceRoller = new Random(System.currentTimeMillis());
	
	/**
	 * @param inPutString A DiceFace to be made the up facing side.
	 */
	protected void setMyUpSide(DiceFace inPutString) {
		myUpSide = inPutString;
	}
	
	public DiceFace getMyUpSide() {
		return myUpSide;
	}
	
	public void roll(DiceFace[] sides) {
		myUpSide = sides[diceRoller.nextInt(6)];
	}
	
	public abstract void roll();
	
	public int getMyRotation() {
		return myRotation;
	}
	
	public void rotateLeft() {
		if(myRotation < 8) {
			myRotation++;
		}else {
			myRotation = 0;
		}
	}
	
	public void rotateRight() {
		if(myRotation > 0) {
			myRotation--;
		}else {
			myRotation = 8;
		}
	}
}
