package servercontroller;

import gamestatemanager.GameMove;

public interface GameCalls {
	public boolean StartTurn(int UserID);
	public boolean MoveDie(GameMove gameMat,int dieIndex);
	public boolean checkSolution(int challangeType, String solution);
	public boolean Win(int winner);
	public void goalStage(Caller goalSetter);
}
