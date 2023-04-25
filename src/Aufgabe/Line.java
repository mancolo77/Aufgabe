package Aufgabe;
// * Eine Klasse, die eine U-Bahn-Linie beschreibt.
public class Line {
    // Der Name der U-Bahn-Linie.
    private String name;
    //Die Farbe der U-Bahn-Linie.
    private String line;

// Getters and Setters
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//   Konstruktor der Klasse, der einen U-Bahn-Liniennamen als Eingabe erhält.
    public Line(String name) {
        this.name = name;
    }

}