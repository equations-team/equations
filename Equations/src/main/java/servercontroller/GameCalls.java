package main.java.servercontroller;

import main.java.gamestatemanager.GameMove;

public interface GameCalls {
	public boolean StartTurn(int UserID);
	public boolean MoveDie(GameMove gameMat,int dieIndex);
	public boolean checkSolution(String solution);
	public boolean Win(int winner);
}
