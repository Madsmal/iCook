package application;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
//import javax.xml.parsers.ParserConfigurationException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Model.primaryStage = primaryStage;
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("iCook");
		primaryStage.setScene(scene);		
		primaryStage.show();
	
	}
	
	// Raspberry pi 3 screen resolution 800x480	
	
	// frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); til at lukke vindue på raspberry.
	
	public static void main(String[] args) throws IOException, Exception {

		launch(args);
		File file = new File("src\\application\\RecipeLibrary\\asciuta.xml");
	    JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    System.setProperty("javax.xml.accessExternalDTD", "all");
	    Recipe recipe = (Recipe) jaxbUnmarshaller.unmarshal(file);

//		Parser asciuta = new Parser();
//		asciuta.parseXMLFile();
	    
	    System.out.println(recipe.getID());
        System.out.println(recipe.getTitle());
        System.out.println(recipe.getStartdate());
        System.out.println(recipe.getChangedate());
        System.out.println(recipe.getTarget());
        System.out.println(recipe.getIngredients().getIngredient().get(0).getIname());
		
		//Parser domParser = new Parser();
		//Parser.readFileAndMakeDOM("asciuta.xml");
		//domParser.readFileAndMakeDOM();
		//launch(args);

	}
}
