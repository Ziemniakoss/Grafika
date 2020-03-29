package pl.ziemniak.grafika.utils.io;

import javafx.scene.paint.Color;
import pl.ziemniak.grafika.utils.io.IMapReader;
import pl.ziemniak.grafika.utils.math.Line;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.ziemniak.grafika.utils.math.Vector;

/**
 * Wczytuje dane o liniach z pliku
 * Plik zawierający jsona musi być listą jsonów o polach:
 * <ul>
 *     <li>x1 - x pierwszego punktu</li>
 *     <li>y1 - y pierwszego punktu</li>
 *     <li>z1 - z pierwszego punktu</li>
 *     <li>x2 - x drugiego punktu</li>
 *     <li>y2 - y drugiego punktu</li>
 *     <li>z2 - z drugiego punktu</li>
 *     <li>color - kolor zapisany w formacie html</li>
 * </ul>
 */
public class JSONMapReader implements IMapReader {

	private final String filename;

	public JSONMapReader(String filename) {
		if (filename == null) {
			throw new NullPointerException("File name can't be null");
		}
		this.filename = filename;
	}

	/**
	 * Wczytuje dane o liniach z pliku o podanej w konstruktorze ścieżce
	 *
	 * @return wczytaną listę lini z pliku
	 * @throws IOException            gdy plik nie istnieje lub nie ma do niego dostępu
	 * @throws org.json.JSONException gdy podany plik zawiera jsona w błędnym formacie
	 */
	@Override
	public List<Line> read() throws IOException, NonParsableWorldException {
		String json = Files.readString(Path.of(filename), StandardCharsets.UTF_8);
		JSONArray ja = new JSONArray(json);
		List<Line> lines = new ArrayList<>();
		JSONToLineConverter converter = new JSONToLineConverter();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject jo = ja.getJSONObject(i);
			Line l = converter.convert(jo);
			lines.add(l);
		}
		return lines;
	}
}
