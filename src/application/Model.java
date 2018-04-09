package application;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javafx.stage.Stage;

public class Model {
	static Stage primaryStage;
	static Recipe recipe;
	
	
	public static void parseXMLFile(String path) throws Exception {
		File file = new File(path);
	    JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    System.setProperty("javax.xml.accessExternalDTD", "all");
	    recipe = (Recipe) jaxbUnmarshaller.unmarshal(file);

	}
}
