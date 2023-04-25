package Tests;

import Aufgabe.Line;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * JUnit-Testklasse für die Line-Klasse.
 */
public class LineTest {

    /**
     * Testet, ob die korrekte Linienbezeichnung zurückgegeben wird.
     */
    @Test
    public void testGetName() {
        Line line = new Line("(Uzbekistan Line");
        assertEquals("(Uzbekistan Line", line.getName());
    }

    /**
     * Testet, ob die Linienbezeichnung korrekt aktualisiert wird.
     */
    @Test
    public void testSetName() {
        Line line = new Line("(Uzbekistan Line");
        line.setName("(Uzbekistan Line");
        assertEquals("(Uzbekistan Line", line.getName());
    }

    /**
     * Testet, ob eine IllegalArgumentException ausgelöst wird,
     * wenn bei der Erstellung einer neuen Line ein Null-Name übergeben wird.
     */
    @Test
    public void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Line line = new Line(null);
        });
    }

}

