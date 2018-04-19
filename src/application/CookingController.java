package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class CookingController implements Initializable {
	
	// Defining variables
	int[] taskSequence = new int[7];//the 7 length is temporary, when final no init is needed 
	int currentTask = 0;
	double timePassed = 0;
	
	
	
	// Defining FXML elements
	@FXML ProgressBar pb;
	@FXML Button previous;
	@FXML Button pause;
	@FXML Button next;
	@FXML Label currentTime;
	@FXML Label task;
	
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
