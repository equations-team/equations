package fundementalgamemechanics;

public interface Dice {

	public void roll();
		
	public void setMyUpSide(DiceFace d);
	
	public DiceFace getMyUpSide();
	
	public int getMyRotation();
	
	public void rotateLeft();
	
	public void rotateRight();
	
}
