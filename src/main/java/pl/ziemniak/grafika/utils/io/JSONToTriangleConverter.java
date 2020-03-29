package pl.ziemniak.grafika.utils.io;

import javafx.scene.paint.Color;
import org.json.JSONException;
import org.json.JSONObject;
import pl.ziemniak.grafika.utils.math.Triangle;
import pl.ziemniak.grafika.utils.math.Vector;

public class JSONToTriangleConverter extends JSONToObject3DConverter<Triangle> {
	@Override
	public Triangle convert(JSONObject json) throws NonParsableWorldException {
		JSONTo3DVectorConverter jtv = JSONTo3DVectorConverter.getInstance();
		Vector a, b, c;
		Color border, fill;
		try {
			a = jtv.convert(json.getJSONObject("a"));
			b = jtv.convert(json.getJSONObject("b"));
			c = jtv.convert(json.getJSONObject("c"));
		} catch (JSONException e) {
			throw new NonParsableWorldException("JSON describing triangle must have 3 points(a,b,c)");
		}
		try {
			border = Color.web(json.getString("border"));
		} catch (JSONException e) {
			throw new NonParsableWorldException("Triangle must have border color");
		}
		fill = json.has("fill") ? Color.web(json.getString("fill")) : null;
		return new Triangle(a, b, c, border, fill);
	}
}
