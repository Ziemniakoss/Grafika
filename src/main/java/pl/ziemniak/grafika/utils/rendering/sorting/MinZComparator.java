package pl.ziemniak.grafika.utils.rendering.sorting;

/**
 * Porównuje obiekty po najmniejszym z
 */
public class MinZComparator implements DepthComparator {
	@Override
	public int compare(SortableByDepth a, SortableByDepth b) {
		return Double.compare(a.getMinZ(), b.getMinZ());
	}
}
