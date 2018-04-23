package application;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
	int totalTime; //in minutes
	int timeLeft;
	Timer timer;
	
	public CountdownTimer(int totalTime) {
		this.totalTime = totalTime;
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
		//CookingController.updateCountdownTimer(Integer.toString(timeLeft));
		timer.scheduleAtFixedRate(new TimerTask() {
			  public void run() {
			    timeLeft--;
			    //CookingController.updateCountdownTimer(Integer.toString(timeLeft));
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
	
}
