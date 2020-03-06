package pl.ziemniak.grafika.utils.math;

public class Vector {
	public final double [] values;

	public Vector(int length) {
		if(length <= 0){
			throw new IllegalArgumentException("Length must be greater than 0");
		}
		values = new double[length];
	}

	public Vector(double ... values){
		if(values == null){
			throw new NullPointerException("Values can't be null");
		}
		if(values.length == 0){
			throw new IllegalArgumentException("Length must be grater than 0");
		}
		this.values = values;

	}

	public void set(int field, double value){
		this.values[field] = value;
	}

	public double get(int field){
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
		for(int i = 1; i < values.length; i++){
			sb.append(", ").append(values[i]);
		}
		sb.append(']');
		return sb.toString();
	}
}
