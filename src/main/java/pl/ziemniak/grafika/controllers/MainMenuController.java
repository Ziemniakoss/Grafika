package pl.ziemniak.grafika.controllers;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import org.json.JSONException;
import pl.ziemniak.grafika.utils.DepthComparator;
import pl.ziemniak.grafika.utils.Object3D;
import pl.ziemniak.grafika.utils.io.*;
import pl.ziemniak.grafika.utils.rendering.Camera;
import pl.ziemniak.grafika.UserMovementTypes;
import pl.ziemniak.grafika.World;
import pl.ziemniak.grafika.utils.math.Line;
import pl.ziemniak.grafika.utils.rendering.IRenderer;
import pl.ziemniak.grafika.utils.rendering.Renderer;
import pl.ziemniak.grafika.utils.rendering.Screen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenuController implements Initializable {
	public Label labelRotZ;
	public Label labelRotY;
	public Label labelRotX;
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

	private final double ADD_ZOOM_ON_SCROLL = 0.02;
	private final double ROTATION_SPEED = 30; // stopnie na sekunde
	private final double MOVEMENT_SPEED = 20; //punkty na sekunde
	private World world;
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
			world.getCamera().addToZoom(ADD_ZOOM_ON_SCROLL);
		} else {
			world.getCamera().addToZoom(-ADD_ZOOM_ON_SCROLL);
		}
		labelZoom.setText(world.getCamera().getZoom() + "");

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		screen.setOnScroll(this::handleScroll);
		IWorldReader reader = new JSONWorldReader("example.json");
		try {
			world = reader.readFromFile();
		} catch (NonParsableWorldException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		new AnimationTimer() {
			private long lastUpdate = System.nanoTime();

			@Override
			public void handle(long currentNanoTime) {
				double deltaSeconds = (currentNanoTime - lastUpdate) / 1_000_000_000.0;
				lastUpdate = currentNanoTime;
				updateCamera(deltaSeconds);
				screen.clear();
				List<Object3D> adjusted = world.getObject3DS().parallelStream().
						map(e -> e.adjustToCamera(world.getCamera())).
						sorted(new DepthComparator()).
						collect(Collectors.toList());
				for (Object3D l : adjusted) {
					l.render(screen, world.getCamera().getZoom(), -400);
				}
				updateCameraInfo();
			}

			private void updateCamera(double deltaSeconds) {
				Camera camera = world.getCamera();
				if (userInputState.get(UserMovementTypes.ROTATES_LEFT)) {
					world.getCamera().rotateY(deltaSeconds * ROTATION_SPEED);
				}
				if (userInputState.get(UserMovementTypes.ROTATES_RIGHT)) {
					world.getCamera().rotateY(-deltaSeconds * ROTATION_SPEED);
				}
				if (userInputState.get(UserMovementTypes.ROTATES_DOWN)) {
					world.getCamera().rotateX(-deltaSeconds * ROTATION_SPEED);
				}
				if (userInputState.get(UserMovementTypes.ROTATES_UP)) {
					world.getCamera().rotateX(deltaSeconds * ROTATION_SPEED);
				}
				if (userInputState.get(UserMovementTypes.TILTS_LEFT)) {
					world.getCamera().rotateZ(deltaSeconds * ROTATION_SPEED);
				}
				if (userInputState.get(UserMovementTypes.TILTS_RIGHT)) {
					world.getCamera().rotateZ(-deltaSeconds * ROTATION_SPEED);
				}
				if (userInputState.get(UserMovementTypes.MOVES_DOWN)) {
					camera.setY(camera.getY() + MOVEMENT_SPEED * deltaSeconds);
				}
				if (userInputState.get(UserMovementTypes.MOVES_UP)) {
					camera.setY(camera.getY() - MOVEMENT_SPEED * deltaSeconds);
				}
				if (userInputState.get(UserMovementTypes.MOVES_RIGHT)) {
					camera.moveToSide(deltaSeconds * MOVEMENT_SPEED);
				}
				if (userInputState.get(UserMovementTypes.MOVES_LEFT)) {
					camera.moveToSide(-MOVEMENT_SPEED * deltaSeconds);

				}
				if (userInputState.get(UserMovementTypes.MOVES_BACKWARDS)) {
					camera.moveForward(-deltaSeconds * MOVEMENT_SPEED);

				}
				if (userInputState.get(UserMovementTypes.MOVES_FORWARDS)) {
					camera.moveForward(deltaSeconds * MOVEMENT_SPEED);
				}
			}
		}.start();
	}

	private void updateCameraInfo() {
		DecimalFormat formatter = new DecimalFormat("#.00");
		labelZoom.setText(formatter.format(world.getCamera().getZoom()));
		labelX.setText(formatter.format(world.getCamera().getX()));
		labelY.setText(formatter.format(world.getCamera().getY()));
		labelZ.setText(formatter.format(world.getCamera().getZ()));

		labelRotX.setText(formatter.format(world.getCamera().getRotationX()));
		labelRotY.setText(formatter.format(world.getCamera().getRotationY()));
		labelRotZ.setText(formatter.format(world.getCamera().getRotationZ()));
	}

	public void readWorld(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Wybierz plik z światem");//todo filtr żeby tylko json
		File f = fileChooser.showOpenDialog(null);
		try {
			readWorldFromFile(f.getName());
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR,"Plik nie istnieje", ButtonType.CLOSE);
			alert.showAndWait();
		} catch (NonParsableWorldException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR,"NIepoprawna zawwartość pliku\n\n"+e.getMessage(),ButtonType.CLOSE);
			alert.showAndWait();
		}catch (JSONException e){
			Alert alert = new Alert(Alert.AlertType.ERROR,"Zawartość pliku musi być tablicą w formacie JSON",ButtonType.CLOSE);
			alert.showAndWait();
		}
		screen.requestFocus();
	}

	public void readWorldFromFile(String filename) throws IOException, NonParsableWorldException {
		JSONWorldReader reader = new JSONWorldReader(filename);
		world = reader.readFromFile();
	}
}
