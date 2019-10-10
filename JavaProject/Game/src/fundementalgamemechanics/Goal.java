package fundementalgamemechanics;

public class Goal extends Mat {
	private String myGoal;
	
	public Goal() {
		this.addToMyMat(new SpecialDie(2));
	}
	
	public void Read() {
		myGoal = "";
		for(int i = 0;i < this.getMyMat().capacity();i++) {
			if(this.getMyMat().get(i)!=null) {
				myGoal += this.getMyMat().get(i).getMyUpSide();
			}
		}
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
	
}
