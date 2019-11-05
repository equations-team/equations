package gamestatemanager;

public class OpponentAI extends AbstractPlayer {
	
	/**
	 * Will need to add Equation Constraint Solver when that is created
	 */
	public OpponentAI(Manager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Will end up calling constraint solver when it is created
	 */
	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		
		//Will end up passing self to move dice as well as the decision from the
		// to determine the index and decision
		//gsm.moveDie(p, index, decision)

	}

}
