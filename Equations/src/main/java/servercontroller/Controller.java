package main.java.servercontroller;

import main.java.gamestatemanager.Manager;

import java.util.Vector;

import main.java.fundementalgamemechanics.DiceInterpreter;
import main.java.gamestatemanager.GameMove;
import main.java.servercontroller.*;

public class Controller implements UserCalls, GameCalls{

	private Caller[] myPlayers;
	private Manager myGame;
	
	Controller(Caller[] Players,Manager Game){
		myPlayers = Players;
		myGame = Game;
	}
	
	
	@Override
	public boolean StartTurn(int UserID) {
		Vector<Integer> players = new Vector<Integer>();
		for(int i = 0;i < myPlayers.length;i++)
			players.add(i);
		for(int i = 0;i < myPlayers.length;i++)
			if(myPlayers[i].PlayerID() == UserID) {
				for(int j = 0;j < players.size();j++)
					if(j != i)
						this.WaitPlayer(myPlayers[j]);
				this.StartPlayer(myPlayers[i]);
				return true;
			}
		return false;
	}

	@Override
	public boolean MoveDie(GameMove gameMat, int dieIndex) {
		return myGame.moveDie(dieIndex, gameMat);
	}

	@Override
	public boolean checkSolution(String solution) {
		return myGame.Solution(solution);
	}

	@Override
	public boolean Win(int winner) {
		Vector<Integer> players = new Vector<Integer>();
		for(int i = 0;i < myPlayers.length;i++)
			players.add(i);
		for(int i = 0;i < myPlayers.length;i++)
			if(myPlayers[i].PlayerID() == winner) {
				for(int j = 0;j < players.size();j++)
					if(j != i)
						this.Inform(myPlayers[j],false);
				return this.Inform(myPlayers[i],true);
			}
		return false;
	}

	@Override
	public void StartPlayer(Caller playerStart) {
		playerStart.Start();
	}

	@Override
	public void WaitPlayer(Caller playerWait) {
		playerWait.Wait();
	}

	@Override
	public boolean UpdatePlayer(Caller playerUpdate) {
		for(int i = 0;i < myPlayers.length;i++)
			if(myPlayers[i] == playerUpdate)
				return myPlayers[i].UpdateUI();
		return false;
	}

	@Override
	public boolean Challenge(Caller challangingPlayer) {
		Vector<Integer> players = new Vector<Integer>();
		for(int i = 0;i < myPlayers.length;i++)
			players.add(i);
		for(int i = 0;i < myPlayers.length;i++)
			if(myPlayers[i] == challangingPlayer)
				for(int j = 0;j < players.size();j++)
					if(j != i)
						this.CallToSolve(myPlayers[j]);
		return false;
	}

	@Override
	public boolean CallToSolve(Caller challangedPlayer) {
		return challangedPlayer.Challange();
	}

	@Override
	public boolean CheckSolution(Caller solvingPlayer, String solution) {
		if(checkSolution(solution)) {
			return Inform(solvingPlayer, true);			
		}else {
			return Inform(solvingPlayer, false);
		}
	}

	@Override
	public boolean Inform(Caller informedPlayer, boolean didWin) {
		if(didWin) {
			this.InformWin(informedPlayer);
		}else {
			this.InformLoss(informedPlayer);
		}
		return false;
	}

	@Override
	public boolean InformLoss(Caller informedPlayer) {
		return informedPlayer.Lose();
	}

	@Override
	public boolean InformWin(Caller informedPlayer) {
		return informedPlayer.Win();
	}

}
