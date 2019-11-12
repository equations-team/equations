package fundementalgamemechanics;

import java.util.Vector;

/**
 * The Interface for Goal
 * @author James P Armstrong IV
 *
 */
public interface MatReader {
	
	public void Read();
	
	public void addPerenthises();
	
	public Vector<DiceFace> getMyGoal();
	
	public int getMyNumNormalDice();
}
