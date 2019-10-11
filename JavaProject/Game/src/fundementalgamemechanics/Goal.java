package fundementalgamemechanics;

import java.util.Vector;

public class Goal extends Mat {
	private Vector<DiceFace> myGoal;
	private int myNumNormalDice;
	
	public void Read() {
		myGoal.clear();
		for(int i = 0;i < this.getMyMat().size();i++) {
			myGoal.add(myMat.get(i).getMyUpSide());
		}
	}
	
	public void addPerenthises() {
		myMat.add(new SpecialDie(0));
		myMat.add(new SpecialDie(1));
	}
	
	@Override
	public void addToMyMat(Die in) {
		myNumNormalDice++;
		myMat.add(in);
	}
	
	public boolean removeDieByIndex(int index) {
		return false;
	}
	
	@Override
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
	
	public Vector<DiceFace> getMyGoal() {
		return getMyGoal();		
	}
	
	public int getMyNumNormalDice() {
		return myNumNormalDice;
	}
}
