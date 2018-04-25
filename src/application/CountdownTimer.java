package application;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
	int totalTime; //in minutes
	int timeLeft;
	int ID;
	int timePassed = 0;
	Timer timer;
	
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
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  public void run() {
			    timeLeft--;
			    timePassed = timeLeft - totalTime;
			    if (timeLeft == 0) {
			    	timer.cancel();	
			    	//TODO pop-up
			    }
			  }
			}, 1000, 1000);
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
	
}
