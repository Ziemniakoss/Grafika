package pl.ziemniak.grafika.utils.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AMatrixTest {

	@ParameterizedTest
	@MethodSource("provideMatrixes")
	void multiplyMatrixes(AMatrix a, AMatrix b, AMatrix expected) {
		AMatrix result = a.multiply(b);
		assertEquals(expected, result);
	}


	@ParameterizedTest
	@MethodSource("streamForMatrixVectorMultiplication")
	void multiplyByVector(AMatrix a, IVector v, IVector expected) {
		IVector result = a.multiply(v);
		assertEquals(expected, result);
	}


	private static Stream<Arguments> streamForMatrixVectorMultiplication() {
		return Stream.of(
				Arguments.of(
						new Matrix(new double[][]{
								{1, 1, 2},
								{2, 1, 3},
								{1, 4, 2}
						}),
						new Vector(true, 3, 1, 2),
						new Vector(true, 8, 13, 11)
				)
		);
	}

	private static Stream<Arguments> provideMatrixes() {
		return Stream.of(
				Arguments.of(
						new Matrix(new double[][]{
								{-1, 4},
								{2, 3}}),
						new Matrix(new double[][]{
								{9, -3},
								{6, 1}}),
						new Matrix(new double[][]{
								{15, 7},
								{36, -3}})
				), Arguments.of(
						new Matrix(new double[][]{
								{1, 2},
								{3, 4}}),
						new Matrix(new double[][]{
								{5, 6},
								{7, 8}}),
						new Matrix(new double[][]{
								{19, 22},
								{43, 50}})
				), Arguments.of(
						new Matrix(new double[][]{
								{2, 0, 1},
								{2, 1, 3}}),
						new Matrix(new double[][]{
								{1, 0},
								{3, -2},
								{2, 4}}),
						new Matrix(new double[][]{
								{4, 4},
								{11, 10}
						})
				), Arguments.of(
						new Matrix(new double[][]{
								{1, 2}
						}),
						new Matrix(new double[][]{
								{3, 1},
								{0, 1}
						}),
						new Matrix(new double[][]{
								{3, 3}
						})
				)
		);
	}

}