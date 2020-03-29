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
		line.adjustToCamera(camera).render(screen,camera.getZoom(),d);
//		Vector aa = transformAndRotate(line.getA());
//		Vector bb = transformAndRotate(line.getB());
//		double x1 = aa.get(0) * (d / aa.get(2));
//		double y1 = aa.get(1) * (d / aa.get(2));
//		double x2 = bb.get(0) * (d / bb.get(2));
//		double y2 = bb.get(1) * (d / bb.get(2));
//		if (aa.get(2) < 0 && bb.get(2) < 0){
//			return;
//		}
//		if (aa.get(2) < 0) {
//			cut(aa, bb);
//			x1 = aa.get(0) * d;
//			y1 = aa.get(1) * d;
//		} else if (bb.get(2) < 0) {
//			cut(bb, aa);
//
//			x2 = bb.get(0) * d;
//			y2 = bb.get(1) * d;
//		}
//
//		screen.drawLine(x1 * camera.getZoom(), y1 * camera.getZoom(),
//				x2 * camera.getZoom(), y2 * camera.getZoom(),
//				2 * camera.getZoom(), line.getColor());//todo wyliczanie grubosci
	}

	private Vector transformAndRotate(Vector v) {
		Vector translated = v.subtract(camera.getCoordinates());
		Vector rotated = RotationMatrix.rotationMatrixXYZ(-camera.getRotationX(), -camera.getRotationY(), -camera.getRotationZ()).multiply(translated);
		return rotated;
	}

	/**
	 * Przycianamy tak, żeby nie było ujemnego z
	 *
	 * @param p1 wektor o ujemnym z
	 * @param p2 wektor o dodatnim z
	 * @return
	 */
	private void cut(Vector p1, Vector p2) {
		//wyznaczamy rownanie prostej 3d r(t) = p1 + a(p2-p1)
		double a = -p1.get(2) / (p2.get(2) - p1.get(2));
		double newX = p1.get(0) + a * (p2.get(0) - p1.get(0));
		double newY = p1.get(1) + a * (p2.get(1) - p1.get(1));
		p1.set(0, newX);
		p1.set(1, newY);
		p1.set(2, 0);
	}


}
