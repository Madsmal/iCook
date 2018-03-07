package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class menuController {
	
	Stage primaryStage;
	
public void onRecipes(ActionEvent event) throws Exception {
	//Parent recipesPath = FXMLLoader.load(getClass().getResource("/application/recipesView.fxml"));
	//Scene recipesScene = new Scene(recipesPath);
	//primaryStage.getScene().setRoot(recipesScene);
	
	//RecipesController recipesController = new RecipesController(model);
	//recipesScene.show();
	
	
}


public void onEditor(ActionEvent event) {
	
}
	
	
public void onExit(ActionEvent event) {
	System.out.println("Exit");
	Platform.exit();
}
	
}
