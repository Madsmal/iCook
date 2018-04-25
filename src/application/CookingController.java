package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;


public class CookingController implements Initializable {
	
	// Defining variables
	int[] taskSequence = new int[Model.recipe.tasks.task.size()];//the 7 length is temporary, when final no init is needed 
	int currentTask = 0;
	double timePassed = 0;
	ArrayList<CountdownTimer> countdownTimerArray = new ArrayList<CountdownTimer>();
	CountdownTimer countdownTimer2;
	
	// Defining FXML elements
	@FXML ProgressBar pb;
	@FXML Button previous;
	@FXML Button pause;
	@FXML Button next;
	@FXML Label task;
	@FXML Label countdownLabel;
	@FXML Label countdownLabel2;
	
	
	public void initialize(URL url, ResourceBundle rb) {
		
		//TEMPORARY START
		taskSequence[0] = 0;
		taskSequence[1] = 1;
		taskSequence[2] = 2;
		taskSequence[3] = 3;
		taskSequence[4] = 4;
		taskSequence[5] = 5;
		taskSequence[6] = 6;
		//TEMPORARY END
		
		
		//taskSequence = calculateTaskSequence();
	
		// Default FXML elements values
		task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
		
		//if first task is attentionRequired == true
		updateCountdownTimer2();
		
		// Continously updating timeline
		Timeline timeline = new Timeline( // Stop with timeline.stop();
			new KeyFrame(Duration.millis(100), 
				new EventHandler<ActionEvent>() {
		        	@Override public void handle(ActionEvent actionEvent) {
		        		
		        		// countdownTimer
		        		String text = "";
		        		for (int i = 0; i < countdownTimerArray.size() ; i++) {
		        			text = text + Integer.toString(countdownTimerArray.get(i).getTimeLeft()) + "\n";
		        		}
		        		countdownLabel.setText(text);
		        		
		        		// countdownTimer2
		        		if (currentTask == taskSequence.length || Model.recipe.tasks.getTask().get(currentTask).attentionRequired==false) {
		        			countdownLabel2.setText("");
		        		} else if (Model.recipe.tasks.getTask().get(currentTask).attentionRequired==true) {
		        			countdownLabel2.setText("Time remaining:\n"+Integer.toString(countdownTimer2.getTimeLeft()));
		        		}
		        	}
		        }
			)
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		
	}
	
	
	
	
	// Button events
	public void onHome(ActionEvent event) throws Exception {
		Parent home = FXMLLoader.load(getClass().getResource("/application/RecipesView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}
	
	public void onNext(ActionEvent event) throws Exception {
		if (currentTask != taskSequence.length) {
			currentTask++;
			timePassed = timePassed + Integer.parseInt(Model.recipe.getTasks().getTask().get(taskSequence[currentTask-1]).getTime());
			pb.setProgress(timePassed/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
			if (currentTask != taskSequence.length) {
				task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
			} else {
				task.setText("You have finished cooking "+Model.recipe.getTitle()+"\nEnjoy your meal!");
				pause.setVisible(false);
				next.setVisible(false);
			}
			
			//countdownTimer attentionRequired==false
			if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask-1]).getAttentionRequired() == false) {
				CountdownTimer countdownTimer = new CountdownTimer(Integer.parseInt(Model.recipe.getTasks().getTask().get(taskSequence[currentTask-1]).getTime()),Model.recipe.getTasks().getTask().get(taskSequence[currentTask-1]).getID());
				countdownTimerArray.add(countdownTimer);		
			}
			
			updateCountdownTimer2();
			
		}
		System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);//TEMP
		
	}
	
	public void onPrevious(ActionEvent event) throws Exception {
		if (currentTask != 0) {
			currentTask--;
			timePassed = timePassed - Integer.parseInt(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTime());
			pb.setProgress(timePassed/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
			task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
			if (currentTask == taskSequence.length-1) {
				pause.setVisible(true);
				next.setVisible(true);
			}
			
			updateCountdownTimer2();
		} 
		System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);//TEMP
	}
	
	public void onPause(ActionEvent event) throws Exception {
		//pause timers
	}
	
	
	
	
	
	
	
	// Methods
	public void updateCountdownTimer2() {
		if (currentTask == taskSequence.length) {
			countdownTimer2 = null;
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) {
			countdownTimer2 = null;
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true) {
			countdownTimer2 = new CountdownTimer(Integer.parseInt(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTime()),Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getID());	
		}
	}
	
	
	
}
