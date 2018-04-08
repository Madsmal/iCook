package application;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;

public class RecipesController implements Initializable {
	
	// Defining fxml elements
	@FXML Label title;
	@FXML Label totalTime;
	@FXML Label worktime;
	@FXML Label startdate;
	@FXML Label changedate;
	@FXML Label ingredients;
	@FXML Label source;
	
	// Initialising the listview
	@FXML 
	private ListView<String> listView;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Inserting values into listview
		Parser.recipeLibraryList = Parser.parseRecipeLibrary();
		ObservableList<String> data = FXCollections.observableArrayList(Parser.recipeLibraryList);
		listView.setItems(data);
		
		// Default label values
		title.setText("Please select a recipe");
		totalTime.setText("Total Time: N/A");
		worktime.setText("Work time: N/A");
		startdate.setText("Start date: N/A");
		changedate.setText("Change date: N/A");
		ingredients.setText("Ingredients: N/A");
		source.setText("Source: N/A");
		
		// Action on listView selection
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	
		        System.out.println("Selected item: " + newValue); // test - remove when finished
		        
		        /* Todo here:
		         * update a selectedRecipe string variable (perhaps)
		         * parse the selected recipe
		         * update labels
		         * 
		         * update image
		         */
		        
		        // Updated label values
		        /*
		        title.setText();
				totalTime.setText("Total Time: " +);
				worktime.setText("Work time: " +);
				startdate.setText("Start date: " + );
				changedate.setText("Change date: " +);
				ingredients.setText("Ingredients: " +);
				source.setText("Source: " + );
				*/
		    }
		});
	}
	
	// Events
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
