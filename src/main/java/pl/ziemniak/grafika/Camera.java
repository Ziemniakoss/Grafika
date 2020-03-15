package pl.ziemniak.grafika;

import pl.ziemniak.grafika.utils.math.Vector;

/**
 * Klasa reprezntująca kamerę. Wszystkie kąty są zapisane w
 * stopniach
 */
public class Camera {
	private final Vector coordinates;
	private int rotationX;
	private int rotationY;
	private int rotationZ;

	public Camera() {
		coordinates = new Vector(true, 0, 0, 0);
	}

	public double getX() {
		return coordinates.get(0);
	}

	public double getY() {
		return coordinates.get(1);
	}

	public double getZ() {
		return coordinates.get(2);
	}

	public void setX(double x) {
		coordinates.set(0, x);
	}

	public void setY(double y) {
		coordinates.set(1, y);
	}

	public void setZ(double z) {
		coordinates.set(2, z);
	}

	public void rotateX(int degrees) {
		setRotationX(rotationX + degrees);
	}

	public void rotateY(int degrees) {
		setRotationY(rotationY + degrees);
	}

	public void rotateZ(int degrees) {
		setRotationZ(rotationZ + degrees);
	}

	/**
	 * Zwraca wektor zawierający koordynaty kamery. Kolejne elementy wektora to:
	 * <ul>
	 *     <li>0 -> x</li>
	 *     <li>1 -> y</li>
	 *     <li>2 -> z</li>
	 * </ul>
	 *
	 * @return wektor z współrzednymi kamery
	 */
	public Vector getCoordinates() {
		return coordinates;
	}

	public int getRotationX() {
		return rotationX;
	}

	public void setRotationX(int rotationX) {
		this.rotationX = rotationX;
	}

	public int getRotationY() {
		return rotationY;
	}

	public void setRotationY(int rotationY) {
		this.rotationY = rotationY;
	}

	public int getRotationZ() {
		return rotationZ;
	}

	public void setRotationZ(int rotationZ) {
		this.rotationZ = rotationZ;
	}
}
