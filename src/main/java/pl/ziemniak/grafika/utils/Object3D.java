package pl.ziemniak.grafika.utils;

import pl.ziemniak.grafika.utils.rendering.Camera;
import pl.ziemniak.grafika.utils.rendering.IRenderable;
import pl.ziemniak.grafika.utils.rendering.sorting.SortableByDepth;

abstract public class Object3D implements IRenderable, SortableByDepth {
	/**
	 * "Przenosi" obiekt do układu współrzędnych zależnego od kamery.
	 *
	 * @param camera kamera która będzie środkiem nowego układu współrzędnych
	 * @return nowy obiekt odpowiadający przetwarzanaemu w układzie współrzędnych
	 * zależnym od kamery
	 */
	public abstract Object3D adjustToCamera(Camera camera);
}
