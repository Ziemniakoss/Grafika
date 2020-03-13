package pl.ziemniak.grafika.utils.math;

public interface IVector {
	int getLength();
	void set(int index, double value);
	double get(int index);
	boolean isVertical();

	IVector multiply(double b);
	IVector multiply(IMatrix b);
	IVector add(IVector b);
	IVector subtract(IVector b);
}
