package fundementalgamemechanics;

public class Goal extends Mat {
	private String myGoal;
	
	public void Read() {
		myGoal = "";
		for(int i = 0;i < this.getMyMat().capacity();i++) {
			if(this.getMyMat().get(i)!=null) {
				myGoal += this.getMyMat().get(i).getMyUpSide();
			}
		}
	}
	
	public void addPerentisis(int firstIndex,int secondIndex) {
		String one = (String) myGoal.subSequence(0, firstIndex);
		String two = (String) myGoal.subSequence(firstIndex, secondIndex);
		String three = (String) myGoal.subSequence(secondIndex, myGoal.length());
		myGoal = one + "(" + two + ")" + three;
	}
}
