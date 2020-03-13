package pl.ziemniak.grafika;

import pl.ziemniak.grafika.utils.math.Vector;

public class Camera {
	private final Vector coordinates;
	private int rotationX;
	private int rotationY;
	private int rotationZ;

	public Camera() {
		coordinates = new Vector(true,0,0,0);
	}

	public double getX(){
		return coordinates.get(0);
	}

	public double getY(){
		return coordinates.get(1);
	}

	public double getZ(){
		return coordinates.get(2);
	}

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
