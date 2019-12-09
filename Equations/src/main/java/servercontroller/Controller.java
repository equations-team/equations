package servercontroller;

import gamestatemanager.Manager;
import gamestatemanager.Player;
import fundementalgamemechanics.BlackDie;
import fundementalgamemechanics.BlueDie;
import fundementalgamemechanics.Die;
import fundementalgamemechanics.GreenDie;
import fundementalgamemechanics.Mat;
import fundementalgamemechanics.RedDie;
import gamestatemanager.GameMove;

public class Controller implements Calls{
	private Manager myGame;
	private Caller[] myPlayers;
	private IDDIE[] myDice;
	private int lastTurnPlayer;
	
	private int myChallengeType;
	private int response;
	private String[] Solutions;

	Controller(Caller[] players){
		myPlayers = players;
	}

	@Override
	public void Start() {
		Die[] dice = gameDice();
		Player[] players = makePlayers();
		myGame = new Manager(players, dice);
		myGame.setFirstPlayer();
		lastTurnPlayer = findPlayer(players,myGame.getGoalSetter());
		setDice(myGame.getMyResources());
		for(int i = 0;i < myPlayers.length;i++) {
			myPlayers[i].UpdateUI(ActionProccess.proccessStart(myDice));
			if(i == lastTurnPlayer) {
				myPlayers[i].SetGoal(ActionProccess.doGoal());
			}else
				myPlayers[i].Wait(ActionProccess.doWait());
		}
	}
	
	private void setDice(Mat myResources) {
		myDice = new IDDIE[myResources.getMyMat().size()];
		for(int i = 0;i < myDice.length;i++)
			myDice[i] = new IDDIE((Die) myResources.getMyMat().get(i),i);
	}

	private int findPlayer(Player[] players, Player test) {
		for(int i = 0;i < players.length;i++)
			if(players[i].equals(test))
				return i;
		return -1;
	}

	private Player[] makePlayers() {
		Player[] players = new Player[myPlayers.length];
		for(int i = 0;i < players.length;i++)
			players[i] = new Player(myPlayers[i].PlayerNamer());
		return players;
	}

	private Die[] gameDice() {
		Die[] dice = new Die[24];
		for(int i = 0; i < 24; i++) {
			if(i < 6) {
				dice[i] = (new RedDie());
			}else if(i < 12) {
				dice[i] = (new BlueDie());
			}else if(i < 18) {
				dice[i] = (new GreenDie());
			}else {
				dice[i] = (new BlackDie());
			}
		}	
		return dice;
	}

	@Override
	public void Challenge(int challengetype, int playerid) {
		response = 1;
		Solutions = new String[myPlayers.length];
		myChallengeType = challengetype;
		int index = findPlayer(playerid);
		Solutions[index] = "NO";
		myPlayers[index].Wait(ActionProccess.doWait());
		myPlayers[lastTurnPlayer].Challange(ActionProccess.proccessChallenge(challengetype));
		for(int i = 0;i < myPlayers.length;i++)
			if(i!=index&&i!=lastTurnPlayer)
				myPlayers[i].ConciderChallange(ActionProccess.proccessConciderChallenge(challengetype));
	}
	
	private int nextPlayer() {
		if(lastTurnPlayer == myPlayers.length-1)
			return 0;
		return lastTurnPlayer + 1;
	}
	
	@Override
	public void SetGoal(int[] diceids) {
		if(myGame.setGoal(diceids)) {
			for(int i = 0;i < myPlayers.length;i++)
				if(i!=lastTurnPlayer)
					myPlayers[i].UpdateUI(ActionProccess.proccessGoal(diceids));
			newTurn();
		}else
			myPlayers[lastTurnPlayer].SetGoal(ActionProccess.doGoal());
	}
	
	private void newTurn() {
		int next = nextPlayer();
		myPlayers[next].Start(ActionProccess.doTurn());
		for(int i = 0;i < myPlayers.length;i++)
			if(i != next)
				myPlayers[i].Wait(ActionProccess.doWait());
	}

	@Override
	public void Move(int dieid, GameMove move) {
		lastTurnPlayer = nextPlayer();
		for(int i = 0;i < myPlayers.length;i++)
			if(i!=lastTurnPlayer)
				myPlayers[i].UpdateUI(ActionProccess.proccessMove(dieid, move));
		int index = findDie(myGame.getMyResources(),myDice[whichDie(dieid)]);
		myGame.moveDie(index, move);
		newTurn();
	}
	
	private int findDie(Mat mat, IDDIE iddie) {
		for(int i = 0;i < mat.getMyMat().size();i++)
			if(mat.getMyMat().get(i).equals(iddie.getDie()))
					return i;					
		return -1;
	}

	private int whichDie(int dieid) {
		for(int i = 0;i < myDice.length;i++)
			if(myDice[i].getID() == dieid)
				return i;
		return -1;
	}

	@Override
	public void Solution(String solution, int playerid) {
		int index = findPlayer(playerid);
		Solutions[index] = solution;
		if(++response>=myPlayers.length)
			challangeComplete();
	}
	
	private int findPlayer(int playerid) {
		for(int i = 0;i < myPlayers.length;i++)
			if(playerid == myPlayers[i].PlayerID())
				return i;
		return -1;
	}
	
	private void challangeComplete() {
		String PlayerEq = Solutions[lastTurnPlayer];
		String ThirdPlayerEq = null;
		for(int i = 0;i < myPlayers.length;i++)
			if(i!=lastTurnPlayer&&Solutions[i].equals("NO"))
				ThirdPlayerEq = Solutions[i];
		myGame.challenge(myChallengeType, PlayerEq, ThirdPlayerEq);
		int[] scores = myGame.getScores();
		for(int i = 0;i < myPlayers.length;i++)
			myPlayers[i].End(ActionProccess.giveEnd(scores[i]));
	}
	
}
