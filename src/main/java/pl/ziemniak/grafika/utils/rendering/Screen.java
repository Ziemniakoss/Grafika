package pl.ziemniak.grafika.utils.rendering;

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
		this(0,0);
	}

	public Screen(double width, double height) {
		super(width, height);
		setFocusTraversable(true);
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public void addToZoom(double zoom) {
		this.zoom += zoom;
	}

	public void drawLine(double x1, double y1, double x2, double y2, double thiccness, Color color) {
		var gc = getGraphicsContext2D();
		x1 *= zoom;
		x2 *= zoom;
		y1 *= zoom;
		y2 *= zoom;
		gc.setLineWidth(thiccness * zoom);
		x1 += getWidth() / 2.0 ;
		x2 += getWidth() / 2.0 ;
		y1 = getHeight() / 2 - y1 ;
		y2 = getHeight() / 2 - y2 ;
		gc.setStroke(color);
		gc.strokeLine(x1, y1, x2, y2);
	}

	public void clear() {
		var gc = getGraphicsContext2D();
		gc.setFill(BACKGROUND_COLOR);
		gc.fillRect(0,0,getWidth(),getHeight());
	}

	public double getZoom() {
		return zoom;
	}
}
