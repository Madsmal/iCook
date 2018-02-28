package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class menuController {

	public void onExit(ActionEvent event) {
		System.out.println("Exit");
		Platform.exit();
	}
	
}
