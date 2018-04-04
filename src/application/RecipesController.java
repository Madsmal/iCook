package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

public class RecipesController implements Initializable {
	
	
	//initialising the listview
	@FXML 
	private ListView<String> listView;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> data = FXCollections.observableArrayList(Parser.recipeLibraryList);
		listView.setItems(data);
	}
	
	//events
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
