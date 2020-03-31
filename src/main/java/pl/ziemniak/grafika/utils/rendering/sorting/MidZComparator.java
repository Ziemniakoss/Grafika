package pl.ziemniak.grafika.utils.rendering.sorting;

/**
 * Prównuje po średnim z(wartości z dla środka figury)
 */
public class MidZComparator implements DepthComparator {
	@Override
	public int compare(SortableByDepth a, SortableByDepth b) {
		return Double.compare(a.getMidZ(), b.getMidZ());
	}
}
