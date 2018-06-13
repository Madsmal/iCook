package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.util.Callback;


public class CookingController implements Initializable {

	// Defining variables
	int[] taskSequence = new int[Model.recipe.tasks.task.size()];
	int currentTask = 0;
	double timePassed = 0; //does not include intermediate-task time
	ArrayList<CountdownTimer> countdownTimerArray = new ArrayList<CountdownTimer>();
	CountdownTimer countdownTimer2;
	Timeline timeline;
	Timeline timeline2;

	Image starEmpty = new Image(new File("src/application/Images/starEmpty2.png").toURI().toString());
	Image starFull = new Image(new File("src/application/Images/starFull2.png").toURI().toString());


	// Defining FXML elements
	@FXML ProgressBar pb;
	@FXML Button previous;
	@FXML Button pause;
	@FXML Button next;
	@FXML Button timerButton;
	@FXML Label task;
	@FXML Label countdownLabel;
	@FXML Label countdownLabel2;
	@FXML Label clock;
	@FXML private ListView<String> listView;
	@FXML Label taskTitle;
	

	@FXML ImageView star1;
	@FXML ImageView star2;
	@FXML ImageView star3;
	@FXML ImageView star4;
	@FXML ImageView star5;


	public void initialize(URL url, ResourceBundle rb) {

		
		Algorithm algorithm = new Algorithm(); 
		//System.out.println(java.util.Arrays.toString(algorithm.calculateTaskSequence()));
		taskSequence = algorithm.calculateTaskSequence();
		// System.out.println(java.util.Arrays.toString(taskSequence));
		
		
		int test = 0;
		for(int i = 0; i < taskSequence.length; i++) {
			test++;
			taskSequence[test-1] = taskSequence[i]-1;
		}
						
		// listview
		String[] taskSequenceStringArray = new String[taskSequence.length];
		for (int i = 0 ; i < taskSequence.length ; i++) {
			taskSequenceStringArray[i] = Model.recipe.tasks.task.get(taskSequence[i]).getTaskTitle();
		}
		ObservableList<String> data = FXCollections.observableArrayList(taskSequenceStringArray);
		listView.setItems(data);
		
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				for (int i = 0 ; i < taskSequence.length ; i++) {
					if (newValue.equals(Model.recipe.tasks.task.get(taskSequence[i]).getTaskTitle())) {
						currentTask = i;
						pause.setText("Pause");
						updateCountdownTimer2();
						updateProgressBar();
						updateButtonVisibility();
						updateListView();
						setTaskTitle();
						task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
					}
				}
			}
		});
		
		

		// Default FXML elements values
		setTaskTitle();
		task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());

		//if first task is attentionRequired == true
		updateCountdownTimer2();
		updateButtonVisibility();
		updateListView();
		
		// remove label identity text
		countdownLabel.setText("");

		// Continuously updating timeline
		timeline = new Timeline(
				new KeyFrame(Duration.millis(200), 
						new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {

						// CountdownTimer
						for (int i = 0 ; i < countdownTimerArray.size() ; i++) {
							if (countdownTimerArray.get(i).getTimeLeft() == 0) {
								// Alert if countdownTimer == 0
								Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Timer Alert");
								alert.setHeaderText(null);
								for (int n = 0 ; n < Model.recipe.tasks.task.size() ; n++) {
									if (Model.recipe.tasks.task.get(n).getID() == countdownTimerArray.get(i).getID()) {
										alert.setContentText(Model.recipe.tasks.task.get(n).getAlertString());
									}
								}
								alert.show();

								// Remove from array if countdownTimer == 0
								countdownTimerArray.remove(i);
								if (countdownTimerArray.size() >= i+1) {
									i--;
								}
								
								// Updates timerStart button
								updateButtonVisibility();
							}
						}

						// Create countdownTimer label
						String text = "";
						for (int i = 0; i < countdownTimerArray.size() ; i++) {
							for (int n = 0 ; n < Model.recipe.tasks.task.size() ; n++) {
								if (countdownTimerArray.get(i).getID() == Model.recipe.tasks.task.get(n).getID()) {
									text = text + Model.secondsToCollapsingHHMMSS(countdownTimerArray.get(i).getTimeLeft()) + " - " + Model.recipe.tasks.task.get(n).getTimerString() + "\n";
								}
							}
						}
						countdownLabel.setText(text);

						// countdownTimer2
						if (currentTask == taskSequence.length || Model.recipe.tasks.getTask().get(taskSequence[currentTask]).attentionRequired==false) {
							countdownLabel2.setText("");
						} else if (Model.recipe.tasks.getTask().get(taskSequence[currentTask]).attentionRequired==true) {
							countdownLabel2.setText("Task time: "+Integer.toString(countdownTimer2.getTimeLeft()));
						}

						// Intermediate-task progress bar update
						updateIntermediateProgressBar();

						// Update 'next' setDisable() value on second last page
						if (currentTask == taskSequence.length - 1 && countdownTimerArray.isEmpty()) {
							next.setDisable(false);
						}
					}
				}
						)
				);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		// clock timeline
		clock.setText(Model.getTime());
		int updateFrequency;
		if (Model.settings.get(Model.section, "clock").equals("hh:mm:ss.sss")) {
			updateFrequency = 1;
		} else {
			updateFrequency = 1000;
		}
		timeline2 = new Timeline(
				new KeyFrame(Duration.millis(updateFrequency), 
						new EventHandler<ActionEvent>() {
							@Override public void handle(ActionEvent actionEvent) {
								// clock update
								clock.setText(Model.getTime());
							}
						}
				)
		);
		timeline2.setCycleCount(Animation.INDEFINITE);
		timeline2.play();
	
	}


	// Button events
	public void onHome(ActionEvent event) throws Exception {
		timeline.stop();
		timeline2.stop();

		Parent home = FXMLLoader.load(getClass().getResource("/application/RecipesView.fxml"));
		Scene Home = new Scene(home);
		Model.primaryStage.setScene(Home);		
		Model.primaryStage.show();
	}

	public void onNext(ActionEvent event) throws Exception {
		if (currentTask != taskSequence.length) {
			currentTask++;
			pause.setText("Pause");
			updateCountdownTimer2();
			updateProgressBar();
			updateButtonVisibility();
			updateListView();
			if (currentTask != taskSequence.length) {
				setTaskTitle();
				task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());
			} else {
				taskTitle.setText("Enjoy your meal!");
				task.setText("You have finished cooking "+Model.recipe.getTitle());
			}
		}
	}

	public void onPrevious(ActionEvent event) throws Exception {
		if (currentTask != 0) {
			currentTask--;
			pause.setText("Pause");
			updateCountdownTimer2();
			updateProgressBar();
			updateButtonVisibility();
			updateListView();
			setTaskTitle();
			task.setText(Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTaskString());

		}
	}

	public void onPause(ActionEvent event) throws Exception {
		if (pause.getText().equals("Pause")) {
			countdownTimer2.pauseCountdownTimer();
			pause.setText("Resume");
		} else if (pause.getText().equals("Resume")) {
			countdownTimer2.continueCountdownTimer();
			pause.setText("Pause");
		}
	}

	public void onStartTimer(ActionEvent event) throws Exception {
		if (timerButton.getText().equals("Start timer")) {
			CountdownTimer countdownTimer = new CountdownTimer((Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTime()),Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getID());
			countdownTimerArray.add(countdownTimer);
			updateButtonVisibility();
		} else if (timerButton.getText().equals("Delete timer")) {
			for (int i = 0 ; i < countdownTimerArray.size() ; i++) {
				if (countdownTimerArray.get(i).getID() == Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getID()) {
					countdownTimerArray.get(i).pauseCountdownTimer();
					countdownTimerArray.remove(i);
					updateButtonVisibility();
				}
			}
		}
	}







	// Methods
	private void updateCountdownTimer2() {
		if (currentTask == taskSequence.length) {
			countdownTimer2 = null;
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) {
			countdownTimer2 = null;
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true) {
			countdownTimer2 = new CountdownTimer((Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getTime()),Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getID());	
		}
	}

	private void updateProgressBar() { 
		if (currentTask != taskSequence.length) {
		timePassed = 0;
		for (int i = 0 ; i < currentTask ; i++) {
			if (Model.recipe.tasks.task.get(taskSequence[i]).getAttentionRequired() == true) {
				timePassed = timePassed + (Model.recipe.tasks.task.get(taskSequence[i]).getTime());
			}
		}
		pb.setProgress(timePassed/Double.parseDouble(Model.recipe.getDuration().getTotaltime()));
		} else {
			pb.setProgress(1);
		}
		updateIntermediateProgressBar();
	}
	
	private void updateIntermediateProgressBar() {
		if (currentTask != taskSequence.length) {
			
			int largestCountdownTimer = 0;
			int largestCountdownTimerTimePassed = 0;
			int largestCountdownTimerTotalTime = 0;
			for (int i = 0 ; i < countdownTimerArray.size() ; i++) {
				if (countdownTimerArray.get(i).getTimeLeft() > largestCountdownTimer) {
					largestCountdownTimerTotalTime = countdownTimerArray.get(i).getTotalTime(); 
					largestCountdownTimer = countdownTimerArray.get(i).getTimeLeft(); 
					largestCountdownTimerTimePassed = countdownTimerArray.get(i).getTimePassed(); 
				}
			}
			
			double divider;
			if (Model.recipe.tasks.task.get(taskSequence[currentTask]).attentionRequired == true) {
				if ((timePassed + countdownTimer2.getTimePassed() + largestCountdownTimer) > Double.parseDouble(Model.recipe.getDuration().getTotaltime())) {
					divider = timePassed + largestCountdownTimerTotalTime;
				} else {
					divider = Double.parseDouble(Model.recipe.getDuration().getTotaltime());
				}
			} else {
				if ((timePassed + largestCountdownTimer) > Double.parseDouble(Model.recipe.getDuration().getTotaltime())) {
					divider = timePassed + largestCountdownTimerTotalTime;
				} else {
					divider = Double.parseDouble(Model.recipe.getDuration().getTotaltime());
				}
			}
			
			if (currentTask == taskSequence.length - 1) {	
				if (Model.recipe.tasks.task.get(taskSequence[taskSequence.length-1]).attentionRequired == true) {
					if (largestCountdownTimer > countdownTimer2.getTimeLeft()) {
						pb.setProgress((timePassed + largestCountdownTimerTimePassed)/divider);
					} else {
						pb.setProgress((timePassed + countdownTimer2.getTimePassed())/divider);
					}
				} else {
					pb.setProgress((timePassed + largestCountdownTimerTimePassed)/divider);
				}
				
			} else {
				if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true) {
					pb.setProgress((timePassed + countdownTimer2.getTimePassed())/divider);
				}
			}
		}
	}

	private void updateButtonVisibility() {
		// previous button
		if (currentTask == 0) {
			previous.setDisable(true);
		} else {
			previous.setDisable(false);
		}
		// pause button
		if (currentTask == taskSequence.length) {
			pause.setDisable(true);
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true) {
			pause.setDisable(false);
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) {
			pause.setDisable(true);
		}
		// next button
		if (currentTask == taskSequence.length) {
			next.setDisable(true);
		} else if (currentTask == taskSequence.length - 1 && (!countdownTimerArray.isEmpty() || Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) && Model.settings.get("dev", "nextAlwaysClickable").equals("false")) {
			next.setDisable(true);
		} else {
			next.setDisable(false);
		}
		// rating star buttons
		if (currentTask == taskSequence.length) {
			star1.setManaged(true);
			star2.setManaged(true);
			star3.setManaged(true);
			star4.setManaged(true);
			star5.setManaged(true);
			
			star1.setVisible(true);
			star2.setVisible(true);
			star3.setVisible(true);
			star4.setVisible(true);
			star5.setVisible(true);
			
			updateRating();
		} else {
			star1.setManaged(false);
			star2.setManaged(false);
			star3.setManaged(false);
			star4.setManaged(false);
			star5.setManaged(false);
			
			star1.setVisible(false);
			star2.setVisible(false);
			star3.setVisible(false);
			star4.setVisible(false);
			star5.setVisible(false);
		}
		// timer button
		if (currentTask == taskSequence.length) {
			timerButton.setVisible(false);
			timerButton.setManaged(false);
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == true) {
			timerButton.setVisible(false);
			timerButton.setManaged(false);
		} else if (Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getAttentionRequired() == false) {
			timerButton.setVisible(true);
			timerButton.setManaged(true);
			for (int i = 0 ; i < countdownTimerArray.size() ; i++) {
				if (countdownTimerArray.get(i).getID() == Model.recipe.getTasks().getTask().get(taskSequence[currentTask]).getID()) {
					timerButton.setText("Delete timer");
					break;
				} else {
					timerButton.setText("Start timer");
				}
			}
			if (countdownTimerArray.size() == 0) {
				timerButton.setText("Start timer");
			}
		}
	}

	// Rating system
	public void onStar1(MouseEvent event) throws Exception {
		Model.recipe.rating = 1;
		updateRating();
		Model.ingredientsQuantityMultiplier(1/Model.servingAmount);
		Model.saveXMLFile();
		Model.ingredientsQuantityMultiplier(Model.servingAmount);
	}
	public void onStar2(MouseEvent event) throws Exception {
		Model.recipe.rating = 2;
		updateRating();
		Model.ingredientsQuantityMultiplier(1/Model.servingAmount);
		Model.saveXMLFile();
		Model.ingredientsQuantityMultiplier(Model.servingAmount);
	}
	public void onStar3(MouseEvent event) throws Exception {
		Model.recipe.rating = 3;
		updateRating();
		Model.ingredientsQuantityMultiplier(1/Model.servingAmount);
		Model.saveXMLFile();
		Model.ingredientsQuantityMultiplier(Model.servingAmount);
	}
	public void onStar4(MouseEvent event) throws Exception {
		Model.recipe.rating = 4;
		updateRating();
		Model.ingredientsQuantityMultiplier(1/Model.servingAmount);
		Model.saveXMLFile();
		Model.ingredientsQuantityMultiplier(Model.servingAmount);
	}
	public void onStar5(MouseEvent event) throws Exception {
		Model.recipe.rating = 5;
		updateRating();
		Model.ingredientsQuantityMultiplier(1/Model.servingAmount);
		Model.saveXMLFile();
		Model.ingredientsQuantityMultiplier(Model.servingAmount);
	}
	private void updateRating() {
		star1.setImage(starFull);
		if (Model.recipe.rating >= 2) {
			star2.setImage(starFull);
		} else {
			star2.setImage(starEmpty);
		}
		if (Model.recipe.rating >= 3) {
			star3.setImage(starFull);
		} else {
			star3.setImage(starEmpty);
		}
		if (Model.recipe.rating >= 4) {
			star4.setImage(starFull);
		} else {
			star4.setImage(starEmpty);
		}
		if (Model.recipe.rating == 5) {
			star5.setImage(starFull);
		} else {
			star5.setImage(starEmpty);
		}
	}
	
	private void setTaskTitle() {
		taskTitle.setText("Task " + (currentTask+1)+": "+Model.recipe.tasks.task.get(taskSequence[currentTask]).getTaskTitle());
	}

	private void updateListView() {
		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        
                        //setText and setStyle
                        for (int i = 0 ; i < taskSequence.length ; i++) {
                    		if (item == Model.recipe.tasks.task.get(taskSequence[i]).getTaskTitle()) {
                    			if (i != currentTask) {
                    				setText((i+1)+". "+item);
	                            } else if (i == currentTask) {
	                            	setText(">> "+(i+1)+". "+item);
	                            	//listView.getFocusModel().focus(i);
	                            }
                    		}
                    	}
                    }
                };
            }
        });
	}


}
