package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
import javafx.util.Duration;


public class CookingController implements Initializable {

	// Defining variables
	int[] taskSequence = new int[Model.recipe.tasks.task.size()];//the 7 length is temporary, when final no init is needed 
	int currentTask = 0;
	double timePassed = 0;
	ArrayList<CountdownTimer> countdownTimerArray = new ArrayList<CountdownTimer>();
	

	// Defining FXML elements
	@FXML ProgressBar pb;
	@FXML Button previous;
	@FXML Button pause;
	@FXML Button next;
	@FXML Label task;
	@FXML Label countdownLabel;


	public void initialize(URL url, ResourceBundle rb) {

		//TEMPORARY START
		//		taskSequence[0] = 0;
		//		taskSequence[1] = 1;
		//		taskSequence[2] = 2;
		//		taskSequence[3] = 3;
		//		taskSequence[4] = 4;
		//		taskSequence[5] = 5;
		//		taskSequence[6] = 6;
		//TEMPORARY END


		//System.out.println(Model.recipe.tasks.task.size()); Size should return 7 in our case. 

		//taskSequence = calculateTaskSequence();

		// Default FXML elements values

		task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());

		



		CountdownTimer asd = new CountdownTimer(30); //TEMP test

		Timeline timeline = new Timeline( // Stop with timeline.stop();
				new KeyFrame(Duration.millis(1000), 
						new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {
						/*
		        		String text = "";
		        		for (int i = 0; i < countdownTimerArray.size() ; i++) {
		        			text = text + Integer.toString(countdownTimerArray.get(i).getTimeLeft()) + "\n";
		        		}
		        		countdownLabel.setText(text);
						 */
						countdownLabel.setText(Integer.toString(asd.getTimeLeft()));
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
		for(int i = 0; i <= taskSequence.length; i++) {
			if (currentTask != taskSequence.length) {
				timePassed = timePassed + Integer.parseInt(Model.recipe.getTasks().getTask().get(currentTask).getTime());
				// System.out.println(""+Model.recipe.getTasks().getTask().get(currentTask).getTime());
				currentTask++;
				pb.setProgress(timePassed/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
				// System.out.println("" + currentTask);
				if (currentTask == taskSequence.length) {
					System.out.println("Here");
					timePassed = timePassed + Integer.parseInt(Model.recipe.getTasks().getTask().get(currentTask-1).getTime());
					/**
					 * TO DO: This returns timePassed = 10, since currentTask-1 but it should return timePassed = 5.
					 * If currentTask-1 is changed to currentTask it crashes. 
					 */
					pb.setProgress(timePassed/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
					task.setText("You have finished cooking "+Model.recipe.getTitle()+"\nEnjoy your meal!");
					pause.setVisible(false);
					next.setVisible(false);
					break;
				}
				task.setText(Model.recipe.getTasks().getTask().get(currentTask).getTaskString());
				break;
			}
			//				task.setText("You have finished cooking "+Model.recipe.getTitle()+"\nEnjoy your meal!");
			//				pause.setVisible(false);
			//				next.setVisible(false);

			//countdownTimer
			/*
			if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask-1]).getAttentionRequired() == false) {
				CountdownTimer countdownTimer = new CountdownTimer(Integer.parseInt(Model.recipe.getTasks().getTask().get(taskSequence[currentTask-1]).getTime()));
				countdownTimerArray.add(countdownTimer);
			}
			 */
		}
		System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);//TEMP

	}

	public void onPrevious(ActionEvent event) throws Exception {
		if (currentTask != 0) {
			currentTask--;
			timePassed = timePassed - Integer.parseInt(Model.recipe.getTasks().getTask().get(currentTask).getTime());
			pb.setProgress(timePassed/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
			task.setText(Model.recipe.getTasks().getTask().get(currentTask).getTaskString());
			if (currentTask == taskSequence.length-1) {
				pause.setVisible(true);
				next.setVisible(true);
			}
		} 
		System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);//TEMP
	}

	public void onPause(ActionEvent event) throws Exception {
		//pause timers
	}



	/*
	// Methods
	public static int[] calculateTaskSequence() {
		int[] taskSequence = new int[Model.recipe.getTasks().getTask().size()];

		return taskSequence;
	}
	 */



}
