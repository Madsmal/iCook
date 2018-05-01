<<<<<<< HEAD
package application;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javafx.stage.Stage;

public class Model {
	static Stage primaryStage;
	static Recipe recipe;
	private int hours;
	private int minutes;
	
	
	public static void parseXMLFile(String path) throws Exception {
		File file = new File(path);
	    JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    System.setProperty("javax.xml.accessExternalDTD", "all");
	    recipe = (Recipe) jaxbUnmarshaller.unmarshal(file);

	}
	
	public void convertMinToHHMM(String totalMinutes) {
		this.hours = Integer.parseInt(totalMinutes)/60;
		this.minutes = Integer.parseInt(totalMinutes)-hours*60;
		
	}
	
	public int getHours() {
		return this.hours;
	}
	
	public int getMinutes() {
		return this.minutes;
	}
	
	
}
=======
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
>>>>>>> branch 'master' of https://github.com/Madsmal/iCook
