package application;

import java.io.*; //for File
import java.util.*; // for scanner

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
	
}
