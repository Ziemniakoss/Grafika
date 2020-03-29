package pl.ziemniak.grafika.utils.io;

import javafx.scene.paint.Color;
import org.json.JSONException;
import org.json.JSONObject;
import pl.ziemniak.grafika.utils.math.Line;
import pl.ziemniak.grafika.utils.math.Vector;

public class JSONToLineConverter extends JSONToObject3DConverter<Line> {
	@Override
	public Line convert(JSONObject json)throws NonParsableWorldException {
		try {
			Vector a = new Vector(true,
					json.getDouble("x1"),
					json.getDouble("y1"),
					json.getDouble("z1"));
			Vector b = new Vector(true,
					json.getDouble("x2"),
					json.getDouble("y2"),
					json.getDouble("z2"));
			Color color = Color.web(json.getString("color"));
			return new Line(a,b,color);
		}catch (JSONException e){
			throw new NonParsableWorldException("Could not read Line object from file");
		}
	}
}
