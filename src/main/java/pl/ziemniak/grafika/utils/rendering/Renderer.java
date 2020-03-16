package pl.ziemniak.grafika.utils.rendering;

import pl.ziemniak.grafika.Camera;
import pl.ziemniak.grafika.utils.math.Line;
import pl.ziemniak.grafika.utils.math.Vector;

public class Renderer implements IRenderer {
	private final Screen screen;
	private final Camera camera;

	public Renderer(Screen screen, Camera camera) {
		this.screen = screen;
		this.camera = camera;
	}

	@Override
	public void render(Line line) {
		Vector aa = transform(line.getA());
		Vector bb = transform(line.getB());
		screen.drawLine(aa.get(0), aa.get(1), bb.get(0), bb.get(1), 2, line.getColor());//todo wyliczanie grubosci
	}

	private Vector transform(Vector v) {
		//todo
		return new Vector(true, v.get(0),v.get(1));
	}
}
