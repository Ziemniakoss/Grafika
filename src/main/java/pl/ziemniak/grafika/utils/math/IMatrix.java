package pl.ziemniak.grafika.utils.math;

public interface IMatrix {
	int getColumns();
	int getRows();
	double get(int row, int column);
	void set(int row, int column, double value);

	IMatrix multiply(IMatrix b);
	IMatrix multiply(double b);
	Vector multiply(IVector b);
	IMatrix subtract(IMatrix b);
	IMatrix add(IMatrix b);
}
