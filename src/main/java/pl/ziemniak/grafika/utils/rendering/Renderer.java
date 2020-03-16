package pl.ziemniak.grafika.utils.rendering;

import pl.ziemniak.grafika.Camera;
import pl.ziemniak.grafika.utils.math.Line;
import pl.ziemniak.grafika.utils.math.RotationMatrix;
import pl.ziemniak.grafika.utils.math.Vector;

/**
 * https://en.wikipedia.org/wiki/3D_projection
 */
public class Renderer implements IRenderer {
	private final Screen screen;
	private final Camera camera;
	private double d = -40;

	public Renderer(Screen screen, Camera camera) {
		this.screen = screen;
		this.camera = camera;
	}

	@Override
	public void render(Line line) {
		Vector aa = transformAndRotate(line.getA());
		Vector bb = transformAndRotate(line.getB());
		screen.drawLine(aa.get(0), aa.get(1), bb.get(0), bb.get(1), 2, line.getColor());//todo wyliczanie grubosci
	}

	private Vector transformAndRotate(Vector v) {
		Vector translated = v.subtract(camera.getCoordinates());
		Vector rotated = RotationMatrix.rotationMatrixXYZ(camera.getRotationX(), camera.getRotationY(), camera.getRotationZ()).multiply(translated);
		double x = rotated.get(0) * (d / rotated.get(2));
		double y = rotated.get(1) * (d / rotated.get(2));
		return new Vector(true, x, y);
	}

	/**
	 * Przycianamy tak, żeby nie było ujemnego z
	 * @param p1 wektor o ujemnym z
	 * @param p2 wektor o dodatnim z
	 * @return
	 */
	private Vector cut(Vector p1, Vector p2) {
		//definiujemy prostą 3d przez punkt i wektor
		System.out.println("thniemy "+p1+p2);
		Vector v = p2.subtract(p1);
		double a = p2.get(2) / (p1.get(2) - p2.get(2));
		double x = p2.get(0) + a *(p2.get(0) - p1.get(0));
		double y = p2.get(1) + a *(p2.get(1) - p1.get(1));
		System.out.println(x+" ");
		return new Vector(true,x,y,0);

	}
}
