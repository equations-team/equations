package localhostgame;

//Timers contain a length, and can be flipped to start.

public class GameTimer extends Thread{
	
	private int myLength; //In half minutes
	
	GameTimer(int length) {
		myLength = length;
	}
	
	public boolean doFlip() {
		try {
			sleep(30000*myLength);
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}
}
