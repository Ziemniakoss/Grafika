package pl.ziemniak.grafika.utils.math;

public abstract class AMatrix implements IMatrix {

	@Override
	public Matrix multiply(IMatrix b) {
		checkIfCanMultiply(b);
		double[][] result = new double[getRows()][b.getColumns()];
		for (int x = 0; x < result[0].length; x++) {
			for (int y = 0; y < result.length; y++) {
				for (int i = 0; i < getColumns(); i++) {
					result[y][x] += get(y, i) * b.get(i, x);
				}
			}
		}
		return new Matrix(result);
	}

	@Override
	public Vector multiply(IVector b) {
		checkIfCanMultiply(b);
		double[] result = new double[b.getLength()];
		for (int i = 0; i < result.length; i++) {
			for (int k = 0; k < result.length; k++) {
				result[i] += b.get(k) * get(i, k);
			}
		}
		return new Vector(b.isVertical(), result);
	}

	@Override
	public IMatrix multiply(double b) {
		Matrix result = new Matrix(getColumns(), getRows());
		for (int x = 0; x < result.getColumns(); x++) {
			for (int y = 0; y < result.getRows(); y++) {
				result.set(y, x, get(y, x) * b);
			}
		}
		return result;
	}

	@Override
	public IMatrix subtract(IMatrix b) {
		if (getColumns() != b.getColumns() || getRows() != b.getRows()) {
			throw new IllegalArgumentException("Sizes does't match");
		}
		IMatrix result = new Matrix(getColumns(), getRows());
		for (int row = 0; row < getRows(); row++) {
			for (int column = 0; column < getColumns(); column++) {
				result.set(row, column, get(row, column) - b.get(row, column));
			}
		}
		return result;
	}

	@Override
	public IMatrix add(IMatrix b) {
		if (getColumns() != b.getColumns() || getRows() != b.getRows()) {
			throw new IllegalArgumentException("Sizes does't match");
		}
		IMatrix result = new Matrix(getColumns(), getRows());
		for (int row = 0; row < getRows(); row++) {
			for (int column = 0; column < getColumns(); column++) {
				result.set(row, column, get(row, column) - b.get(row, column));
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IMatrix))
			return false;
		IMatrix b = (IMatrix) obj;
		if (getRows() != b.getRows() || getColumns() != b.getColumns()) {
			return false;
		}
		for (int row = 0; row < getRows(); row++) {
			for (int column = 0; column < getColumns(); column++) {
				if (get(row, column) != b.get(row, column)) {
					//todo System.out.println(column+" "+row+" expected " +get(row,column)+", got "+b.get(row,column));
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < getRows(); row++) {
			sb.append(get(row, 0));
			for (int column = 1; column < getColumns(); column++) {
				sb.append(' ');
				sb.append(get(row, column));
			}
			sb.append('\n');
		}

		return sb.toString();
	}

	private void checkIfCanMultiply(IMatrix b) {
		if (b == null) {
			throw new NullPointerException("Matrix to mutliply by can't be null");
		}
		if (b.getRows() != getColumns()) {
			throw new IllegalArgumentException("Illegal size of matrix to multiply, (first)col " + getColumns() + " (second)rows " + b.getRows());
		}
	}

	private void checkIfCanMultiply(IVector v) {
		if(v == null){
			throw new NullPointerException("Vector to multiply by can't be null");
		}
		if(v.isVertical() && getColumns() != v.getLength()){
			throw new IllegalArgumentException("Vertical vector of illegal length, expecting: "+getColumns()+" got "+v.getLength());
		}
		if(!v.isVertical() && getColumns() != 1){
			throw new IllegalArgumentException("Matrix must have 1 row to be able to be multiplied by vector");
		}

	}
}
