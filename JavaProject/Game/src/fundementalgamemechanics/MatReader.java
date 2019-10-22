package fundementalgamemechanics;

import java.util.Vector;

public interface MatReader {
	
	public void Read();
	
	public void addPerenthises();
	
	public Vector<DiceFace> getMyGoal();
	
	public int getMyNumNormalDice();
}
