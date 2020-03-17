package pl.ziemniak.grafika.utils.rendering;

import pl.ziemniak.grafika.utils.math.Line;
import pl.ziemniak.grafika.utils.math.RotationMatrix;
import pl.ziemniak.grafika.utils.math.Vector;

/**
 * https://en.wikipedia.org/wiki/3D_projection
 */
public class Renderer implements IRenderer {
	private final Screen screen;
	private final Camera camera;
	private double d = -400;

	public Renderer(Screen screen, Camera camera) {
		this.screen = screen;
		this.camera = camera;
	}

	@Override
	public void render(Line line) {
		Vector aa = transformAndRotate(line.getA());
		Vector bb = transformAndRotate(line.getB());
		if(aa.get(2) < 0 || bb.get(2) < 0)
			return;
		if(aa.get(2) < d){
			aa = cut(aa,bb);
		}else if(bb.get(2) < d){
			bb = cut(bb,aa);
		}
		screen.drawLine(aa.get(0) * camera.getZoom(), aa.get(1)* camera.getZoom(), bb.get(0)* camera.getZoom(), bb.get(1)* camera.getZoom(), 2* camera.getZoom(), line.getColor());//todo wyliczanie grubosci
	}

	private Vector transformAndRotate(Vector v) {
		Vector translated = v.subtract(camera.getCoordinates());
		Vector rotated = RotationMatrix.rotationMatrixXYZ(camera.getRotationX(), camera.getRotationY(), camera.getRotationZ()).multiply(translated);
		double x = rotated.get(0) * (d / rotated.get(2));
		double y = rotated.get(1) * (d / rotated.get(2));
		return new Vector(true, x, y, rotated.get(2));
	}

	/**
	 * Przycianamy tak, żeby nie było ujemnego z
	 * @param p1 wektor o ujemnym z
	 * @param p2 wektor o dodatnim z
	 * @return
	 */
	private Vector cut(Vector p1, Vector p2) {
		System.out.println("Cuttint "+p1);
		//definiujemy prostą 3d przez punkt i wektor
		Vector v = p2.subtract(p1);
		double a = (d- p2.get(2)) / v.get(2);
		double x = p2.get(0) + a * v.get(0);
		double y = p2.get(1) + a * v.get(1);
		return new Vector(true,x,y,d);

	}
}
