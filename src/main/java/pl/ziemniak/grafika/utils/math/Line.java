package pl.ziemniak.grafika.utils.math;

import javafx.scene.paint.Color;

public class Line {
	private final Point a;
	private final Point b;
	private Color color;

	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}
}
