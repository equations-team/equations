package fundementalgamemechanics;

import java.util.Random;

//Die is an abstract class to hold the various dice used in the equations game. Dice have six sides, can be rolled to randomly bring a side up, has an individual up side, and a rotation.

public abstract class Die {
	
	private char myUpSide;
	private int myRotation;
	
	public static final Random diceRoller = new Random(System.currentTimeMillis());
	
	public int getMyUpSide() {
		return myUpSide;
	}
	
	public void roll(char[] sides) {
		myUpSide = sides[diceRoller.nextInt(6)];
	}
	
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
