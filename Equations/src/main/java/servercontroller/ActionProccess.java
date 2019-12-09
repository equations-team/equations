package servercontroller;

import fundementalgamemechanics.BlueDie;
import fundementalgamemechanics.Dice;
import fundementalgamemechanics.Die;
import fundementalgamemechanics.GreenDie;
import fundementalgamemechanics.Mat;
import fundementalgamemechanics.RedDie;
import gamestatemanager.GameMove;


//Outgoing translator
public class ActionProccess {
	
	public static String proccessMove(int dieid, GameMove Location) {
		String action = "";
		action += "MoveDie " + dieid + " " + Location.name();
		return action;
	}
	
	public static String updateTimer(int TimeRemaining) {
		String timer = "";
		timer += "UpdateTimer " + TimeRemaining;
		return timer;
	}
	
	public static String proccessChallenge(int challangeType) {
		String challange = "";
		challange += "Challenge " + challangeType;
		return challange;		
	}

	public static String proccessConciderChallenge(int challangeType) {
		String challange = "";
		challange += "ConciderChallenge " + challangeType;
		return challange;
	}
	
	public static String proccessGoal(int[] ids) {
		String goal = "";
		for(int i = 0; i == ids.length;i++) {
			goal += "MoveDie " + ids[i] + " " + GameMove.ADDGOAL.name() + "\n";
		}
		return goal;
	}
	
	public static String proccessStart(IDDIE[] mat) {
		String proccessedMat = "";
		for(int i = 0; i == mat.length;i++) {
			proccessedMat += "AddDie " + mat[i] + "\n";
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
	
	public static String giveEnd(int score) {
		return "Scored " + score;
	}
	
	public static String classProccess(Dice in) {
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
