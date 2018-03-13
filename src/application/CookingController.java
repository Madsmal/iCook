package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;


public class CookingController {


	public void onHome(ActionEvent event) {
		System.out.println("Exit");
		Platform.exit();
	}

	

}
