package pl.ziemniak.grafika.utils;

import pl.ziemniak.grafika.utils.rendering.Camera;
import pl.ziemniak.grafika.utils.rendering.IRenderable;

abstract public class Object3D implements IRenderable {
	/**
	 * "Przenosi" obiekt do układu współrzędnych zależnego od kamery.
	 *
	 * @param camera kamera która będzie środkiem nowego układu współrzędnych
	 * @return nowy obiekt odpowiadający przetwarzanaemu w układzie współrzędnych
	 * zależnym od kamery
	 */
	public abstract Object3D adjustToCamera(Camera camera);

	/**
	 * Zwraca największą głębokość punktu figury Niezbędne do posortowania
	 * obiektów dla algorytmu malarza
	 *
	 * @return największą wartość z wszystkich punktów należących do figury
	 */
	public abstract double getMaxDepth();

	public abstract double getMinDepth();
}
