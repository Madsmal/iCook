package application;

import java.io.File;
import java.util.Calendar;

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
	
	public static String getTime() {
		Calendar time = Calendar.getInstance();
        String clock = "";
        
        if (Integer.toString(time.get(Calendar.HOUR_OF_DAY)).length() == 2) {
        	clock = clock + Integer.toString(time.get(Calendar.HOUR_OF_DAY));
        } else {
        	clock = clock + "0" + Integer.toString(time.get(Calendar.HOUR_OF_DAY));
        }
        
        if (Integer.toString(time.get(Calendar.MINUTE)).length() == 2) {
        	clock = clock + ":" + Integer.toString(time.get(Calendar.MINUTE));
        } else {
        	clock = clock + ":0" + Integer.toString(time.get(Calendar.MINUTE));
        }
        /*
        if (Integer.toString(time.get(Calendar.SECOND)).length() == 2) {
        	clock = clock + ":" + Integer.toString(time.get(Calendar.SECOND));
        } else {
        	clock = clock + ":0" + Integer.toString(time.get(Calendar.SECOND));
        }
        
        clock = clock + "." + Integer.toString(time.get(Calendar.MILLISECOND));
        */
        return clock;
	}
}
