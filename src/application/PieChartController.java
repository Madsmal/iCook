package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


public class PieChartController implements Initializable {
	@FXML private PieChart piechart;
	@FXML private Label caption;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//TODO
	}
	public void onBack (ActionEvent event) throws Exception {
		Parent home = FXMLLoader.load(getClass().getResource("/application/RecipesView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}
	
	private float calculateFat() {
		float fat = 9*Model.recipe.getFat();
		float fatp = (float) ((fat/Integer.parseInt(Model.recipe.getCalories()))) * 100; 
		return fatp;
	}
	
	private float calculateCarbs() {
		float carbohydrates = 4*Model.recipe.getCarbohydrates();
		float carbp = (float) ((carbohydrates/Integer.parseInt(Model.recipe.getCalories())) * 100);
		return carbp;
	}
	
	private float calculateProtein() {
		float protein = 4*Model.recipe.getProtein();
		float protp = (float) ((protein/Integer.parseInt(Model.recipe.getCalories())) * 100);
		return protp;
	}
	
	private float calculateOthers() {
		int cals = Integer.parseInt(Model.recipe.getCalories());
		float cfp = calculateFat()+calculateCarbs()+calculateProtein();
		float others = (float) ((cals-cfp)/cals);
		return others;
	}
	
	@FXML
	public void onCurrentMeal (ActionEvent event) throws Exception {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Fat", calculateFat()),
				new PieChart.Data("Carbohydrates", calculateCarbs()),
				new PieChart.Data("Protein", calculateProtein()),
				new PieChart.Data("Vitamins, minerals, etc.", calculateOthers()));
		piechart.setTitle("Calorie Distribution for " + Model.recipe.getTitle());
		piechart.setData(pieChartData);
		
		 for (final PieChart.Data data : piechart.getData()) {
	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
	                e -> {
	                    double total = 0;
	                    for (PieChart.Data d : piechart.getData()) {
	                        total += d.getPieValue();
	                    }
	                    String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
	                    caption.setText(text);
	                 }
	                );
	        }
		Model.primaryStage.setTitle("Calorie Distribution");
		Model.primaryStage.show();
	}
	
	@FXML
	public void onBalancedDiet (ActionEvent event) throws Exception {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("50% Carbohydrates", 50),
				new PieChart.Data("25% Fat", 25),
				new PieChart.Data("25% Protein", 25));
		piechart.setTitle("Calorie Distribution for a healthy balanced diet");
		piechart.setData(pieChartData);
	}
	
	@FXML
	public void onMuscleGain (ActionEvent event) throws Exception {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("60% Carbohydrates", 60),
				new PieChart.Data("25% Protein", 25),
				new PieChart.Data("15% Fat", 15));
		piechart.setTitle("Calorie Distribution for a muscle gain diet");
		piechart.setData(pieChartData);
	}
	
	@FXML
	public void onWeightLoss (ActionEvent event) throws Exception {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("50% Protein", 50),
				new PieChart.Data("20% Carbohydrates", 20),
				new PieChart.Data("30% Fat", 30));
		piechart.setTitle("Calorie Distribution for a weight loss diet");
		piechart.setData(pieChartData);
	}

}
