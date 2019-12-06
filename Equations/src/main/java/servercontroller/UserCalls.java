package servercontroller;

public interface UserCalls {

	public void StartPlayer(Caller playerStart);
	public void WaitPlayer(Caller playerWait);
	public boolean UpdatePlayer(Caller playerUpdate,String Action);
	public boolean Challenge(Caller challangingPlayer);
	public boolean CallToSolve(Caller challangedPlayer);
	public boolean CheckSolution(Caller solvingPlayer, String solution);
	public boolean Inform(Caller informedPlayer, boolean didWin);
	public boolean InformLoss(Caller informedPlayer);
	public boolean InformWin(Caller informedPlayer);
	boolean SetGoal(int[] GoalDice);
}
