package localhostgame;

import java.util.Vector;

import dice.Die;
import dice.DieHolder;

//The Mat is a class that contains dice.

public class Mat implements DieHolder{
	
	protected Vector<Die> myMat = new Vector<Die>();
	
	public Vector<Die> getMyMat() {
		return myMat;
	}
	
	public boolean checkEmpty() {
		return myMat.size() == 0;
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
