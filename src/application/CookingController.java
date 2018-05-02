package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Recipe.Tasks;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


public class CookingController implements Initializable {
	
	// Defining variables
	int[] taskSequence = new int[Model.recipe.tasks.task.size()];
	int currentTask = 0;
	double timePassed = 0; //does not include intermediate-task time
	ArrayList<CountdownTimer> countdownTimerArray = new ArrayList<CountdownTimer>();
	CountdownTimer countdownTimer2;
	Timeline timeline;
	Timeline timeline2;
	Image starEmpty = new Image(new File("src/application/Images/starEmpty2.png").toURI().toString());
	Image starFull = new Image(new File("src/application/Images/starFull2.png").toURI().toString());
	
	
	// Defining FXML elements
	@FXML ProgressBar pb;
	@FXML Button previous;
	@FXML Button pause;
	@FXML Button next;
	@FXML Label task;
	@FXML Label countdownLabel;
	@FXML Label countdownLabel2;
	@FXML Label clock;
	
	@FXML ImageView star1;
	@FXML ImageView star2;
	@FXML ImageView star3;
	@FXML ImageView star4;
	@FXML ImageView star5;
	
	
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
		
		
		// taskSequence = calculateTaskSequence();
	
		// Default FXML elements values
		task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
		
		//if first task is attentionRequired == true
		updateCountdownTimer2();
		updateButtonVisibility();
		
