/**
 * @author Saadman Haq s160081
 */

package application;

import java.io.File;
import java.io.FilenameFilter;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditRecipeController implements Initializable {
	public static String selectedName;
	
	

	// Initialising the listview
	@FXML 
	private ListView<String> listView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// Inserting values into listview
		File[] listOfFiles = finder("src/application/RecipeLibrary");

		String[] recipeLibraryList = new String[listOfFiles.length]; 

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && !"rezepte.dtd".equals(listOfFiles[i].getName()) ) {
				recipeLibraryList[i] =
						listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length() - 4);
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
				selectedName = newValue;
				
				
				Model.parsedFileName = newValue;
			}
		});
		
		//selects the first recipe in the listView as default
		listView.getSelectionModel().select(0);
	
	}
	
	//finds all files with .xml extension which solves the null element issue in listview
	public File[] finder( String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".xml"); }
        } );

    }
	
	// Events
	public void onHome(ActionEvent event) throws Exception {
		Parent home = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}
	
	public void onBackToEditor (ActionEvent event) throws Exception {
		Parent back = FXMLLoader.load(getClass().getResource("/application/EditorView.fxml"));
		Scene Back = new Scene(back);
		Model.primaryStage.setScene(Back);
		Model.primaryStage.show();
	}
	
	public void onEditRecipe (ActionEvent event) throws Exception {
		
		if(selectedName==null)
			return;
		EditorController.editMode = true;
		Parent addRecipe = FXMLLoader.load(getClass().getResource("/application/AddRecipeView.fxml"));
		Scene AddRecipe = new Scene(addRecipe);
		Model.primaryStage.setScene(AddRecipe);
		Model.primaryStage.show();
	}
	
	public void onDelete (ActionEvent event) throws Exception {
		final int selectedIdx = listView.getSelectionModel().getSelectedIndex();
		String itemToRemove = listView.getSelectionModel().getSelectedItem();
		if (selectedIdx != -1) {
          final int newSelectedIdx =
            (selectedIdx == listView.getItems().size() - 1)
               ? selectedIdx - 1
               : selectedIdx;
 
          listView.getItems().remove(selectedIdx);
          listView.getSelectionModel().select(newSelectedIdx);
          File file = new File("src/application/RecipeLibrary/" + itemToRemove+".xml");
          file.delete();
        }
        
	}	
}
