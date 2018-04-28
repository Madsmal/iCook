package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javafx.stage.Stage;

import org.ini4j.Ini;

public class Model {
	static Stage primaryStage;
	static Recipe recipe;
	static Ini settings;
	static String section = "default"; //Used with ini get and put methods
	
	public static void parseXMLFile(String path) throws Exception {
		File file = new File(path);
	    JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    System.setProperty("javax.xml.accessExternalDTD", "all");
	    recipe = (Recipe) jaxbUnmarshaller.unmarshal(file);
	}
	
	public static void parseIni() throws IOException {
		settings = new Ini(new FileReader("src/application/settings.ini"));
		System.out.print(Model.settings.get(Model.section, "clock"));
	}
	
	public static void storeIni() throws IOException {
		settings.store(new File("src/application/settings.ini"));
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
        
        if (Model.settings.get(Model.section, "clock").equals("hh:mm:ss")) {
        	if (Integer.toString(time.get(Calendar.SECOND)).length() == 2) {
		    	clock = clock + ":" + Integer.toString(time.get(Calendar.SECOND));
		    } else {
		    	clock = clock + ":0" + Integer.toString(time.get(Calendar.SECOND));
		    }
        }
        
        if (Model.settings.get(Model.section, "clock").equals("hh:mm:ss.sss")) {
		    clock = clock + "." + Integer.toString(time.get(Calendar.MILLISECOND));
        }
        
        return clock;
	}
	
}
