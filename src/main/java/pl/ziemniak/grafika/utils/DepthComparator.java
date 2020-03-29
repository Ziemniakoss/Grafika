package pl.ziemniak.grafika.utils;

import java.util.Comparator;

public class DepthComparator implements Comparator<Object3D> {
	@Override
	public int compare(Object3D a, Object3D b) {
		return Double.compare(b.getMaxDepth(), a.getMaxDepth());
	}
}
