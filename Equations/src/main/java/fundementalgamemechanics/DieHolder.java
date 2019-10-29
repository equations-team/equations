package fundementalgamemechanics;

import java.util.Vector;

public interface DieHolder {

	public Vector<Die> getMyMat();
	
	public boolean checkEmpty();
	
	public void addToMyMat(Die in);
	
	public boolean removeDieByIndex(int index);
	
	public boolean removeDie(Die out);

	public boolean reorderDice(int firstIndex,int secondIndex);
}
