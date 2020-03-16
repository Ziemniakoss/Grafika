package pl.ziemniak.grafika.utils.math;

public class RotationMatrix extends AMatrix {
	private final double[][] values;

	private RotationMatrix(double[][] values) {
		this.values = values;
	}

	public static RotationMatrix rotationMatrixX(double degrees) {
		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		return new RotationMatrix(new double[][]{
				{1, 0, 0},
				{0, cos, sin},
				{0, -sin, cos}
		});
	}

	public static RotationMatrix rotationMatrixY(double degrees) {
		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		return new RotationMatrix(new double[][]{
				{cos, 0, -sin},
				{0, 1, 0},
				{sin, 0, cos}
		});
	}

	public static RotationMatrix rotationMatrixZ(double degrees) {
		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		return new RotationMatrix(new double[][]{
				{cos, sin, 0},
				{-sin, cos, 0},
				{0, 0, 1}
		});
	}


	public static AMatrix rotationMatrixXYZ(double degreesX, double degreesY, double degreesZ) {
		return rotationMatrixX(degreesX).multiply(rotationMatrixY(degreesY)).multiply(rotationMatrixZ(degreesZ));//todo jakoś ładniej
	}


	@Override
	public int getColumns() {
		return 3;
	}

	@Override
	public int getRows() {
		return 3;
	}

	@Override
	public double get(int row, int column) {
		return values[row][column];
	}

	@Override
	public void set(int row, int column, double value) {
		throw new UnsupportedOperationException("Can't change values of rotation matrix");
	}
}
