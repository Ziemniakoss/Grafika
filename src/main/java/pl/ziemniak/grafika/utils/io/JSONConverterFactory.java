package pl.ziemniak.grafika.utils.io;

public class JSONConverterFactory {
	public JSONToObject3DConverter getJSONToObject3DConverter(String type) {
		switch (type) {
			case "LINE":
				return new JSONToLineConverter();
			default:
				return null;
		}
	}
}
