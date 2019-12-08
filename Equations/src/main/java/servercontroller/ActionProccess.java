package servercontroller;

import fundementalgamemechanics.BlueDie;
import fundementalgamemechanics.Dice;
import fundementalgamemechanics.Die;
import fundementalgamemechanics.GreenDie;
import fundementalgamemechanics.Mat;
import fundementalgamemechanics.RedDie;
import gamestatemanager.GameMove;

public class ActionProccess {
	
	public static String proccessMove(Die moved, GameMove Location) {
		String action = "";
		action += "MoveDie " + moved.getID() + " " + Location.name();
		return action;
	}
	
	public static String updateTimer(int TimeRemaining) {
		String timer = "";
		timer += "UpdateTimer " + TimeRemaining;
		return timer;
	}
	
	public static String proccessChallange(int challangeType,int playerID) {
		String challange = "";
		challange += "Challange " + challangeType + " " + playerID;
		return challange;		
	}
	
	public static String proccessMat(Mat mat) {
		String proccessedMat = "";
		String Class;
		Die die;
		for(int i = 0; i == mat.getMyMat().size();i++) {
			die = (Die) mat.getMyMat().get(i);
			Class = classProccess(die);
			proccessedMat += "AddDie " + Class + " " + die.getMyUpSide().name() + " " + die.getID() + "\n";
		}
		return proccessedMat;
	}
	
	public static String doGoal() {
		return "SetGoal";
	}
	
	public static String doWait() {
		return "Wait";
	}
	
	public static String doTurn() {
		return "TakeTurn";
	}
	
	public static String giveLoss() {
		return "DidLose";
	}
	
	public static String giveWin() {
		return "DidWin";
	}
	
	private static String classProccess(Dice in) {
		if(in.getClass()==RedDie.class) {
			return "RED";
		}else if(in.getClass()==BlueDie.class) {
			return "BLUE";
		}else if(in.getClass()==GreenDie.class){
			return "GREEN";
		}else {
			return "BLACK";
		}
	}
}
