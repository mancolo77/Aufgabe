package Aufgabe;

import java.util.ArrayList;
import java.util.List;


// Eine Klasse, die eine U-Bahn-Station beschreibt.
public class Station {
    // Liste der U-Bahn-Linien, auf denen sich die Station befindet.
    private List<Line> lines;

    // Name der U-Bahn-Station.
    private String name;
//  Konstruktor der Klasse, die den Namen der U-Bahn-Station als Eingabe erhält.
    public Station(String name) {
        this.name = name;
        this.lines = new ArrayList<>();
    }
//    Erhalten Sie eine Liste der U-Bahn-Linien, an denen der Bahnhof liegt.
    public List<Line> getLines() {
        return lines;
    }

// Hinzufügen einer U-Bahn-Linie zum Bahnhof.
    public void addLines(Line line){
        lines.add(line);
    }

    public String getName() {
        return name;
    }
// Gibt den Namen der U-Bahn-Station als String zurück.
    @Override
    public String toString(){
        return name;
    }

}
