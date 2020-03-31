package pl.ziemniak.grafika.utils.math;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pl.ziemniak.grafika.utils.Object3D;
import pl.ziemniak.grafika.utils.Utils3D;
import pl.ziemniak.grafika.utils.rendering.Camera;
import pl.ziemniak.grafika.utils.rendering.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Prosta reprezentacja trójkąta w przestrzeni 3D
 */
public class Triangle extends Object3D {
	private final Vector a;
	private final Vector b;
	private final Vector c;
	private final Color border;
	/**
	 * Kolor wypełnienia trójkąta, jeżeli null to nie będzie
	 * miał wypełnienia a jedynie krawędzie
	 */
	private final Color fill;

	/**
	 * Tworzy trójkąt o podanych punktach
	 *
	 * @param a      punkt a
	 * @param b      punkt b
	 * @param c      punkt c
	 * @param border Kolor krawędzi.
	 * @param fill   Kolor wypełnienia. Jeżeli null to będzie rysowany jedynie kontur figury
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

	/**
	 * Renederuje trójkąt. Kontur będzie wyrenderowany obowiązkowo, wypełnienie
	 * tylko wtedy, gdy jego kolor nie jest nullem
	 *
	 * @param screen ekran na któ©ym należy wyświetlić dany obiekt
	 * @param zoom   zoom z którym powinna być wyrenderowana figura.
	 * @param d      odległość kamery od rzutnii
	 */
	@Override
	public void render(Screen screen, double zoom, double d) {
		//renderBorder(screen, zoom, d);
		if (fill != null) {
			renderFill(screen, zoom, d);
		}
	}

	private void renderFill(Screen screen, double zoom, double d) {
		if (a.get(2) < 0 && b.get(2) < 0 && c.get(2) < 0) {
			System.out.println("a");
			return;
		}
		GraphicsContext gc = screen.getGraphicsContext2D();
		List<Vector> points;
		if (a.get(2) >= 0) {
			points = calculatePoints(a, b, c);
		} else if (b.get(2) >= 0) {
			points = calculatePoints(b, c, a);
		} else {
			points = calculatePoints(c, a, b);
		}
		double[] xp = new double[4];
		double[] yp = new double[4];

		for (int i = 0; i < points.size(); i++) {
			Vector casted = Utils3D.cast(points.get(i), d);
			double x = casted.get(0) * zoom + (screen.getWidth() / 2);
			double y = (casted.get(1) * zoom * (-1)) + (screen.getHeight() / 2);
			xp[i] = x;
			yp[i] = y;
		}
		gc.setFill(fill);
		gc.fillPolygon(xp, yp, points.size());
	}

	/**
	 * Oblicza punkty, które będą krawędziami wyrerenderowanego trójkąta.
	 * Ponieważ jeden lub 2 z wierzchołków mogą mieć z < 0 zwraca listę albo
	 * 3 albo 4 elementową.
	 *
	 * @param a wierchołek,który ma <b>z >= 0</b>
	 * @param b wierzchołek b, może mieć z < 0
	 * @param c wierzchołek c, może mieć z < 0
	 * @return listę punktów, reprezentującą część trójkąta z z >= 0. Może mieć 3 lub 4 elementy
	 */
	private List<Vector> calculatePoints(Vector a, Vector b, Vector c) {
		List<Vector> points = new ArrayList<>(4);
		points.add(a);
		if (b.get(2) < 0 && c.get(2) < 0) {
			b = Utils3D.cut(b, a);
			c = Utils3D.cut(c, b);
			points.add(b);
			points.add(c);
		} else if (b.get(2) < 0) {
			Vector ab = Utils3D.cut(b, a);
			Vector bc = Utils3D.cut(b, c);
			points.add(ab);
			points.add(bc);
			points.add(c);
		} else if (c.get(2) < 0) {
			Vector bc = Utils3D.cut(c, b);
			Vector ca = Utils3D.cut(c, a);
			points.add(b);
			points.add(bc);
			points.add(ca);
		} else {
			points.add(b);
			points.add(c);
		}
		return points;
	}

	private void renderBorder(Screen screen, double zoom, double d) {
		//Reuse line rendering
		new Line(a, b, border).render(screen, zoom, d);
		new Line(c, b, border).render(screen, zoom, d);
		new Line(a, c, border).render(screen, zoom, d);
	}

	@Override
	public Triangle adjustToCamera(Camera camera) {
		Vector newA = camera.getRotationMatrix().multiply(a.subtract(camera.getCoordinates()));
		Vector newB = camera.getRotationMatrix().multiply(b.subtract(camera.getCoordinates()));
		Vector newC = camera.getRotationMatrix().multiply(c.subtract(camera.getCoordinates()));
		return new Triangle(newA, newB, newC, border, fill);
	}

	@Override
	public double getMaxZ() {
		return Math.max(a.get(2), Math.max(b.get(2), c.get(2)));
	}

	@Override
	public double getMinZ() {
		return Math.min(a.get(2), Math.min(b.get(2), c.get(2)));
	}

	@Override
	public double getMidZ() {
		return (a.get(2) + b.get(2) + c.get(2)) / 3;
	}

	@Override
	public double[] getAllZ() {
		return new double[]{a.get(2), b.get(2), c.get(2)};
	}
}
