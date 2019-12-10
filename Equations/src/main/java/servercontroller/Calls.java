package servercontroller;

import gamestatemanager.GameMove;

//Calls from this to

public interface Calls {
	void Start();
	void Challenge(int challengetype,int playerid);
	void SetGoal(int[] diceids);
	void Move(int dieid,GameMove move);
	void Solution(String solution,int playerid);
}
