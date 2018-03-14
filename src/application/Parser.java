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
	
}
