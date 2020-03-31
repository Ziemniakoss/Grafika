package pl.ziemniak.grafika.utils.rendering.sorting;

/**
 * Najpierw porównuje po najmniejszym z.
 * Gdy dwa obiekty mają takie samo najmniejsze z, porównuje po największym z
 */
public class MinMaxZComparator implements DepthComparator {
	@Override
	public int compare(SortableByDepth a, SortableByDepth b) {
		double aMin = a.getMinZ();
		double bMin = b.getMinZ();
		if (aMin == bMin) {
			double amax = a.getMaxZ();
			double bMax = b.getMaxZ();
			return Double.compare(amax, bMax);
		} else if (aMin > bMin) {
			return 1;
		} else {
			return -1;
		}
	}
}
