package pl.ziemniak.grafika.utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Zmodyfikowana Canvas. Środek układu współrzędnych jest
 * umieszczony po środku Canavasa. Co więcej pionowa oś y ma
 * wartości rosnące do góry a nie do dołu jak w klasycznej Canvie
 */
public class Screen extends Canvas {
	private double zoom = 1;
	private final static Color BACKGROUND_COLOR = Color.BLACK;

	public Screen() {
	}

	public Screen(double width, double height) {
		super(width, height);
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public void addToZoom(double zoom) {
		this.zoom += zoom;
	}

	public void drawLine(double x1, double y1, double x2, double y2, double thiccness, Color color) {
		var gc = getGraphicsContext2D();
		gc.setLineWidth(thiccness * zoom);
		x1 += getWidth() / 2.0 * zoom;
		x2 += getWidth() / 2.0 * zoom;
		y1 = getHeight() / 2 - y1 * zoom;
		y2 = getHeight() / 2 - y2 * zoom;
		gc.setStroke(color);
		gc.strokeLine(x1, y1, x2, y2);
	}

	public void clear() {

	}
}
