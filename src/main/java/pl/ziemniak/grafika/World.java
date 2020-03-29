package pl.ziemniak.grafika;

import pl.ziemniak.grafika.utils.Object3D;
import pl.ziemniak.grafika.utils.rendering.Camera;

import java.util.Collection;

public class World {
	private final Camera camera;
	private final Collection<Object3D> object3DS;


	public World(Collection<Object3D> object3DS) {
		this.object3DS = object3DS;
		camera = new Camera();
	}

	public Collection<Object3D> getObject3DS() {
		return object3DS;
	}


	public Camera getCamera() {
		return camera;
	}

}
