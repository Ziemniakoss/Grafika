package pl.ziemniak.grafika.utils.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.transform.Source;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

	@ParameterizedTest
	@MethodSource("streamForAddingTest")
	void addVector(Vector a, Vector b, Vector expected) {
		Vector result = a.add(b);
		assertEquals(expected, a.add(b));

	}

	@ParameterizedTest
	@MethodSource("streamForMatrixMultiplication")
	void multiplyByMatrix(Vector v, IMatrix x, Vector expected) {
		Vector result = v.multiply(x);
		assertEquals(expected, result);

	}

	private static Stream<Arguments> streamForMatrixMultiplication() {
		return Stream.of(
				Arguments.of(
						new Vector(false,3,1,2),
						new Matrix(new double[][]{
								{1,1,2},
								{2,1,3},
								{1,4,2}
						}),
						new Vector(false,7,12,13)
				)
		);
	}

	//todo odjemownie, błędne dodawanie

	private static Stream<Arguments> streamForAddingTest() {
		return Stream.of(
				Arguments.of(
						new Vector(true, 10, 9, -200),
						new Vector(true, 5, 20, 100),
						new Vector(true, 15, 29, -100)
				), Arguments.of(
						new Vector(true, 0, 0, 0),
						new Vector(true, 0, 0, 0),
						new Vector(true, 0, 0, 0)
				), Arguments.of(
						new Vector(false, 209.1, 14.7195, -89.101),
						new Vector(false, 0, 0, 0),
						new Vector(false, 209.1, 14.7195, -89.101)
				), Arguments.of(
						new Vector(false, 0, 0, 0),
						new Vector(false, 209.1, 14.7195, -89.101),
						new Vector(false, 209.1, 14.7195, -89.101)
				));
	}

}