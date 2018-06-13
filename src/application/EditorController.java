/**
 * @author Saadman Haq s160081
 */

package application;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EditorController implements Initializable {
	@FXML private TextField titleText;
	private byte recipeCount = 0;
	@FXML private TextField sourceText;
	@FXML private TextField startdateText;
	@FXML private TextField fatText;
	@FXML private TextField carbsText;
	@FXML private TextField proteinText;
	@FXML private TextField caloriesText;
	@FXML private TextField totaltimeText;
	@FXML private TextField worktimeText;
	@FXML private TextField ratingText;
	@FXML private VBox ingBox;
	@FXML private VBox taskBox;
	
	private List<IngredientUI> ingredientUIs = new ArrayList<IngredientUI>();
	private List<TaskUI> taskUIs = new ArrayList<TaskUI>();
	
	public static boolean editMode = false;
	
	
	String editFileName;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		recipeCount=Byte.parseByte(Util.getId());
		loadEditFile();		
	}
	
	//make changes to existing recipe (except title)
	private void loadEditFile() {
		if(editMode) {
			editFileName = "src/application/RecipeLibrary/"+EditRecipeController.selectedName+".xml";
			
			try {
				Model.parseXMLFile(editFileName);
				Recipe rec = Model.recipe;
				Recipe.Duration recd = rec.getDuration();
				titleText.setText(rec.getTitle());
				titleText.setEditable(false);
				//filenameText.setText(EditRecipeController.selectedName);
				sourceText.setText(rec.getSource());
				startdateText.setText(rec.getStartdate());
				fatText.setText(""+rec.getFat());
				carbsText.setText(""+rec.getCarbohydrates());
				proteinText.setText(""+rec.getProtein());
				caloriesText.setText(rec.getCalories());
				ratingText.setText(""+rec.getRating());
				totaltimeText.setText(recd.getTotaltime());
				worktimeText.setText(recd.getWorktime());
				
				List<Recipe.Ingredients.Ingredient> ing = rec.getIngredients().ingredient;
				for(Recipe.Ingredients.Ingredient e : ing) {
					addIngredient(e.iname, e.quantity, e.unit);
				}
				
				List<Recipe.Tasks.Task> tk = rec.getTasks().task;
				for(Recipe.Tasks.Task s : tk) {
					addTask(""+s.time, s.taskString, s.taskTitle, s.timerString, s.alertString);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	//Recipe.Ingredients ingredients = new Recipe.Ingredients();
	//save information from textfields to xml file
	public void saveToXMLFile() throws Exception {
		Recipe rec = new Recipe();
		rec.setTitle(titleText.getText());
		rec.setID(++recipeCount);
		rec.setSource(sourceText.getText());
		rec.setStartdate(startdateText.getText());
		rec.setFat(Float.parseFloat(fatText.getText()));
		rec.setCarbohydrates(Float.parseFloat(carbsText.getText()));
		rec.setProtein(Float.parseFloat(proteinText.getText()));
		rec.setCalories(caloriesText.getText());
		rec.setRating(Integer.parseInt(ratingText.getText()));
		
		Recipe.Duration t = new Recipe.Duration();
		t.setTotaltime(totaltimeText.getText());
		t.setWorktime(worktimeText.getText());
		rec.setDuration(t);
		
		
		rec.setIngredients(new Recipe.Ingredients());
		for(IngredientUI ui : ingredientUIs) {
			Recipe.Ingredients.Ingredient ingredient = new Recipe.Ingredients.Ingredient();
			ingredient.setIname(ui.getiName().getText());
			ingredient.setQuantity(ui.getQuantity().getText());
			ingredient.setUnit(ui.getUnit().getText());
			
			rec.ingredients.getIngredient().add(ingredient);
			
		}
		
		rec.setTasks(new Recipe.Tasks());
		int taskId = 1;
		List<String> taskStringList = new ArrayList<String>();
		
		
		for(TaskUI ui : taskUIs) {
			String taskString = ui.getTaskString().getText();
			if(taskStringList.contains(taskString))
				continue;
			taskStringList.add(taskString);
			Recipe.Tasks.Task task = new Recipe.Tasks.Task();
			boolean att = ui.getAttention().getSelectionModel().selectedIndexProperty().get()== 0 ? true : false;
			//timerButton.setVisible(false);
			//timerButton.setManaged(false);
			task.setTaskTitle(ui.getTaskTitle().getText());
			task.setAlertString(ui.getAlertString().getText());
			task.setAttentionRequired(att);
			task.setTaskString(taskString);
			task.setTime(Integer.parseInt(ui.getTime().getText()));
			task.setTimerString(ui.getTimerString().getText());
			
			
			task.setID(taskId);
			rec.tasks.getTask().add(task);
		
			taskId++;
		}
		String path = "src/application/RecipeLibrary/" + titleText.getText() + ".xml";
		
		try {
			File file = new File(path);
			if(!file.exists()) {
				Util.storeId(""+recipeCount);
				file.createNewFile();
			} else {
				Model.parseXMLFile(path);
				Recipe r = Model.recipe;
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
			
				rec.setChangedate(sdf.format(now));
				rec.setID(r.getID());
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(rec, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void onAddRecipe (ActionEvent event) throws Exception {
		editMode= false;
		Parent addRecipe = FXMLLoader.load(getClass().getResource("/application/AddRecipeView.fxml"));
		Scene AddRecipe = new Scene(addRecipe);
		Model.primaryStage.setScene(AddRecipe);
		Model.primaryStage.show();
		
	}
	
	public void onGoToTasks (ActionEvent event) throws Exception {
		Parent task = FXMLLoader.load(getClass().getResource("/application/AddTasksView.fxml"));
		Scene Task = new Scene(task);
		Model.primaryStage.setScene(Task);
		Model.primaryStage.show();
	}
	
	public void onBackToEditor (ActionEvent event) throws Exception {
		Parent back = FXMLLoader.load(getClass().getResource("/application/EditorView.fxml"));
		Scene Back = new Scene(back);
		Model.primaryStage.setScene(Back);
		Model.primaryStage.show();
	}
	
	public void onEditRecipe (ActionEvent event) throws Exception {
		Parent edit = FXMLLoader.load(getClass().getResource("/application/EditRecipeView.fxml"));
		Scene Edit = new Scene(edit);
		Model.primaryStage.setScene(Edit);
		Model.primaryStage.show();
	}
	
	public void onExit(ActionEvent event) throws Exception {
		Parent home = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}

	
	int ingCounter = 1;
	public void addIngredientToUI(ActionEvent event) throws Exception {
		addIngredient("", "", "");
	}
	
	private void addIngredient(String name, String q, String uni) {
		TextField iName = new TextField(name), quan = new TextField(q),unit = new TextField(uni) ;
		iName.setPromptText("Enter ingredient name");
		quan.setPromptText("Enter ingredient quantity");
		unit.setPromptText("Enter ingredient unit");
		
		ingBox.setSpacing(20);
		ingBox.getChildren().addAll(new Label("Ingredient "+ingCounter), iName, quan, unit );
		ingCounter++;
		
		ingredientUIs.add(new IngredientUI(iName, quan, unit));
	}
	
	int taskCounter = 1;
	public void addTaskToUI (ActionEvent event) throws Exception {
		addTask("","","","","");
//		TextField time = new TextField();
//		TextArea taskString = new TextArea();
//		TextField taskTitle = new TextField();
//		TextField timerString = new TextField();
//		TextField alertString = new TextField();
//		
//		ComboBox<String> attention = new ComboBox<String>();
//		attention.getItems().add("True");
//		attention.getItems().add("False");
//		
//		time.setPromptText("Enter amount of time for task");
//		taskString.setPromptText("Describe action of task");
//		taskTitle.setPromptText("Give the task a title");
//		timerString.setPromptText("(Optional) Timer title for when you start your timer");
//		alertString.setPromptText("(Optional) Alert message for when timer runs out");
//		
//		taskBox.setSpacing(20);
//		taskBox.getChildren().addAll(new Label("Task "+ taskCounter), time, taskString, 
//				taskTitle, timerString, alertString,attention);
//		taskCounter++;
//		
//		taskUIs.add(new TaskUI(time, taskString, taskTitle, alertString, timerString, attention));
	}
	
	private void addTask(String tim, String ts, String tt, String tstr, String astr) {
		TextField time = new TextField(tim);
		TextArea taskString = new TextArea(ts);
		TextField taskTitle = new TextField(tt);
		TextField timerString = new TextField(tstr);
		TextField alertString = new TextField(astr);
		
		ComboBox<String> attention = new ComboBox<String>();
		attention.getItems().add("This task requires my full attention");
		attention.getItems().add("This task does not require my full attention");
		
		time.setPromptText("Enter amount of time for task");
		taskString.setPromptText("Describe action of task");
		taskTitle.setPromptText("Give the task a title");
		timerString.setPromptText("(Optional) Timer title for when you start your timer");
		alertString.setPromptText("(Optional) Alert message for when timer runs out");
		
		taskBox.setSpacing(20);
		taskBox.getChildren().addAll(new Label("Task "+ taskCounter), time, taskString, 
				taskTitle, timerString, alertString,attention);
		taskCounter++;
		
		taskUIs.add(new TaskUI(time, taskString, taskTitle, alertString, timerString, attention));
	}

	
	
	private class IngredientUI{
		
		private TextField iName;
		private TextField quantity; 
		private TextField unit;

		public IngredientUI(TextField iName, TextField quantity, TextField unit) {
			super();
			this.iName = iName;
			this.quantity = quantity;
			this.unit = unit;
		}

		public TextField getiName() {
			return iName;
		}

		public TextField getQuantity() {
			return quantity;
		}

		public TextField getUnit() {
			return unit;
		}
	}
	
	private class TaskUI {
		private TextField time;
		private TextArea taskString;
		private TextField taskTitle;
		private TextField alertString;
		private TextField timerString;
		private ComboBox attention;
		
		
		public TaskUI(TextField time, TextArea taskString, TextField taskTitle, TextField alertString,
				TextField timerString, ComboBox attention) {
			super();
			this.time = time;
			this.taskString = taskString;
			this.taskTitle = taskTitle;
			this.alertString = alertString;
			this.timerString = timerString;
			this.attention = attention;
		}
		
		public TextField getTime() {
			return time;
		}
		
		public TextArea getTaskString() {
			return taskString;
		}
		
		public TextField getTaskTitle() {
			return taskTitle;
		}
		
		public TextField getAlertString() {
			return alertString;
		}
		
		public TextField getTimerString() {
			return timerString;
		}
		
		public ComboBox getAttention() {
			return attention;
		}
		
	}
}