		// Continuously updating timeline
		timeline = new Timeline(
			new KeyFrame(Duration.millis(100), 
				new EventHandler<ActionEvent>() {
		        	@Override public void handle(ActionEvent actionEvent) {
		        		
		        		// CountdownTimer
		        		// Alert if countdownTimer == 0 TODO
		        		
		        		
		        		// Remove from array if countdownTimer == 0
		        		for (int i = 0 ; i < countdownTimerArray.size() ; i++) {
			        		if (countdownTimerArray.get(i).getTimeLeft() == 0) {
		        				countdownTimerArray.remove(i);
		        				i--;
		        			}
		        		}
		        		
		        		
		        		// Create label
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
		        		
		        		// Intermediate-task progress bar update
		        		if (currentTask != taskSequence.length && Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true ) {
		        			pb.setProgress((timePassed + countdownTimer2.getTimePassed())/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
		        		}
		        		
		        		// Update 'next' setDisable() value on second last page
		        		// TODO Can be moved to alert 'OK' button when it is written to lower resources 
		        		
		        		if (currentTask == taskSequence.length - 1 && countdownTimerArray.isEmpty()) {
		        			next.setDisable(false);
		        		}
		        		
		        		// Rating system TODO save recipe to file when exiting cookingView to save rating
		        		if (currentTask == taskSequence.length) {
		        			if (Model.recipe.rating >= 2) {
		        				star2.setImage(starFull);
		        			} else {
		        				star2.setImage(starEmpty);
		        			}
		        			if (Model.recipe.rating >= 3) {
		        				star3.setImage(starFull);
		        			} else {
		        				star3.setImage(starEmpty);
		        			}
		        			if (Model.recipe.rating >= 4) {
		        				star4.setImage(starFull);
		        			} else {
		        				star4.setImage(starEmpty);
		        			}
		        			if (Model.recipe.rating == 5) {
		        				star5.setImage(starFull);
		        			} else {
		        				star5.setImage(starEmpty);
		        			}
		        		} 
		        	}
		        }
			)
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		// clock timeline
		int updateFrequency;
		if (Model.settings.get(Model.section, "clock").equals("hh:mm:ss.sss")) {
			updateFrequency = 1;
		} else {
			updateFrequency = 1000;
		}
		timeline2 = new Timeline(
				new KeyFrame(Duration.millis(updateFrequency), 
					new EventHandler<ActionEvent>() {
			        	@Override public void handle(ActionEvent actionEvent) {
			        		// clock update
			        		clock.setText(Model.getTime());
			        	}
			        }
				)
			);
			timeline2.setCycleCount(Animation.INDEFINITE);
			timeline2.play();
	}
	
	
	
	
	/* public int[] calculateTaskSequence() {
		
		int[] sequence;
		
		for(int i = 0; i <= Model.recipe.tasks.task.size(); i++) {
			if() {
				
			}
		}
		
		return null;
	} */




	// Button events
	public void onHome(ActionEvent event) throws Exception {
		timeline.stop();
		timeline2.stop();
		
		Parent home = FXMLLoader.load(getClass().getResource("/application/RecipesView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}
	
	public void onNext(ActionEvent event) throws Exception {
		if (currentTask != taskSequence.length) {
			updateCountdownTimer();
			currentTask++;
			pause.setText("Pause");
			updateCountdownTimer2();
			updateProgressBar();
			updateButtonVisibility();
			if (currentTask != taskSequence.length) {
				task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
			} else {
				task.setText("You have finished cooking "+Model.recipe.getTitle()+"\nEnjoy your meal!");
			}
		}
		// System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);//TEMP
		
	}
	
	public void onPrevious(ActionEvent event) throws Exception {
		if (currentTask != 0) {
			currentTask--;
			pause.setText("Pause");
			updateCountdownTimer2();
			updateProgressBar();
			updateButtonVisibility();
			task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
			
		} 
		// System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);//TEMP
	}
	
	public void onPause(ActionEvent event) throws Exception {
		if (pause.getText().equals("Pause")) {
			countdownTimer2.pauseCountdownTimer();
			pause.setText("Resume");
		} else if (pause.getText().equals("Resume")) {
			countdownTimer2.continueCountdownTimer();
			pause.setText("Pause");
		}
	}
	
	
	
	
	
	
	
	// Methods
	private void updateCountdownTimer() { //TODO make it a button rather than when clicking 'next'
		if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) {
			CountdownTimer countdownTimer = new CountdownTimer(Integer.parseInt(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTime()),Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getID());
			countdownTimerArray.add(countdownTimer);		
		}
	}
	
	private void updateCountdownTimer2() {
		if (currentTask == taskSequence.length) {
			countdownTimer2 = null;
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) {
			countdownTimer2 = null;
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true) {
			countdownTimer2 = new CountdownTimer(Integer.parseInt(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTime()),Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getID());	
		}
	}
	
	private void updateProgressBar() { //TODO attentionrequired false kun t�lle med som det sidste
		timePassed = 0;
		for (int i = 0 ; i < currentTask ; i++) {
			timePassed = timePassed + Double.parseDouble(Model.recipe.tasks.task.get(i).getTime());
		}
		pb.setProgress(timePassed/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
	}
	
	private void updateButtonVisibility() {
		// previous
		if (currentTask == 0) {
			previous.setDisable(true);
		} else {
			previous.setDisable(false);
		}
		// pause
		if (currentTask == taskSequence.length) {
			pause.setDisable(true);
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true) {
			pause.setDisable(false);
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) {
			pause.setDisable(true);
		}
		// next
		if (currentTask == taskSequence.length) {
			next.setDisable(true);
		} else if (currentTask == taskSequence.length - 1 && (!countdownTimerArray.isEmpty() || Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) && Model.settings.get("dev", "nextAlwaysClickable").equals("false")) {
			next.setDisable(true);
		} else {
			next.setDisable(false);
		}
		// rating stars
		if (currentTask == taskSequence.length) {
			star1.setVisible(true);
			star2.setVisible(true);
			star3.setVisible(true);
			star4.setVisible(true);
			star5.setVisible(true);
			star1.setImage(starFull);
		} else {
			star1.setVisible(false);
			star2.setVisible(false);
			star3.setVisible(false);
			star4.setVisible(false);
			star5.setVisible(false);
		}
	}
	
	
	// Rating system
	public void onStar1(MouseEvent event) throws Exception {
		Model.recipe.rating = 1;
	}
	public void onStar2(MouseEvent event) throws Exception {
		Model.recipe.rating = 2;
	}
	public void onStar3(MouseEvent event) throws Exception {
		Model.recipe.rating = 3;
	}
	public void onStar4(MouseEvent event) throws Exception {
		Model.recipe.rating = 4;
	}
	public void onStar5(MouseEvent event) throws Exception {
		Model.recipe.rating = 5;
	}
	
	
	
	
}