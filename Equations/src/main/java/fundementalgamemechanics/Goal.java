package fundementalgamemechanics;

import java.util.Vector;

/**
 * A special mat that can not have dice removed, and can have perenthises added.
 * @author James P Armstrong IV
 *
 */

public class Goal extends Mat implements MatReader{
	private Vector<DiceFace> myGoal = new Vector<DiceFace>();
	private int myNumNormalDice;
	
	/**
	 * Updates the Goal Vector with the contents of the object.
	 */
	public void Read() {
		myGoal.clear();
		for(int i = 0;i < this.getMyMat().size();i++) {
			myGoal.add(myMat.get(i).getMyUpSide());
		}
	}
	
	/**
	 * Adds an open and close perenthises to the object.
	 */
	public void addPerenthises() {
		myMat.add(new SpecialDie(0));
		myMat.add(new SpecialDie(1));
	}
	
	@Override
	public void addToMyMat(Die in) {
		myNumNormalDice++;
		myMat.add(in);
	}
	
	/**
	 * @return bool false dice can not be removed.
	 */
	public boolean removeDieByIndex(int index) {
		return false;
	}
	
	/**
	 * @return Goal A vector of the dice faces on the mat.
	 */
	public Vector<DiceFace> getMyGoal() {
		return myGoal;		
	}
	
	/**
	 * The number of non-special dice in the mat.
	 */
	public int getMyNumNormalDice() {
		return myNumNormalDice;
	}
}
