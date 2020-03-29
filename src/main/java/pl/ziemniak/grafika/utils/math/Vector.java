package pl.ziemniak.grafika.utils.math;


import java.util.Arrays;

public class Vector implements IVector {
	private final double[] values;
	private final boolean vertical;

	public Vector(boolean vertical, int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than 0");
		}
		values = new double[length];
		this.vertical = vertical;
	}

	public Vector(boolean vertical, double... values) {
		if (values == null) {
			throw new NullPointerException("Values can't be null");
		}
		if (values.length == 0) {
			throw new IllegalArgumentException("Length must be grater than 0");
		}
		this.values = values;
		this.vertical = vertical;
	}

	@Override
	public void set(int index, double value) {
		this.values[index] = value;
	}

	@Override
	public double get(int field) {
		return this.values[field];
	}

	@Override
	public boolean isVertical() {
		return vertical;
	}

	@Override
	public int getLength() {
		return values.length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(values[0]);
		for (int i = 1; i < values.length; i++) {
			sb.append(", ").append(values[i]);
		}
		sb.append(']');
		return sb.toString();
	}

	@Override
	public Vector multiply(double a) {
		double[] result = values.clone();
		for (int i = 0; i < result.length; i++) {
			result[i] *= a;
		}
		return new Vector(vertical, result);
	}

	@Override
	public Vector multiply(IMatrix b) {
		if (b == null) {
			throw new NullPointerException("matrix can't be null");
		}
		return vertical ? multiplyVertical(b) : multiplyHorizontal(b);
	}

	private Vector multiplyVertical(IMatrix b) {
		if (getLength() != b.getColumns()) {
			throw new IllegalArgumentException("illegal amount of rows in matrix");
		}
		double[] result = new double[getLength()];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i] += get(j) * b.get(j, i);
			}
		}
		return new Vector(true, result);
	}

	private Vector multiplyHorizontal(IMatrix b) {
		if (b.getRows() != 1) {
			throw new IllegalArgumentException("Illegal amount of rows in matrix");
		}
		double[] result = new double[getLength()];
		for (int i = 0; i < result.length; i++) {
			result[i] = get(i) * b.get(0, i);
		}
		return new Vector(false, result);

	}

	@Override
	public Vector add(IVector b) {
		checkVector(b);
		double[] result = values.clone();
		for (int i = 0; i < values.length; i++) {
			result[i] = b.get(i) + values[i];
		}
		return new Vector(vertical, result);
	}

	@Override
	public Vector subtract(IVector b) {
		checkVector(b);
		double[] result = values.clone();
		for (int i = 0; i < values.length; i++) {
			result[i] -= b.get(i);
		}
		return new Vector(vertical, result);
	}

	private void checkVector(IVector v) {
		if (v == null) {
			throw new NullPointerException("Vector can't be null");
		}
		if (v.getLength() != getLength()) {
			throw new IllegalArgumentException("Sizes don't match");
		}
		if (vertical != v.isVertical()) {
			throw new IllegalArgumentException("Both vectors must have same orientation");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vector vector = (Vector) o;
		return vertical == vector.vertical && Arrays.equals(values, vector.values);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(values);
	}
}
