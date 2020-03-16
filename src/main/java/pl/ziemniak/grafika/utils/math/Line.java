package pl.ziemniak.grafika.utils.math;

import javafx.scene.paint.Color;

public class Line {
	private final Vector a;
	private final Vector b;
	private Color color;

	public Line(Vector a, Vector b, Color color) {
		this.a = a;
		this.b = b;
		this.color = color;
	}

	public Vector getA() {
		return a;
	}

	public Vector getB() {
		return b;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Line{" +
				"a=" + a +
				", b=" + b +
				", color=" + color +
				'}';
	}
}
