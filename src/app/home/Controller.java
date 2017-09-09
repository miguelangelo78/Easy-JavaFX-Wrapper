package app.home;

import gui.GenericController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends GenericController {

	public Controller(Stage stage, Scene scene, Parent root) {
		super(stage, scene, root);
		// Add your code here
	}

	@FXML private void goBackToStart() throws Exception {
		openAndCloseThis("start");
	}
	
	@Override
	public void handle(Event event) {
		// Add your code here
	}
	
}
