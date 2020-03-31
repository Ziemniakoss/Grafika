package pl.ziemniak.grafika.utils.rendering.sorting;

/**
 * Porównuje po największym z
 */
public class MaxZComparator implements DepthComparator {
	@Override
	public int compare(SortableByDepth a, SortableByDepth b) {
		return Double.compare(a.getMaxZ(), b.getMaxZ());
	}
}
