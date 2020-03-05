package pl.ziemniak.grafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Canvas c = new Canvas(500,500);
		Scene s = new Scene(new HBox(c));
		primaryStage.setScene(s);
		primaryStage.show();
	}
}
