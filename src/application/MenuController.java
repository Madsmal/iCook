/**
 * @author Erik Nikolajsen s144382
 */


package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class MenuController {
	
public void onRecipes(ActionEvent event) throws Exception {
	Parent recipesPath = FXMLLoader.load(getClass().getResource("/application/RecipesView.fxml"));
	Scene recipesScene = new Scene(recipesPath);
	Model.primaryStage.setScene(recipesScene);		
	Model.primaryStage.show();
}

public void onEditor(ActionEvent event) throws Exception {
	Parent editorPath = FXMLLoader.load(getClass().getResource("/application/EditorView.fxml"));
	Scene editorScene = new Scene(editorPath);
	Model.primaryStage.setScene(editorScene);
	Model.primaryStage.show();
}
	
	
public void onExit(ActionEvent event) {
	Platform.exit();
}

public void onSettings(MouseEvent event) throws IOException {
	Parent settingsPath = FXMLLoader.load(getClass().getResource("/application/SettingsView.fxml"));
	Scene settingScene = new Scene(settingsPath);
	Model.primaryStage.setScene(settingScene);		
	Model.primaryStage.show();
}
	
}
