package pl.ziemniak.grafika;

import pl.ziemniak.grafika.utils.math.Line;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class World {
	private final static World instance = new World();
	private final Camera camera;
	private final List<Line> lines;

	public static World getInstance() {
		return instance;
	}

	public void addLine(Line line) {
		if (line != null) {
			lines.add(line);
		}
	}

	public void addAllLines(Collection<Line> lines){
		this.lines.addAll(lines);
	}

	private World() {
		this.camera = new Camera();
		this.lines = new ArrayList<>();
	}

	public Camera getCamera() {
		return camera;
	}

	public List<Line> getLines() {
		return lines;
	}
}
