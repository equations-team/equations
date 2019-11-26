package fundementalgamemechanics;

import java.util.Vector;

/**
 * A interfact to hold the functions used by Mats and goal mats.
 * @author James P Armstrong IV
 *
 */

public interface DieHolder {

	/**
	 * @return Dice A vector of dice contaned by the object.
	 */
	public Vector<Dice> getMyMat();
	
	/**
	 * @return bool true if empty false elsewise.
	 */
	public boolean checkEmpty();
	
	/**
	 * @param in The die to add to the object.
	 */
	public void addToMyMat(Dice in);
	
	/**
	 * @param index the index of the die to remove.
	 * @return bool true if successful false elsewise
	 */
	public boolean removeDieByIndex(int index);
	
	/**
	 * @param out the die to remove
	 * @return bool true if successful false elsewise
	 */
	public boolean removeDie(Dice out);

	/**
	 * @param firstIndex number to replace with the other die
	 * @param secondIndex number to replace with the first die
	 * @return bool true if successful false elsewise
	 */
	public boolean reorderDice(int firstIndex,int secondIndex);
}
