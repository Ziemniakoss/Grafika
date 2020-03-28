package pl.ziemniak.grafika.utils;

import pl.ziemniak.grafika.utils.math.Vector;

public final class Utils3D {

	/**
	 * Zmienia wektor p1 w taki sposób, żeby:
	 * <ul>
	 *     <li>Dalej leżał na prostej przechodzącej przez punkty p1 i p2</li>
	 *     <li>Nie miał z o wartości mniejszej niż 0</li>
	 * </ul>
	 * Obydwa wektroy muszą być<b> trójelementowe</b>, poniważ
	 * przycięcie przeprowadzane jest dla przestrzeni 3D
	 *
	 * @param p1 trzyelementowy wektor o ujemnym z
	 * @param p2 trzyelementowy wektor o dodatnim z
	 * @return Wektor reprzentujący punk leżący
	 * na prostej przechodzącej przez punkty
	 * p1 i p2 oraz o z = 0
	 * @throws NullPointerException     gdy jeden z wektrów jest nullem
	 * @throws IllegalArgumentException gdy wektroy nie są 3 elementowe
	 */
	public static Vector cut(Vector p1, Vector p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException("Vecotrs can't be null");
		}
		if (p1.getLength() != 3 || p2.getLength() != 3) {
			throw new IllegalArgumentException("vecotrs must have 3 elements");
		}
		//wyznaczamy rownanie prostej 3d r(t) = p1 + a(p2-p1)
		double a = -p1.get(2) / (p2.get(2) - p1.get(2));
		double newX = p1.get(0) + a * (p2.get(0) - p1.get(0));
		double newY = p1.get(1) + a * (p2.get(1) - p1.get(1));
		return new Vector(true, newX, newY, 0);
	}

	/**
	 * Rzutuje perspektywistycznie vektor 3D na wektor 2D
	 *
	 * @param v wektor reprezentujący punkt 3D
	 * @param d odległość kamery od rzutnii
	 * @return rzut perspektywistyczny wektora v na przestrzeń 2D
	 */
	public static Vector cast(Vector v, double d) {
		if (v == null) {
			throw new NullPointerException("Vector can't be null");
		}
		if (v.getLength() != 3) {
			throw new IllegalArgumentException("Vector must have 3 elements, recoved " + v.getLength());
		}
		double divider = v.get(2) == 0 ? 1 : v.get(2);
		double x = v.get(0) * (d / divider);
		double y = v.get(1) * (d / divider);
		return new Vector(true, x, y);
	}
}
