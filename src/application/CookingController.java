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
	int[] taskSequence = new int[5];//the 5 length is temporary, when final no init is needed 
	int currentTask = 0;
	int timePassed = 0;
	
	
	
	// Defining FXML elements
	@FXML ProgressBar pb;
	@FXML Button previous;
	@FXML Label currentTime;
	@FXML Label task;
	
	public void initialize(URL url, ResourceBundle rb) {
		
		//TEMPORARY START
		taskSequence[0] = 1;
		taskSequence[1] = 2;
		taskSequence[2] = 3;
		taskSequence[3] = 4;
		taskSequence[4] = 5;
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
		if (currentTask != taskSequence.length-1) {
			currentTask++;
			//timePassed = timePassed +
			//pb.setProgress(7/(currentTask+2)); todo: update based on tasktime and totaltime
		}
		System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);
		task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
	}
	
	public void onPrevious(ActionEvent event) throws Exception {
		if (currentTask != 0) {
			currentTask--;
			//timePassed = timePassed - task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask-1]).getTime());
		} 
		
		
		task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
		
		System.out.println("currentTask = "+currentTask+" ; timePassed = "+timePassed);
		
		//pb.setProgress(7/(currentTask+2)); todo: update based on tasktime and totaltime
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
