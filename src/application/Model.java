package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javafx.stage.Stage;

import org.ini4j.Ini;

public class Model {
	static Stage primaryStage;
	static Recipe recipe;
	static String parsedFileName;
	static Ini settings;
	static String section = "default"; //Used with ini get and put methods
	
	
	
	
	public static void parseXMLFile(String path) throws Exception {
		File file = new File(path);
	    JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    System.setProperty("javax.xml.accessExternalDTD", "all");
	    recipe = (Recipe) jaxbUnmarshaller.unmarshal(file);
	}
	
	public static void saveXMLFile() throws Exception {
		JAXB.marshal(Model.recipe, new File("src/application/RecipeLibrary/"+parsedFileName+".xml"));
	}
	
	public static void parseIni() throws IOException {
		settings = new Ini(new FileReader("src/application/settings.ini"));
	}
	
	public static void saveIni() throws IOException {
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
        
        if (Model.settings.get(Model.section, "clock").equals("hh:mm:ss") || Model.settings.get(Model.section, "clock").equals("hh:mm:ss.sss")) {
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
	
	private int hours;
	private int minutes;
	
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
	
	public static String secondsToHHMMSS(int seconds) {
		String string = "";
		if (Integer.toString(seconds/3600).length() >= 2) {
			string = string + seconds/3600;
		} else {
			string = string + "0" + seconds/3600;
		}
		
		if (Integer.toString((seconds%3600)/60).length() == 2) {
			string = string + ":" + (seconds%3600)/60;
		} else {
			string = string + ":0" + (seconds%3600)/60;
		}
		
		if (Integer.toString((seconds%3600)%60).length() == 2) {
			string = string + ":" + (seconds%3600)%60;
		} else {
			string = string + ":0" + (seconds%3600)%60;
		}
		return string;
	}
	
	public static String secondsToCollapsingHHMMSS(int seconds) {
		String string = "";
		boolean HH = false;
		boolean MM = false;
		
		if (Integer.toString(seconds/3600).length() >= 2) {
			string = string + seconds/3600 + ":";
			HH = true;
		} else if (Integer.toString(seconds/3600).length() == 1 && seconds/3600 != 0){
			string = string + seconds/3600 + ":";
			HH = true;
		}
		
		if (Integer.toString((seconds%3600)/60).length() == 2) {
			string = string + (seconds%3600)/60 + ":";
			MM = true;
		} else if (Integer.toString((seconds%3600)/60).length() == 1 && HH == true) {
			string = string + "0" + (seconds%3600)/60 + ":";
			MM = true;
		} else if ((seconds%3600)/60 != 0) {
			string = string + (seconds%3600)/60 + ":";
			MM = true;
		}
		
		if (Integer.toString((seconds%3600)%60).length() == 2) {
			string = string + (seconds%3600)%60;
		} else if (Integer.toString((seconds%3600)%60).length() == 1 && MM == true){
			string = string + "0" + (seconds%3600)%60;
		} else {
			string = string + (seconds%3600)%60;
		}
		return string;
	}
	
	static double servingAmount = 1;
	public static void ingredientsQuantityMultiplier(double multiplier) {
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
		if (string2.endsWith(".0")) {
			string2 = string2.substring(0, string2.length() - 2);
		}
		Model.recipe.setCalories(string2);
	}
}

