package fundementalgamemechanics;

public interface Dice {

	public void roll();
	
	public DiceFace getMyUpSide();
	
	public int getMyRotation();
	
	public void rotateLeft();
	
	public void rotateRight();
	
}
