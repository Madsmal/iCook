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


public class CookingController implements Initializable {
	
	// Defining variables
	int[] taskSequence = new int[5];//the 5 length is temporary, when final no init is needed 
	int currentTask = -1;
	
	
	
	// Defining FXML elements
	@FXML Button previous;
	@FXML Label currentTime;
	@FXML Label task;
	
	public void initialize(URL url, ResourceBundle rb) {
		
		
		//taskSequence = calculateTaskSequence();
	
		// Default FXML elements values
		//task.setText("Get all your ingredients ready:\n"); //TODO: insert ingredients
		
		
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
		}
		System.out.println("currentTask = "+currentTask);
		//task.setText(Model.recipe.getIngredients().getIngredient().get(i).getTaskString());
	}
	
	public void onPrevious(ActionEvent event) throws Exception {
		if (currentTask != -1) {
			currentTask--;
		}
		System.out.println("currentTask = "+currentTask);
		//if currentTask == -1 then show ingredients else:
		//task.setText(Model.recipe.getIngredients().getIngredient().get(i).getTaskString());
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
