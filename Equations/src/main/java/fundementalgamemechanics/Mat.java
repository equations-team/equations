package fundementalgamemechanics;

import java.util.Vector;

/**
 * Mat is a class that allows the holding and ordering of dice.
 * @author James P Armstrong IV
 */
public class Mat implements DieHolder{
	
	protected Vector<Die> myMat = new Vector<Die>();
	
	/**
	 * @return Vector[Die] The Vector of dice.
	 */
	public Vector<Die> getMyMat() {
		return myMat;
	}
	
	/**
	 * @return true if the mat is empty false otherwise.
	 */
	public boolean checkEmpty() {
		return myMat.size() == 0;
	}
  
	/**
	 * @param in the Die to add to the Mat
	 */
	public void addToMyMat(Die in) {
		myMat.add(in);
	}
	
	/**
	 * Removes a die from the mat based on the index entered.
	 * @param index an int of the Die to remove.
	 * @return true if the die existed and was removed, false if not.
	 */
	public boolean removeDieByIndex(int index) {
		try {
			myMat.get( index );
		} catch ( IndexOutOfBoundsException e ) {
			return false;
		}
		myMat.removeElementAt(index);
		return true;
	}
	
	/**
	 * Removes a die when given the die.
	 * @param die to find and remove.
	 * @return true if the element was removed. false if not.
	 */
	public boolean removeDie(Die out) {
		return myMat.removeElement(out);
	}

	/**
	 * Switches the dice in the mat.
	 * @param firstIndex the location of the first die to move.
	 * @param secondIndex the location of the second die to move.
	 * @return true if successful and false when not.
	 */
	public boolean reorderDice(int firstIndex,int secondIndex) {
		Die first;
		try {
			first = myMat.get(firstIndex);
		} catch ( IndexOutOfBoundsException e ) {
			return false;
		}
		Die second;
		try {
			second = myMat.get(secondIndex);
		} catch ( IndexOutOfBoundsException e ) {
			return false;
		}
		if(firstIndex < secondIndex) {
			secondIndex--;
		}
		myMat.remove(firstIndex);
		myMat.remove(secondIndex);
		myMat.insertElementAt(first, secondIndex);
		myMat.insertElementAt(second, firstIndex);
		return true;
	}
}
