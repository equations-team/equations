package servercontroller;

import fundementalgamemechanics.DiceFace;
import fundementalgamemechanics.Die;

//An object used by the controller to track dice.
public class IDDIE {
	Die me;
	String myColor;
	DiceFace myFace;
	int myID;
	
	IDDIE(Die you, int id){
		me = you;
		myColor = ActionProccess.classProccess(me);
		myFace = me.getMyUpSide();
		myID = id;
	}
	
	public int getID() {
		return myID;
	}
	
	public Die getDie() {
		return me;
	}
	
	@Override
	public String toString() {
		return myColor + " " + myFace.name() + " " + myID;
	}
}
