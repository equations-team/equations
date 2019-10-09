package fundementalgamemechanics;

import java.util.Vector;

//The Mat is a class that contains dice.

public class Mat {
	
	private Vector<Die> myMat = new Vector<Die>();
	
	public Vector<Die> getMyMat() {
		return myMat;
	}
	
	public void addToMyMat(Die in) {
		myMat.add(in);
	}
	
	public boolean removeDieByIndex(int index) {
		try {
			myMat.get( index );
		} catch ( IndexOutOfBoundsException e ) {
			return false;
		}
		myMat.removeElementAt(index);
		return true;
	}
	
	//This is only meant for use by the Resource Mat
	public boolean removeDie(Die out) {
		return myMat.removeElement(out);
	}

	public boolean reorderDice(int firstIndex,int secondIndex) {
		Die first = myMat.get(firstIndex);
		Die second = myMat.get(secondIndex);
		myMat.remove(firstIndex);
		myMat.remove(secondIndex-1);
		myMat.insertElementAt(first, secondIndex-1);
		myMat.insertElementAt(second, firstIndex);
		return true;
	}
}
