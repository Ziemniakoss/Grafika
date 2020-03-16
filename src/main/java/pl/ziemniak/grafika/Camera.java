package pl.ziemniak.grafika;

import pl.ziemniak.grafika.utils.math.RotationMatrix;
import pl.ziemniak.grafika.utils.math.Vector;

/**
 * Klasa reprezntująca kamerę. Wszystkie kąty są zapisane w
 * stopniach
 */
public class Camera {
	private final Vector coordinates;
	private double rotationX;
	private double rotationY;
	private double rotationZ;

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

	public void rotateX(double degrees) {
		setRotationX(rotationX + degrees);
	}

	public void rotateY(double degrees) {
		setRotationY(rotationY + degrees);
	}

	public void rotateZ(double degrees) {
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

	public double getRotationX() {
		return rotationX;
	}

	public void setRotationX(double rotationX) {
		this.rotationX = rotationX % 360;
	}

	public double getRotationY() {
		return rotationY;
	}

	public void setRotationY(double rotationY) {
		this.rotationY = rotationY % 360;
	}

	public double getRotationZ() {
		return rotationZ;
	}

	public void setRotationZ(double rotationZ) {
		this.rotationZ = rotationZ %360;
	}

	public void moveForward(double delta) {
		double radians = Math.toRadians(rotationY);
		setX(getX() + Math.sin(radians) * delta);
		setZ(getZ() + Math.cos(radians) * delta);

	}

	/**
	 * Pójdź w lewo lub prawo o deltę
	 *
	 * @param delta jeżeli dodatnia to w prawo a jeżeli ujemna w lewo
	 */
	public void moveToRight(double delta) {
		double radians = Math.toRadians(rotationY);
		setZ(getZ() + delta * Math.sin(radians));
		setX(getX() + delta * Math.cos(radians));

	}
}
