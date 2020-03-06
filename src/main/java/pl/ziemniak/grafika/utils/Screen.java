package pl.ziemniak.grafika.utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * Zmodyfikowana Canvas. Środek układu współrzędnych jest
 * umieszczony po środku Canavasa. Co więcej pionowa oś y ma
 * wartości rosnące do góry a nie do dołu jak w klasycznej Canvie
 */
public class Screen extends Canvas {
	private double zoom = 1;
	private final static Color BACKGROUND_COLOR = Color.BLACK;


	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public void addToZoom(double zoom){
		this.zoom += zoom;
	}
}
