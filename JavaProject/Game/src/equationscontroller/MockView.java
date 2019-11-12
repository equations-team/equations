
/**
 * This is a MOCK VIEW. It is not the real view. It is useed purely for testing purposes.
 * @author Stephen Mingolelli
 *
 */
public class MockView {
	private Equations_Controller myController;
	private int myTurn;
	
	public MockView(Equations_Controller c ) {
		myController = c;
		myTurn = c.getTurn();
	}
	
	public void setGoalSetter(int h) {
		myTurn = h;
	}
	
	public void setTurn(int t) {
		myTurn = t;
	}
	
	public int getGoalSet() {
		System.out.print("Turn: "+myTurn);
		return myTurn;
	}
	

}
