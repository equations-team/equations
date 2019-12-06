package dice;

//This is the green die it contains a *, ×, -, 6, 5, and 4 sides.

public class GreenDie extends Die{
	
	public final static DiceFace[] GREENSIDES = new DiceFace[] {DiceFace.FOUR,DiceFace.FIVE,DiceFace.SIX,DiceFace.SUBTRACTION,
			DiceFace.POWER,DiceFace.MULTIPLICATION};
	
	public GreenDie() {
		this.setColor("GREEN");
		roll();
	}
	
	public void roll() {
		roll(GREENSIDES);
	}
}
