package application;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecipesController implements Initializable {

	// Defining FXML elements
	@FXML Label title;
	@FXML Label totalTime;
	@FXML Label worktime;
	@FXML Label startdate;
	@FXML Label changedate;
	@FXML Label ingredients;
	@FXML Label source;
	@FXML ImageView RecipeImageView;
	/*
	@FXML MenuItem serving1 = new MenuItem("Option 1");
	@FXML MenuItem serving2 = new MenuItem("Option 2");
	@FXML MenuButton servingAmount = new MenuButton("Options", null, serving1, serving2);
	 */


	// Initialising the listview
	@FXML 
	private ListView<String> listView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Inserting values into listview
		File folder = new File("src/application/RecipeLibrary");
		File[] listOfFiles = folder.listFiles();

		String[] recipeLibraryList = new String[listOfFiles.length-1]; 

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && !"rezepte.dtd".equals(listOfFiles[i].getName()) ) {
				recipeLibraryList[i] = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length() - 4);
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Error: RecipeLibraryList folder contains a folder.");
				Platform.exit();
			}
		}

		ObservableList<String> data = FXCollections.observableArrayList(recipeLibraryList);
		listView.setItems(data);

		// Default FXML elements values
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

				//parse selected recipe
				try {
					Model.parseXMLFile("src/application/RecipeLibrary/"+newValue+".xml");
				} catch (Exception e) {
					e.printStackTrace();
				}

				//update labels
				String ingredientsList = "Ingredients:\n";

				for (int i = 0 ; i < Model.recipe.getIngredients().getIngredient().size() ; i++) {
					if (!"".equals(Model.recipe.getIngredients().getIngredient().get(i).getQuantity())) {
						ingredientsList = ingredientsList+Model.recipe.getIngredients().getIngredient().get(i).getQuantity()+" ";
					}
					if (!"".equals(Model.recipe.getIngredients().getIngredient().get(i).getUnit())) {
						ingredientsList = ingredientsList+Model.recipe.getIngredients().getIngredient().get(i).getUnit()+" ";
					}
					ingredientsList = ingredientsList+Model.recipe.getIngredients().getIngredient().get(i).getIname()+"    \n";
				}

				ingredients.setText(ingredientsList);

				title.setText(Model.recipe.getTitle());
				totalTime.setText("Total Time: " + Model.recipe.getDuration().getTotaltime());
				worktime.setText("Work time: " + Model.recipe.getDuration().getWorktime());
				startdate.setText("Start date: " + Model.recipe.getStartdate());
				changedate.setText("Change date: " + Model.recipe.getChangedate());
				source.setText("Source: " + Model.recipe.getSource());

				// Update recipe image

				/* Recipe images supported formats are PNG, JPEG, GIF, BMP, and JPG
				 * 
				 * The image associated with a recipe must have the same name as the recipe,
				 * and must be placed in "src\\application\\Images\\"
				 */

				File filePNG = new File("src/application/RecipeImages/" + newValue + ".png");
				File fileJPEG = new File("src/application/RecipeImages/" + newValue + ".jpeg");
				File fileGIF = new File("src/application/RecipeImages/" + newValue + ".gif");
				File fileBMP = new File("src/application/RecipeImages/" + newValue + ".bmp");
				File fileJPG = new File("src/application/RecipeImages/" + newValue + ".jpg");

				Image image = null;
				if (filePNG.exists()) {
					image = new Image(filePNG.toURI().toString());
				} else if (fileJPEG.exists()) {
					image = new Image(fileJPEG.toURI().toString());
				} else if (fileGIF.exists()) {
					image = new Image(fileGIF.toURI().toString());
				} else if (fileBMP.exists()) {
					image = new Image(fileBMP.toURI().toString());
				} else if (fileJPG.exists()) {
					image = new Image(fileJPG.toURI().toString());
				} else {
					System.out.println("ERROR: The recipe \""+newValue+"\" is missing an associated image");
				}

				RecipeImageView.setImage(image);
			}
		});
		/*
		serving1.setOnAction(event -> {
			try {
				Model.parseXMLFile("src\\application\\RecipeLibrary\\"+"Lorem ipsum"+".xml");
			} catch (Exception e) {
				e.printStackTrace();
			}

		    System.out.println("Option 1 selected via Lambda");
		    Model.recipe.getIngredients().getIngredient().get(0).setQuantity(Double.toString(Double.parseDouble(Model.recipe.getIngredients().getIngredient().get(0).getQuantity())*2));

		  //update labels
	        String ingredientsList = "Ingredients:\n";

	        for (int i = 0 ; i < Model.recipe.getIngredients().getIngredient().size() ; i++) {
	        	if (!"".equals(Model.recipe.getIngredients().getIngredient().get(i).getQuantity())) {
	        		ingredientsList = ingredientsList+Model.recipe.getIngredients().getIngredient().get(i).getQuantity()+" ";
	        	}
	        	if (!"".equals(Model.recipe.getIngredients().getIngredient().get(i).getUnit())) {
	        		ingredientsList = ingredientsList+Model.recipe.getIngredients().getIngredient().get(i).getUnit()+" ";
	        	}
	        	ingredientsList = ingredientsList+Model.recipe.getIngredients().getIngredient().get(i).getIname()+"    \n";
	        }

	        ingredients.setText(ingredientsList);

	        title.setText(Model.recipe.getTitle());
	        totalTime.setText("Total Time: " + Model.recipe.getDuration().getTotaltime());
			worktime.setText("Work time: " + Model.recipe.getDuration().getWorktime());
			startdate.setText("Start date: " + Model.recipe.getStartdate());
			changedate.setText("Change date: " + Model.recipe.getChangedate());
			source.setText("Source: " + Model.recipe.getSource());

		});
		serving2.setOnAction(event -> {
		    System.out.println("Option 2 selected via Lambda");
		});
		 */

	}

	// Events
	public void onHome(ActionEvent event) throws Exception {
		Parent home = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}

	public void onStart(ActionEvent event) throws Exception {

		// Checks if a listview element is selected. 
		
		if (listView.getSelectionModel().getSelectedItem() != null) {
			Parent start = FXMLLoader.load(getClass().getResource("/application/CookingView.fxml"));
			Scene Start = new Scene(start);
			Model.primaryStage.setScene(Start);		
			Model.primaryStage.show();
			
		} 
	}



}
