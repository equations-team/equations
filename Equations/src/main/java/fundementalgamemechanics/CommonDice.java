package fundementalgamemechanics;

public class CommonDice implements Dice {
	
	private DiceFace myUpSide;

	public DiceFace[] myDices = new DiceFace[] {DiceFace.ZERO,DiceFace.ONE,DiceFace.TWO,DiceFace.THREE
			,DiceFace.SUBTRACTION,DiceFace.ADDITION};
	
	public CommonDice() {
		myUpSide = myDices[0];
	}
	
	public void setMyUpSide(DiceFace d) {
		myUpSide = d;
	}

	public DiceFace getMyUpSide() {
		// TODO Auto-generated method stub
		return myUpSide;
	}

	public int getMyRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	public void rotateRight() {
		// TODO Auto-generated method stub
		
	}

	public void roll() {
		// TODO Auto-generated method stub
		
	}
	
	

}
