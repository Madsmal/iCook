package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Model.primaryStage = primaryStage;
		Model.parseIni();
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("iCook");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	// Raspberry pi 3 screen resolution 800x480	

	// frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); til at lukke vindue p� raspberry.
		
	public static void main(String[] args) throws IOException, Exception {
		launch(args);
	}
}
