package pl.ziemniak.grafika.utils.io;

import org.json.JSONObject;
import pl.ziemniak.grafika.utils.math.Vector;

/**
 * Konwerter JSON -> Vector
 */
public class JSONTo3DVectorConverter {
	private static final JSONTo3DVectorConverter instance = new JSONTo3DVectorConverter();

	public static JSONTo3DVectorConverter getInstance() {
		return instance;
	}

	private JSONTo3DVectorConverter() {
	}

	/**
	 * Konwertuje jsona na wektor 3D.
	 * JSON musi zawierać pola:
	 * <ul>
	 *     <li>x -> typ double</li>
	 *     <li>y -> typ double</li>
	 *     <li>z -> typ double</li>
	 * </ul>
	 *
	 * @param json json do przekonwertowania
	 * @return wektor 3D
	 * @throws NullPointerException   kiedy json był nullem
	 * @throws org.json.JSONException kiedy json nie zawierał wymaganych pól
	 */
	public Vector convert(JSONObject json) {
		return new Vector(true,
				json.getDouble("x"),
				json.getDouble("y"),
				json.getDouble("z"));

	}
}
