package pl.ziemniak.grafika.utils.io;

import pl.ziemniak.grafika.World;

import java.io.IOException;

public interface IWorldReader {
	World readFromFile() throws NonParsableWorldException, IOException;
}
