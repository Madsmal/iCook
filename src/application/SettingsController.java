package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

public class SettingsController {
	
	// defining FXML elements
	@FXML MenuItem clock1 = new MenuItem("hh:mm");
	@FXML MenuItem clock2 = new MenuItem("hh:mm:ss");
	@FXML MenuItem clock3 = new MenuItem("hh:mm:ss.sss");
	@FXML MenuButton clock = new MenuButton("Options", null, clock1, clock2, clock3);
	
	public void initialize(URL url, ResourceBundle rb) {
		//serving amount menu
		//TODO change to events
		clock1.setOnAction(event -> {
			Model.settings.put(Model.section, "clock", "hh:mm");

			System.out.println(Model.settings.get(Model.section, "clock"));
		});
		clock2.setOnAction(event -> {
			Model.settings.put(Model.section, "clock", "hh:mm:ss");

			System.out.println(Model.settings.get(Model.section, "clock"));
		});
		clock3.setOnAction(event -> {
			Model.settings.put(Model.section, "clock", "hh:mm:ss.sss");
		});
	}	
			
	// events
	public void onHome(ActionEvent event) throws Exception {
		Model.saveIni();
		
		Parent home = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
		}
	
	// methods
	
	
}
