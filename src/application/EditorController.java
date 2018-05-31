package application;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditorController implements Initializable {
	public TextField userText;
	public TextField passText;
	public Button logText;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void handle(ActionEvent event) {
		String user = userText.getText();
		String pass = passText.getText();
		
	}
	
	public void onAddRecipe (Observable o, Object arg) {
		Platform.runLater(new Runnable() {
			public void run() {
				new EditorAddRecipeWizard().start(new Stage());
			}
		});
		
//		Parent wizard = FXMLLoader.load(getClass().getResource("/application/Wizard.fxml"));
//		Scene Wizard = new Scene(wizard);
//		Model.primaryStage.setScene(Wizard);
//		Model.primaryStage.show();
	}
	
	public void onExit(ActionEvent event) throws Exception {
		Parent home = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}

	
}
