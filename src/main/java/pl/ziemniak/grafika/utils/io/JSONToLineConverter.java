package pl.ziemniak.grafika.utils.io;

import javafx.scene.paint.Color;
import org.json.JSONException;
import org.json.JSONObject;
import pl.ziemniak.grafika.utils.math.Line;
import pl.ziemniak.grafika.utils.math.Vector;

public class JSONToLineConverter extends JSONToObject3DConverter<Line> {
	@Override
	public Line convert(JSONObject json) throws NonParsableWorldException {
		Vector a, b;
		Color color;
		JSONTo3DVectorConverter vectorConverter = JSONTo3DVectorConverter.getInstance();
		try {
			a = vectorConverter.convert(json.getJSONObject("a"));
			b = vectorConverter.convert(json.getJSONObject("b"));
		} catch (JSONException e) {
			throw new NonParsableWorldException("Line must have 2 points: a and b");
		}
		try {
			color = Color.web(json.getString("color"));
		} catch (JSONException e) {
			throw new NonParsableWorldException("Line must have color");
		}
		return new Line(a, b, color);
	}
}
