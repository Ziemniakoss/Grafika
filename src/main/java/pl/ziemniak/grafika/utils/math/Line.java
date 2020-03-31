package pl.ziemniak.grafika.utils.math;

import javafx.scene.paint.Color;
import pl.ziemniak.grafika.utils.Object3D;
import pl.ziemniak.grafika.utils.Utils3D;
import pl.ziemniak.grafika.utils.rendering.Camera;
import pl.ziemniak.grafika.utils.rendering.Screen;

public class Line extends Object3D {
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

	@Override
	public Line adjustToCamera(Camera camera) {
		Vector newA = camera.getRotationMatrix().multiply(a.subtract(camera.getCoordinates()));
		Vector newB = camera.getRotationMatrix().multiply(b.subtract(camera.getCoordinates()));
		return new Line(newA, newB, color);
	}


	@Override
	public void render(Screen screen, double zoom, double d) {
		if (a.get(2) < 0 && b.get(2) < 0) {
			return;
		}
		Vector aa = a;
		Vector bb = b;
		if (aa.get(2) < 0) {
			aa = Utils3D.cut(aa, bb);
		} else if (bb.get(2) < 0) {
			bb = Utils3D.cut(bb, aa);
		}
		aa = Utils3D.cast(aa, d);
		bb = Utils3D.cast(bb, d);
		screen.drawLine(aa.get(0) * zoom, aa.get(1) * zoom,
				bb.get(0) * zoom, bb.get(1) * zoom, 2 * zoom, color);
	}

	@Override
	public double getMaxZ() {
		return Math.max(a.get(2), b.get(2));
	}

	@Override
	public double getMinZ() {
		return Math.min(a.get(2), b.get(2));
	}

	@Override
	public double getMidZ() {
		return (a.get(2) + b.get(2)) / 2;
	}

	@Override
	public double[] getAllZ() {
		return new double[]{a.get(2), b.get(2)};
	}
}
