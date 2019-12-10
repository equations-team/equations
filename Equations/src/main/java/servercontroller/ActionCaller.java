package servercontroller;

import java.util.Vector;

import gamestatemanager.GameMove;

//Incoming Translator
public class ActionCaller {

	private Controller myMaster;
	
	ActionCaller(Controller master){
		myMaster = master;
	}
	
	public void TranslateCall(String input) {
		String[] parseStage1 = stage1Parse(input);
		switch(parseStage1[0]){
		case "MoveDie":
			CallMove(parseStage1[1],parseStage1[2]);
			break;
		case "SetGoal":
			CallSetGoal(parseStage1);
			break;
		case "Challenge":
			CallChallenge(parseStage1[1],parseStage1[2]);
			break;
		case "Solution":
			CallSolution(parseStage1[1],parseStage1[2]);
			break;
		case "No":
			CallNo(parseStage1[1]);
			break;
		}
	}

	private void CallNo(String playerid) {
		int playerId = Integer.parseInt(playerid);
		myMaster.Solution("NO", playerId);
	}

	//EXPECTS FORM "Challenge *SOLUTION* *YOUROWNID"
	private void CallSolution(String solution, String playerid) {
		solution = solution.replace('#', ' ');
		int playerId = Integer.parseInt(playerid);
		myMaster.Solution(solution, playerId);
	}

	//EXPECTS FORM "Challenge *CHALLENGETYPE* *YOUROWNID"
	private void CallChallenge(String challenge, String playerid) {
		int challengeType = Integer.parseInt(challenge);
		int playerId = Integer.parseInt(playerid);
		myMaster.Challenge(challengeType, playerId);
	}

	//EXPECTS FORM "SetGoal *DIEID* ... *DIEID*"
	private void CallSetGoal(String[] parse) {
		int[] diceids = new int[parse.length-1];
		for(int i = 1;i < parse.length;i++)
			diceids[i-1] = Integer.parseInt(parse[i]); 
		myMaster.SetGoal(diceids);
	}

	//EXPECTS FORM "MoveDie *DIEID* *GAMEMOVEENUMNAME*"
	private void CallMove(String dieid, String movename) {
		int dieID = Integer.parseInt(dieid);
		GameMove move = GameMove.valueOf(movename);
		myMaster.Move(dieID, move);
	}

	private String[] stage1Parse(String input) {
		int number = 0;
		Vector<String> parts = new Vector<String>();
		String temp = "";
		for(int i = 0;i < input.length();i++) {
			if(input.charAt(i)==' ') {
				number++;
				parts.add(temp);
				temp = "";
			}else {
				temp += input.charAt(i);
			}
		}
		String[] out = new String[number];
		for(int i = 0;i < number;i++)
			out[i] = parts.get(i);
		return out;
	}
}
