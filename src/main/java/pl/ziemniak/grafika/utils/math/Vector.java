package pl.ziemniak.grafika.utils.math;

import java.util.Arrays;

public class Vector {
	public final double[] values;

	public Vector(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than 0");
		}
		values = new double[length];
	}

	public Vector(double... values) {
		if (values == null) {
			throw new NullPointerException("Values can't be null");
		}
		if (values.length == 0) {
			throw new IllegalArgumentException("Length must be grater than 0");
		}
		this.values = values;

	}

	public void set(int field, double value) {
		this.values[field] = value;
	}

	public double get(int field) {
		return this.values[field];
	}

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

	public Vector multiply(double a) {
		double[] result = values.clone();
		for (int i = 0; i < result.length; i++) {
			result[i] *= a;
		}
		return new Vector(result);
	}

	public Vector multiply(Vector v) {
		checkVector(v);
		double[] result = values.clone();
		for (int i = 0; i < result.length; i++) {
			result[i] = values[i] * v.values[i];
		}
		return new Vector(result);
	}

	public Vector normalize() {
		double magnitude = magnitude();
		if(magnitude > 0) {
			return new Vector(Arrays.stream(values).map(e -> e / magnitude).toArray());
		}
		return new Vector(values.clone());
	}

	public double magnitude() {
		return Math.sqrt(Arrays.stream(values).parallel().map(e -> e * e).sum());
	}

	public Vector add(Vector v) {
		checkVector(v);
		double[] result = values.clone();
		for (int i = 0; i < values.length; i++) {
			result[i] = v.values[i] + values[i];
		}
		return new Vector(result);
	}

	private void checkVector(Vector v) {
		if (v == null) {
			throw new NullPointerException("Vector can't be null");
		}
		if (v.getLength() != getLength()) {
			throw new IllegalArgumentException("Sizes don't match");
		}
	}
}
