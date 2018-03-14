package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RecipesController {

public void onHome(ActionEvent event) throws Exception {
	
	Parent home = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
	Scene Home = new Scene(home);
	Model.primaryStage.setScene(Home);		
	Model.primaryStage.show();
	}

public void onStart(ActionEvent event) throws Exception {
	
	Parent start = FXMLLoader.load(getClass().getResource("/application/CookingView.fxml"));
	Scene Start = new Scene(start);
	Model.primaryStage.setScene(Start);		
	Model.primaryStage.show();
	}
	
}
