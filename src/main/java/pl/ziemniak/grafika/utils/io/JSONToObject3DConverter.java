package pl.ziemniak.grafika.utils.io;

import org.json.JSONObject;
import pl.ziemniak.grafika.utils.Object3D;

public abstract class JSONToObject3DConverter<T extends Object3D> {
	public abstract T convert(JSONObject json) throws NonParsableWorldException;
}
