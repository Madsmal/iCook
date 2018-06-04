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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.SwipeEvent;
import javafx.util.Callback;

public class RecipesController implements Initializable {

	// Defining FXML elements
	@FXML Label title;
	@FXML Label totalTime;
	@FXML Label worktime;
	@FXML Label startdate;
	@FXML Label changedate;
	@FXML Label ingredients;
	@FXML Label source;
	@FXML Label rating;
	@FXML Label calories;
	@FXML ImageView RecipeImageView;
	
	@FXML MenuItem serving1 = new MenuItem("Option 1");
	@FXML MenuItem serving2 = new MenuItem("Option 2");
	@FXML MenuItem serving3 = new MenuItem("Option 3");
	@FXML MenuItem serving4 = new MenuItem("Option 4");
	@FXML MenuButton servingAmount = new MenuButton("Options", null, serving1, serving2, serving3, serving4);
	


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
		
		

		// Action on listView selection
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				Model.parsedFileName = newValue;
				//parse selected recipe
				parseXML();

				//update labels
				updateLabels();

				/* Update recipe image
				 * Recipe images supported formats are PNG, JPEG, GIF, BMP, and JPG
				 * The image associated with a recipe must have the same name as the recipe,
				 * and must be placed in "src/application/Images/"
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
		
		//selects the first recipe in the listView as default
		listView.getSelectionModel().select(0);
		
		//serving amount menu
		serving1.setOnAction(event -> {
			parseXML();
			ingredientsQuantityMultiplier(1);
		    updateLabels();
		    servingAmount.setText("Servings: 1");
		});
		serving2.setOnAction(event -> {
			parseXML();
			ingredientsQuantityMultiplier(2);
		    updateLabels();
		    servingAmount.setText("Servings: 2");
		});
		serving3.setOnAction(event -> {
			parseXML();
			ingredientsQuantityMultiplier(3);
		    updateLabels();
		    servingAmount.setText("Servings: 3");
		});
		serving4.setOnAction(event -> {
			parseXML();
			ingredientsQuantityMultiplier(4);
		    updateLabels();
		    servingAmount.setText("Servings: 4");
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

		// Checks if a listview element is selected. 
		
		if (listView.getSelectionModel().getSelectedItem() != null) {
			Parent start = FXMLLoader.load(getClass().getResource("/application/CookingView.fxml"));
			Scene Start = new Scene(start);
			Model.primaryStage.setScene(Start);		
			Model.primaryStage.show();
			
		} 
	}
	
	public void onSwipe(SwipeEvent event) throws Exception {
		System.out.println("test");
	}

	// methods
	private void parseXML() {
		try {
			Model.parseXMLFile("src/application/RecipeLibrary/"+Model.parsedFileName+".xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateLabels() {
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
		
		Model timeConverterTT = new Model();
		
		timeConverterTT.convertMinToHHMM(Model.recipe.getDuration().getTotaltime());
		int tthours = timeConverterTT.getHours();
		int ttminutes = timeConverterTT.getMinutes();
		
		Model timeConverterWT = new Model();
		timeConverterWT.convertMinToHHMM(Model.recipe.getDuration().getWorktime());

		int wthours = timeConverterWT.getHours();
		int wtminutes = timeConverterWT.getMinutes();
		
		ingredients.setText(ingredientsList);

		title.setText(Model.recipe.getTitle());
		//totalTime.setText("Total Time: " + Model.recipe.getDuration().getTotaltime());
		totalTime.setText("Total time: " + tthours + "h " + ttminutes + "m");
		//worktime.setText("Work time: " + Model.recipe.getDuration().getWorktime());
		worktime.setText("Work time: " + wthours + "h " + wtminutes + "m");
		startdate.setText("Start date: " + Model.recipe.getStartdate());
		changedate.setText("Change date: " + Model.recipe.getChangedate());
		source.setText("Source: " + Model.recipe.getSource());
		calories.setText("Calories: " + Model.recipe.getCalories() + " kcal");
		
		
		// rating
		String ratingString = "";
		for (int i = 1 ; i < 6 ; i++) {
			if (Model.recipe.getRating() >= i) {
				ratingString = ratingString + "\u26ab"; //black star: 2605
			} else {
				ratingString = ratingString + "\u26aa"; //white star: 2606
			}
		}
		rating.setText("Rating: " + ratingString);
	}
	
	private void ingredientsQuantityMultiplier(int multiplier) {
		for (int i = 0 ; i < Model.recipe.getIngredients().getIngredient().size() ; i++) {
			if (Model.recipe.getIngredients().getIngredient().get(i).getQuantity().length() > 0) {
				String string1 = Double.toString(Double.parseDouble(Model.recipe.getIngredients().getIngredient().get(i).getQuantity())*multiplier);
				if (string1.endsWith(".0")) {
					string1 = string1.substring(0, string1.length() - 2);
				}
				Model.recipe.getIngredients().getIngredient().get(i).setQuantity(string1);
			}
		}
		String string2 = Double.toString(Double.parseDouble(Model.recipe.getCalories())*multiplier);
		Model.recipe.setCalories(string2);
	}
	
	//ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
	
	/*private calculateFat(float fat) {
		
	}*/
	
}
