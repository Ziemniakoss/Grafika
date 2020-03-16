package pl.ziemniak.grafika.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import pl.ziemniak.grafika.UserMovementTypes;
import pl.ziemniak.grafika.World;
import pl.ziemniak.grafika.utils.io.IMapReader;
import pl.ziemniak.grafika.utils.io.JSONMapReader;
import pl.ziemniak.grafika.utils.math.Line;
import pl.ziemniak.grafika.utils.rendering.IRenderer;
import pl.ziemniak.grafika.utils.rendering.Renderer;
import pl.ziemniak.grafika.utils.rendering.Screen;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
	@FXML
	private Label labelZoom;
	@FXML
	private Screen screen;
	@FXML
	private Label labelX;
	@FXML
	private Label labelY;
	@FXML
	private Label labelZ;
	@FXML
	private Label labelRoll;
	@FXML
	private Label lawYaw;
	@FXML
	private Label labelPitch;
	@FXML
	private Label labelFPS;

	private final double ADD_ZOOM_ON_SCROLL = 0.02;
	private final double ROTATION_SPEED = 2; // stopnie na sekunde
	private final double MOVEMENT_SPEED = 2; //punkty na sekunde
	private World world = World.getInstance();
	private final HashMap<UserMovementTypes, Boolean> userInputState;
	private final HashMap<KeyCode, UserMovementTypes> keyBindings;
	private IRenderer renderer;

	public MainMenuController() {
		userInputState = new HashMap<>();
		for (UserMovementTypes u : UserMovementTypes.values()) {
			userInputState.put(u, false);
		}
		keyBindings = new HashMap<>();
		keyBindings.put(KeyCode.W, UserMovementTypes.MOVES_FORWARDS);
		keyBindings.put(KeyCode.S, UserMovementTypes.MOVES_BACKWARDS);
		keyBindings.put(KeyCode.A, UserMovementTypes.MOVES_LEFT);
		keyBindings.put(KeyCode.D, UserMovementTypes.MOVES_RIGHT);
		keyBindings.put(KeyCode.SPACE, UserMovementTypes.MOVES_UP);
		keyBindings.put(KeyCode.SHIFT, UserMovementTypes.MOVES_DOWN);

		keyBindings.put(KeyCode.I, UserMovementTypes.ROTATES_DOWN);
		keyBindings.put(KeyCode.K, UserMovementTypes.ROTATES_UP);
		keyBindings.put(KeyCode.J, UserMovementTypes.ROTATES_LEFT);
		keyBindings.put(KeyCode.L, UserMovementTypes.ROTATES_RIGHT);
		keyBindings.put(KeyCode.U, UserMovementTypes.TILTS_LEFT);
		keyBindings.put(KeyCode.O, UserMovementTypes.TILTS_RIGHT);
	}

	@FXML
	private void handleKeyRelease(KeyEvent keyEvent) {
		if (keyBindings.containsKey(keyEvent.getCode())) {
			userInputState.put(keyBindings.get(keyEvent.getCode()), false);
		}
	}

	@FXML
	private void handleKeyPress(KeyEvent keyEvent) {
		if (keyBindings.containsKey(keyEvent.getCode())) {
			userInputState.put(keyBindings.get(keyEvent.getCode()), true);
		}
	}

	@FXML
	private void handleScroll(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY() > 0) {
			screen.addToZoom(ADD_ZOOM_ON_SCROLL);
		} else {
			screen.addToZoom(-ADD_ZOOM_ON_SCROLL);
		}
		labelZoom.setText(screen.getZoom() + "");

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		screen.setOnScroll(this::handleScroll);
		IMapReader reader = new JSONMapReader("example.json");
		try {
			world.addAllLines(reader.read());
		} catch (IOException e) {
			//todo dialog
			e.printStackTrace();
		}
		renderer = new Renderer(screen, world.getCamera());
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				//todo uplyw czasu
				//todo sprawdzenie stanu inputów
				//todo zaktualizowanie stanu kamery
				screen.clear();
				for(Line l : world.getLines()){
					renderer.render(l);
				}

			}
		}.start();
	}

	private void updateCameraInfo() {

	}
}
