package pl.ziemniak.grafika.utils.rendering.sorting;

/**
 * Pozwala na różne rodzaje sortowania bo Z
 */
public interface SortableByDepth {
	/**
	 * Zwraca największą wartośc z z wszystkich punktów figury
	 *
	 * @return wartość z najdlaszego punktu od środka układu współrzędnych
	 */
	double getMaxZ();

	/**
	 * Zwraca wartość najmniejszą wartość z wszystkich punktów figury
	 *
	 * @return wartość z najbliższego punktu środka układu współrzędnych
	 */
	double getMinZ();

	/**
	 * Zwraca wartość z dla środka figury. Zazwyczaj jest to średnie z
	 * wszsytkich punktów
	 *
	 * @return wartość z dla środka figury
	 */
	double getMidZ();

	double[] getAllZ();
}
