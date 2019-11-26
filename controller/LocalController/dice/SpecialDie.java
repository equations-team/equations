package dice;

//A special die to be used by the system for functionalities.

public class SpecialDie extends Die {
	
//	~ is used as an unsused string space this may be replaced at a later time if another character is needed.
	public final static DiceFace[] SPECIALSIDES = new DiceFace[] {DiceFace.LEFT,DiceFace.RIGHT,DiceFace.LEFT,
			DiceFace.RIGHT,DiceFace.LEFT,DiceFace.RIGHT};
	
	public SpecialDie(int index){
		
	}
	
	public void selectSide(int index) {
		this.setMyUpSide(SPECIALSIDES[index]);
	}
	
	@Override
	public void roll() {
		
	}

}
