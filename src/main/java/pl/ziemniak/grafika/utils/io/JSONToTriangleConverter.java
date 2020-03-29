package pl.ziemniak.grafika.utils.io;

import org.json.JSONObject;
import pl.ziemniak.grafika.utils.math.Triangle;

public class JSONToTriangleConverter extends JSONToObject3DConverter<Triangle> {
	@Override
	public Triangle convert(JSONObject json) throws NonParsableWorldException {
		return null;//todo
	}
}
