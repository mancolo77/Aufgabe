package Tests;

import Aufgabe.Line;
import Aufgabe.Main;
import Aufgabe.MetroMap;
import Aufgabe.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Diese Klasse enthält Tests für die MetroMap-Klasse.
 */
class MetroMapTest {

    private MetroMap map;

    /**
     * Initialisiert eine neue Instanz von MetroMap für jeden Test.
     */
    @BeforeEach
    void setUp() {
        map = new MetroMap();
    }

    /**
     * Testet die addStation-Methode.
     */
    @Test
    void testAddStation() {
        // Erstellt eine neue Station und fügt sie zur Karte hinzu.
        Station station = new Station("Test Aufgabe.Station");
        map.addStation(station);
        // Überprüft, ob die Station in der Karte enthalten ist.
        assertTrue(map.getStations().contains(station));
    }

    /**
     * Testet die addConnection-Methode.
     */
    @Test
    void testAddConnection() {
        // Erstellt eine neue Karte und zwei Stationen.
        MetroMap metroMap = new MetroMap();
        Station station1 = new Station("Aufgabe.Station 1");
        Station station2 = new Station("Aufgabe.Station 2");
        // Fügt die Stationen zur Karte hinzu und verbindet sie.
        metroMap.addStation(station1);
        metroMap.addStation(station2);
        metroMap.addConnection(station1, station2);
        // Überprüft, ob die Stationen miteinander verbunden sind.
        List<Station> neighbors1 = metroMap.getNeighbors(station1);
        List<Station> neighbors2 = metroMap.getNeighbors(station2);
        assertTrue(neighbors1.contains(station2));
        assertTrue(neighbors2.contains(station1));
    }

    /**
     * Testet die loadStations-Methode.
     */
    @Test
    void testLoadStations() {
        // Lädt die Stationen aus der Datei "Lines".
        map.loadStations("Lines");
        // Überprüft, ob die Liste der Stationen nicht leer ist.
        assertFalse(map.getStations().isEmpty());
    }

    /**
     * Testet die getStationByName-Methode.
     */
    @Test
    void testGetStationByName() {
        // Erstellt eine neue Station und fügt sie zur Karte hinzu.
        Station station = new Station("Test Aufgabe.Station");
        map.addStation(station);
        // Überprüft, ob die Station anhand ihres Namens gefunden werden kann.
        assertEquals(station, map.getStationByName("Test Aufgabe.Station"));
        // Überprüft, ob eine nicht existierende Station nicht gefunden wird.
        assertNull(map.getStationByName("Non-existent Aufgabe.Station"));
    }

    /**
     * Testet die addLine-Methode.
     */
    @Test
    void testAddLine() {
        // Erstellt eine neue Linie und fügt sie zur Karte hinzu.
        Line line = new Line("Test Aufgabe.Line");
        map.addLine(line);
        // Es werden keine Überprüfungen durchgeführt, nur ob die Methode funktioniert.
    }

    /**
     * Testet die getShortestPath-Methode.
     */

    @Test
    void testGetShortestPath()  {
        Station station1 = new Station("Aufgabe.Station 1");
        Station station2 = new Station("Aufgabe.Station 2");
        Station station3 = new Station("Aufgabe.Station 3");
        Station station4 = new Station("Aufgabe.Station 4");
        map.addStation(station1);
        map.addStation(station2);
        map.addStation(station3);
        map.addStation(station4);
        map.addConnection(station1, station2);
        map.addConnection(station2, station3);
        map.addConnection(station3, station4);
        try {
            assertEquals(4, Main.getShortestPath(station1, station4, map).size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
