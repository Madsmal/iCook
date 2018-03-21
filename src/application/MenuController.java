package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {
	
	
public void onRecipes(ActionEvent event) throws Exception {
	Parser.recipeLibraryList = Parser.parseRecipeLibrary();
	
	Parent recipesPath = FXMLLoader.load(getClass().getResource("/application/RecipesView.fxml"));
	Scene recipesScene = new Scene(recipesPath);
	Model.primaryStage.setScene(recipesScene);		
	Model.primaryStage.show();
}


public void onEditor(ActionEvent event) {
	
}
	
	
public void onExit(ActionEvent event) {
	Platform.exit();
}

public void onSettings(ActionEvent event) throws IOException {
	Parent settingsPath = FXMLLoader.load(getClass().getResource("/application/SettingsView.fxml"));
	Scene settingScene = new Scene(settingsPath);
	Model.primaryStage.setScene(settingScene);		
	Model.primaryStage.show();
}
	
}
