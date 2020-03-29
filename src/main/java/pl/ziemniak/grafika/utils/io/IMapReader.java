package pl.ziemniak.grafika.utils.io;

import pl.ziemniak.grafika.utils.math.Line;

import java.io.IOException;
import java.util.List;

/**
 * Służy do wczytywania danych o liniach z pliku
 */
public interface IMapReader {
	List<Line> read() throws IOException, NonParsableWorldException;
}
