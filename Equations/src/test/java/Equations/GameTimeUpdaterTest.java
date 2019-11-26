package Equations;

import fundementalgamemechanics.Mat;
import fundementalgamemechanics.*;
import gamestatemanager.GameTimeUpdater;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GameTimeUpdaterTest {
	private GameTimeUpdater gameTimer;

	@Test
	/**
	 * Not so much a test but just shows how to use the code
	 */
	public void basicUsage() {
		// Arrange
		gameTimer = new GameTimeUpdater();

		//Act
		gameTimer.setOnSecondsUpdate(() -> {
			System.out.println("This is printed every second");
			// If false is returned the timer cancels itself
			return true;
		}).setOnExpirationUpdate(() -> {
			System.out.println("This is printed when the timer expires");
			return true;
		}).start();
	}
}
