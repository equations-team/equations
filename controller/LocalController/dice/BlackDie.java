package dice;

//This is the black die it contains a '√','÷','+','7','8', and '9' sides.

public class BlackDie extends Die{
	
	public final static DiceFace[] BLACKSIDES = new DiceFace[] {DiceFace.SEVEN,DiceFace.EIGHT,DiceFace.NINE,DiceFace.ADDITION,
			DiceFace.DIVISION,DiceFace.ROOT};
	
	public BlackDie() {
		roll();
	}
	
	public void roll() {
		roll(BLACKSIDES);
	}
}