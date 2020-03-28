package pl.ziemniak.grafika.utils.rendering;

/**
 * Pozwala na wyświetlanie obiektu na ekranie
 */
public interface IRenderable {
	/**
	 * Renderuje obiekt na ekranie z odpowiednim zoomem.
	 *
	 * @param screen ekran na któ©ym należy wyświetlić dany obiekt
	 * @param zoom   zoom z którym powinna być wyrenderowana figura.
	 * @param d      odległość kamery od rzutnii
	 */
	void render(Screen screen, double zoom, double d);
}
