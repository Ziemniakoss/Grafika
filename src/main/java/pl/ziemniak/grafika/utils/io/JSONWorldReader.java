package pl.ziemniak.grafika.utils.io;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.ziemniak.grafika.World;
import pl.ziemniak.grafika.utils.Object3D;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class JSONWorldReader implements IWorldReader{
	private final String filename;

	public JSONWorldReader(String filename) {
		this.filename = filename;
	}

	@Override
	public World readFromFile() throws NonParsableWorldException, IOException {
		String file = Files.readString(Path.of(filename), StandardCharsets.UTF_8);
		JSONConverterFactory factory = new JSONConverterFactory();
		JSONArray array = new JSONArray(file);
		Collection<Object3D> objects = new ArrayList<>();
		for(int i = 0; i < array.length();i++){
			JSONObject j = array.getJSONObject(i);
			String type = null;
			try {
				type = j.getString("type");
			}catch (JSONException e){
				throw new NonParsableWorldException("Object3D has no type");
			}
			JSONToObject3DConverter converter = factory.getJSONToObject3DConverter(type);
			if(converter == null){
				throw new NonParsableWorldException("Unsupported Object3D type: "+ type );
			}
			objects.add(converter.convert(j));
		}
		return new World(objects);
	}
}
