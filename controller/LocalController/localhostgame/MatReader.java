package localhostgame;

import java.util.Vector;

import dice.DiceFace;

public interface MatReader {
	
	public void Read();
	
	public void addPerenthises();
	
	public Vector<DiceFace> getMyGoal();
	
	public int getMyNumNormalDice();
}
