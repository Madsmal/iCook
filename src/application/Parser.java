package application;

import java.io.*; //for File
import java.util.*; // for scanner

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.sun.xml.internal.txw2.Document;

import java.lang.Object;

public class Parser {
	private String filename;
	private DocumentBuilder db;
	public static Document readFileAndMakeDOM(String filename) {
	     long time = - System.currentTimeMillis();
	     org.w3c.dom.Document dom = null;
	       //get the factory
	     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	       try {
	
	           //Using factory get an instance of document builder
	           DocumentBuilder db = dbf.newDocumentBuilder();
	
	           //parse using builder to get DOM representation of the XML file
	           dom = db.parse(filename);
	
	
	       } catch(ParserConfigurationException pce) {
	           pce.printStackTrace();
	       } catch(SAXException se) {
	           se.printStackTrace();
	       } catch(IOException ioe) {
	           ioe.printStackTrace();
	       }
	       time += System.currentTimeMillis();
	       Utilities.displayMsgLn("Time to read file and create DOM"+time+" msec.");
	      return(dom);
	   }

import javafx.application.Platform;

public class Parser {
	/*
	<?xml version="1.0" encoding="ISO-8859-1"?>
	<!DOCTYPE cookbook SYSTEM "rezepte.dtd">
	*/
	//<recipe> wraps all the xml
	String title;
	String ID;
	String startdate;
	String changedate;
	String target; //amount of people, default should be based on setting value. 
	String ingredients; //contains String ingredient; //contains String quantity; and String unit; and String iname;
	String preparation;
	String variation; //what you can change from the recipe
	String remark; //???
	String privateremark; //???
	String duration; //contains String totaltime; and String worktime;
	String source; //forfatter
	
	
	public void parseRecipe(String recipeName) {
		
	}
	
	public static String[] recipeLibraryList;
	
	public static String[] parseRecipeLibrary() {
		File folder = new File("src\\application\\RecipeLibrary");
		File[] listOfFiles = folder.listFiles();
		
		String[] recipeLibraryList = new String[listOfFiles.length]; 
		
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    		recipeLibraryList[i] = listOfFiles[i].getName();
	    	} else if (listOfFiles[i].isDirectory()) {
	    		System.out.println("Error: RecipeLibraryList folder contains a folder.");
	    		Platform.exit();
	    	}
	    }
	    return recipeLibraryList;
	}
>>>>>>> b133558a71fe53f13793fc90656ac15748e23a36
	
}
