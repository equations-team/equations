package fundementalgamemechanics;

//This is a class that contains the state of the game.

public class Game {
	
	public static final int DICENUMBER = 24;
	public static final int REDNUMBER = 6;
	public static final int GREENNUMBER = 6;
	public static final int BLACKUMBER = 6;
	public static final int BLUENUMBER = 6;
	
	Game() {
		for(int i = 0; i < DICENUMBER; i++) {
			if(i < REDNUMBER) {
				myResources.addToMyMat(new RedDie());
			}else if(i < REDNUMBER + BLUENUMBER) {
				myResources.addToMyMat(new BlueDie());
			}else if(i < REDNUMBER + BLUENUMBER + GREENNUMBER) {
				myResources.addToMyMat(new GreenDie());
			}else {
				myResources.addToMyMat(new BlackDie());
			}
		}	
	}
	
	public int moveDie(int index, int destination) {
		Die moved = myResources.getMyMat().get(index);
		if(moved == null) return -1;
		myResources.removeDie(moved);
		switch(destination){
			case 0:
				myGoal.addToMyMat(moved);
				break;
			case 1:
				myForbidden.addToMyMat(moved);
				break;
			case 2:
				myRequired.addToMyMat(moved);
				break;
			default:
				myAllowed.addToMyMat(moved);
				break;
		}
		return myResources.getMyMat().size();
	}
	
	private Mat myResources = new Mat();
	
	private Mat myGoal = new Mat();
	
	private Mat myForbidden = new Mat();
	
	private Mat myAllowed = new Mat();
	
	private Mat myRequired = new Mat();

	public Mat getMyResources() {
		return myResources;
	}

	public Mat getMyGoal() {
		return myGoal;
	}

	public Mat getMyForbidden() {
		return myForbidden;
	}

	public Mat getMyAllowed() {
		return myAllowed;
	}

	public Mat getMyRequired() {
		return myRequired;
	}	
}
