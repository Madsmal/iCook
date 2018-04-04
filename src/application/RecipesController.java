package application;


import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.events.MouseEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	
	// Initialising the listview
	@FXML 
	private ListView<String> listView;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> data = FXCollections.observableArrayList(Parser.recipeLibraryList);
		listView.setItems(data);
		
		// Action on listView selection
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        System.out.println("Selected item: " + newValue);
		        /* Todo here:
		         * update a selectedRecipe string variable
		         * parse the selected recipe
		         * update scene or labels
		         */
		    }
		});
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
