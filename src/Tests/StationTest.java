package Tests;

import Aufgabe.Line;
import Aufgabe.Station;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Dies ist die Testklasse für die Klasse Station. Hier werden verschiedene Methoden von Station getestet.
 */
public class StationTest {
    /**
     * Testet, ob der Konstruktor von Station korrekt funktioniert.
     */
    @Test
    public void testConstructor() {
        Station station = new Station("Test Station");
        assertNotNull(station);
        assertEquals("Test Station", station.getName());
        assertTrue(station.getLines().isEmpty());
    }
    /**
     * Testet, ob die Methode addLines von Station korrekt funktioniert.
     */
    @Test
    public void testAddLines() {
        Station station = new Station("Test Station");
        Line line1 = new Line("Line 1");
        Line line2 = new Line("Line 2");
        station.addLines(line1);
        station.addLines(line2);
        List<Line> lines = station.getLines();
        assertEquals(2, lines.size());
        assertTrue(lines.contains(line1));
        assertTrue(lines.contains(line2));
    }
    /**
     * Testet, ob die Methode toString von Station korrekt funktioniert.
     */
    @Test
    public void testToString() {
        Station station = new Station("Test Station");
        assertEquals("Test Station", station.toString());
    }

}
