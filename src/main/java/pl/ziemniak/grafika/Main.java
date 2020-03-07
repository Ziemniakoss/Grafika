package pl.ziemniak.grafika;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.ziemniak.grafika.utils.Screen;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Screen c = new Screen(500, 500);
		Parent root = FXMLLoader.load(getClass().getResource(File.separator + "MainMenu.fxml"));


		Scene s = new Scene(new HBox(root));
		primaryStage.setScene(s);
		primaryStage.show();
	}
}
