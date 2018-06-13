package application;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
	private int totalTime;
	private int timeLeft;
	private int ID;
	private int timePassed = 0;
	private Timer timer;
	
	public CountdownTimer(int totalTime, int ID) {
		this.totalTime = totalTime;
		this.ID = ID;
		startCountdownTimer();
	}
	
	public void startCountdownTimer() { 
		timeLeft = totalTime;
		continueCountdownTimer();
	}
	
	public void pauseCountdownTimer() {
		timer.cancel();
	}
	
	public void continueCountdownTimer() {
		if (timeLeft != 0) {
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					timeLeft--;
					timePassed = totalTime - timeLeft;
					if (timeLeft == 0) {
						timer.cancel();	
				    }
				}
			}, 1000, 1000);
		}
	}
	
	public void resetCountdownTimer() {
		timer.cancel();
		startCountdownTimer();
	}
	
	//get & set methods
	
	public int getTimeLeft() {
		return timeLeft;
	}

	public int getID() {
		return ID;
	}

	public int getTimePassed() {
		return timePassed;
	}
	
	public int getTotalTime() {
		return totalTime;
	}
}
