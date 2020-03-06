package pl.ziemniak.grafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.ziemniak.grafika.utils.Screen;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Screen c = new Screen(500, 500);
		Scene s = new Scene(new HBox(c));
		primaryStage.setScene(s);
		primaryStage.show();
	}
}
