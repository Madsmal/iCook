package application;

import java.io.*; //for File
import java.util.*; // for scanner

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;


//import com.sun.xml.internal.txw2.Document;

//import com.sun.xml.internal.txw2.Document;

import java.lang.Object;


import javafx.application.Platform;



public class Parser {
	
	
	
	
//	private File filename;
//	private DocumentBuilder db;
//	private List<Recipe> myRecps = new ArrayList<Recipe>();
//	
//	void readFileAndMakeDOM() throws IOException, ParserConfigurationException {
//	    long time = - System.currentTimeMillis();
//	    Document dom = null;
//	       //get the factory
//	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//	    try {
//	
//	           //Using factory get an instance of document builder
//	           DocumentBuilder db = dbf.newDocumentBuilder();
//	
//	           //parse using builder to get DOM representation of the XML file
//	           dom = db.parse("src\\application\\asciuta.xml");
//	           parseDoc(dom);
//	           printData();
//	           
//	           
//	
//	
//	       } catch(ParserConfigurationException pce) {
//	           pce.printStackTrace();
//	       } catch(SAXException se) {
//	           se.printStackTrace();
//	       } catch(IOException ioe) {
//	           ioe.printStackTrace();
//	       }
//	       //time += System.currentTimeMillis();
//	       //System.out.println("Time to read file and create DOM: "+time+" msec.");
//	      //return (dom);
//	}
//	
//	private void parseDoc(Document dom) {
//		Element docEle = dom.getDocumentElement();
//		
//		NodeList nl = docEle.getElementsByTagName("recipe");
//		if (nl != null && nl.getLength() > 0) {
//			for (int i = 0; i < nl.getLength(); i++) {
//				Element el = (Element) nl.item(i);
//				
//				Recipe r = getRecipe(el);
//				
//				myRecps.add(r);
//				
//			}
//		}
//	}
//	
//	private Recipe getRecipe (Element recEl) {
//		String name = getText(recEl, "title");
//		int id = getInt(recEl, "ID");
//		int target = getInt(recEl,"target");
//		
//		String ingredient = recEl.getAttribute("ingredients");
//		
//		Recipe r = new Recipe(id, name, target, ingredient);
//		
//		return r;
//		
//	}
//	
//	private String getText(Element ele, String tagName) {
//		String textVal = null;
//        NodeList nl = ele.getElementsByTagName(tagName);
//        if (nl != null && nl.getLength() > 0) {
//            Element el = (Element) nl.item(0);
//            textVal = el.getFirstChild().getNodeValue();
//        }
//        return textVal;
//	}
//	
//	private int getInt (Element ele, String tagName) {
//		return Integer.parseInt(getText(ele, tagName));
//	}
//	
//	private void printData() {
//        System.out.println("No of Recipes '" + myRecps.size() + "'.");
//
//        Iterator<Recipe> it = myRecps.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next().toString());
//        }
//	}
	

	
}
