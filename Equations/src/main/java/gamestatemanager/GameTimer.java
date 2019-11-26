package gamestatemanager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.BooleanSupplier;

public class GameTimer {
    public static final int SECONDS_PER_TURN = 15;
    private static final int SECONDS_TO_MILLISECONDS = 1000;

    // Timers that fire every second and on expiration of the turn
    private Timer secondsTimer, expirationTimer;

    // The functions that will be called for these timers
    private BooleanSupplier everySecond, onExpiration;

    private boolean hasTimeExpired;

    public boolean isHasTimeExpired() {
        return hasTimeExpired;
    }

    /**
     * This method takes in a function that it will call every second. The function must return a boolean type
     * the return should indicate whether or not the timer should keep counting down
     * @param everySecond
     */
    public GameTimer setOnSecondsUpdate(BooleanSupplier everySecond) {
        this.everySecond = everySecond;
        return this;
    }
    /**
     * This method takes in a function that it will call on the expiration of the total time for the game timer.
     * @param onExpiration
     */
    public GameTimer setOnExpirationUpdate(BooleanSupplier onExpiration) {
        this.onExpiration = onExpiration;
        return this;
    }

    /**
     * This function cancels all of the timer objects, so none of the funcions passed in will be invoked following
     * this call
     */
    public void cancel() {

    }

    /**
     * Creates the timers and they begin counting down immediately
     */
    public void start() {
        secondsTimer = new Timer();
        expirationTimer = new Timer();

        // This is stupid janky...
        GameTimer itself = this;

        secondsTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!everySecond.getAsBoolean()) {
                    itself.cancel();
                }
            }
        },0, SECONDS_TO_MILLISECONDS);

        expirationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onExpiration.getAsBoolean();
            }
        },SECONDS_PER_TURN * SECONDS_TO_MILLISECONDS);
    }
}
