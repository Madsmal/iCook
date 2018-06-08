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
	
//	float cals = (float) (Integer.parseInt(Model.recipe.getCalories()));
	float fat = 9*Model.recipe.getFat();
	float carbohydrates = 4*Model.recipe.getCarbohydrates();
	float protein = 4*Model.recipe.getProtein();
//	float calTotal = fat + carbohydrates + protein;
//	float calD = cals - calTotal;
//	float updCarb = 4*Model.recipe.getCarbohydrates() + calD;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String fatpr = String.format("%.1f", calculateFat());
		String carbpr = String.format("%.1f", calculateExcessCarbs());
		String protpr = String.format("%.1f", calculateProtein());
		
		//temporary hack
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data(fatpr + "% Fat", calculateFat()),
				new PieChart.Data(carbpr + "% Carbohydrates", calculateExcessCarbs()),
				new PieChart.Data(protpr + "% Protein", calculateProtein()));
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
		
		Model.primaryStage.show();
	}
	public void onBack (ActionEvent event) throws Exception {
		Parent home = FXMLLoader.load(getClass().getResource("/application/RecipesView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}
	
	private float calculateFat() {
		float fatp = (float) ((fat/Integer.parseInt(Model.recipe.getCalories()))) * 100; 
		return fatp;
	}
	
//	private float calculateCarbs() {
//		float carbohydrates = 4*Model.recipe.getCarbohydrates();
//		float carbp = (float) ((carbohydrates/Integer.parseInt(Model.recipe.getCalories())) * 100);
//		return carbp;
//	}
	
	private float calculateProtein() {
		float protp = (float) ((protein/Integer.parseInt(Model.recipe.getCalories())) * 100);
		return protp;
	}
	
	private float calculateCalTotal() {
		float calTotal = fat + carbohydrates + protein;
		return calTotal;
	}
	
	//temporary hack
	private float calculateExcessCarbs() {
		float cals = (float) (Integer.parseInt(Model.recipe.getCalories()));
		float calD = cals - calculateCalTotal();
		float exCarbsCal = 4*Model.recipe.getCarbohydrates() + calD;
		float exCarbs = (exCarbsCal/cals) * 100;
		return exCarbs;
	}
	
	
	@FXML
	public void onCurrentMeal (ActionEvent event) throws Exception {
		String fatpr = String.format("%.1f", calculateFat());
		String carbpr = String.format("%.1f", calculateExcessCarbs());
		String protpr = String.format("%.1f", calculateProtein());
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data(fatpr + "% Fat", calculateFat()),
				new PieChart.Data(carbpr + "% Carbohydrates", calculateExcessCarbs()),
				new PieChart.Data(protpr + "% Protein", calculateProtein()));
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
