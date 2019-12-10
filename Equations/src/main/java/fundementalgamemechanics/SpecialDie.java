package fundementalgamemechanics;

/**
 * A die to be used as a dummy part that allowed (,) to be manipulated without altering functionality of Mat.
 * @author James P Armstrong IV
 */
public class SpecialDie extends Die {
	
	public final static DiceFace[] SPECIALSIDES = new DiceFace[] {DiceFace.LEFT,DiceFace.RIGHT,DiceFace.LEFT,
			DiceFace.RIGHT,DiceFace.LEFT,DiceFace.RIGHT};
	
	/**
	 * @param index the face to start up.
	 */
	public SpecialDie(int index) {
		selectSide(index);
	}
	
	/**
	 * @param index the face to turn up.
	 */
	public void selectSide(int index) {
		this.setMyUpSide(SPECIALSIDES[index]);
	}
	
	@Override
	public void roll() {
		
	}

}
