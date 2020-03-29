package pl.ziemniak.grafika.utils.math;

import javafx.scene.paint.Color;
import pl.ziemniak.grafika.utils.Object3D;
import pl.ziemniak.grafika.utils.rendering.Camera;
import pl.ziemniak.grafika.utils.rendering.Screen;

/**
 * Prosta reprezentacja trójkąta w przestrzeni 3D
 */
public class Triangle extends Object3D {
	private final Vector a;
	private final Vector b;
	private final Vector c;
	private final Color border;
	private final Color fill;

	/**
	 * Tworzy trójkąt o podanych punktach
	 *
	 * @param a punkt a
	 * @param b punkt b
	 * @param c punkt c
	 * @throws NullPointerException jeżeli którykolwiek z punktów będzie nullem
	 */
	public Triangle(Vector a, Vector b, Vector c, Color border, Color fill) {
		if (a == null || b == null || c == null) {
			throw new NullPointerException("Points can't be null");
		}
		this.a = a;
		this.b = b;
		this.c = c;
		this.border = border;
		this.fill = fill;
	}

	public Vector getA() {
		return a;
	}

	public Vector getB() {
		return b;
	}

	public Vector getC() {
		return c;
	}

	@Override
	public void render(Screen screen, double zoom, double d) {
		//todo
	}

	@Override
	public Triangle adjustToCamera(Camera camera) {
		Vector newA = camera.getRotationMatrix().multiply(a.subtract(camera.getCoordinates()));
		Vector newB = camera.getRotationMatrix().multiply(b.subtract(camera.getCoordinates()));
		Vector newC = camera.getRotationMatrix().multiply(c.subtract(camera.getCoordinates()));
		return new Triangle(newA, newB, newC, border, fill);
	}

	@Override
	public double getMaxDepth() {
		return Math.max(a.get(2), Math.max(b.get(2), c.get(2)));
	}
}
