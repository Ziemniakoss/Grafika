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
				{0, cos, -sin},
				{0, sin, cos}
		});
	}

	public static RotationMatrix rotationMatrixY(double degrees) {
		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		return new RotationMatrix(new double[][]{
				{cos, 0, sin},
				{0, 1, 0},
				{-sin, 0, cos}
		});
	}

	public static RotationMatrix rotationMatrixZ(double degrees) {
		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		return new RotationMatrix(new double[][]{
				{cos, -sin, 0},
				{sin, cos, 0},
				{0, 0, 1}
		});
	}


	public static RotationMatrix rotationMatrixXYZ(double degreesX, double degreesY, double degreesZ) {
		double radX = Math.toRadians(degreesX);
		double sinX = Math.sin(radX);
		double cosX = Math.cos(radX);

		double radY = Math.toRadians(degreesY);
		double sinY = Math.sin(radY);
		double cosY = Math.cos(radY);

		double radZ = Math.toRadians(degreesZ);
		double sinZ = Math.sin(radZ);
		double cosZ = Math.cos(radZ);

		double[][] result = new double[3][3];
		result[0][0] = cosY * cosZ;
		result[0][1] = -cosY * sinZ;
		result[0][2] = sinY;

		result[1][0] = sinX * sinY * cosZ + cosX * sinZ;
		result[1][1] = -sinX * sinY * sinZ + cosX * cosZ;
		result[1][2] = -sinX * cosY;

		result[2][0] = -sinY * cosX * cosZ + sinX * sinZ;
		result[2][1] = sinY * cosX * sinZ + sinX * cosZ;
		result[2][2] = cosX * cosY;
		return new RotationMatrix(result);
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
