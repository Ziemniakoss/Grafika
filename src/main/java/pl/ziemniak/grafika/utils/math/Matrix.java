package pl.ziemniak.grafika.utils.math;

public class Matrix extends AMatrix {
	private final int columns;
	private final int rows;
	private final double[][] values;

	public Matrix(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		values = new double[rows][columns];
	}

	public Matrix(double[][] values) {
		if (values == null) {
			throw new NullPointerException("Values can't be null");
		}
		if (values.length == 0) {
			throw new IllegalArgumentException("Matrix must have at least one row and column");
		}
		int columns = values[0].length;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null) {
				values[i] = new double[columns];
			} else if (values[i].length != columns) {
				throw new IllegalArgumentException("Matrix is not square");
			}
		}
		this.values = values;
		this.rows = values.length;
		this.columns = columns;
	}

	public double get(int row, int column) {
		checkSize(row, column);
		return values[row][column];

	}

	public void set(int row, int column, double value) {
		checkSize(row, column);
		values[row][column] = value;
	}

	private void checkSize(int rows, int columns) {
		if (rows > this.rows || columns > this.columns) {
			throw new IndexOutOfBoundsException(
					"matrix has size: r=" + this.rows + ", c=" + columns
							+ " but tried to access r=" + rows + ", c=" + columns);
		}
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
}
