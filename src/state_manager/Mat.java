package test;

import java.util.Vector;

//The Mat is a class that contains dice.

public class Mat {
	
	private Vector<Die> myMat ;
	
	public Mat() {
	    myMat  = new Vector<Die>();
	};
	
	public Vector<Die> getMyMat() {
		return myMat;
	}
	
	public void addToMyMat(Die in) {
		myMat.add(in);
	}
	
	//This is only meant for use by the Resource Mat
	public boolean removeDie(Die out) {
		return myMat.removeElement(out);
	}

	public boolean reorderDice(int firstIndex,int secondIndex) {
		Die first = myMat.get(firstIndex);
		Die second = myMat.get(secondIndex);
		myMat.remove(firstIndex);
		myMat.remove(secondIndex);
		myMat.insertElementAt(first, secondIndex);
		myMat.insertElementAt(second, firstIndex);
		return true;
	}
}
