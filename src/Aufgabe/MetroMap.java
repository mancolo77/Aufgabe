package Aufgabe;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Klasse, die eine MetroMap repräsentiert

public class MetroMap {
    // Instanzvariablen für die Stationen und die Adjazenzliste
    private List<Station> stations;
    private Map<Station, List<Station>> adjacencyMap;
    //  Konstruktor für eine MetroMap
    public MetroMap() {
        this.stations = new ArrayList<>();
        this.adjacencyMap = new HashMap<>();
    }
    //Fügt eine Station zur MetroMap hinzu
    public void addStation(Station station){
        stations.add(station);
    }
    //Gibt alle Stationen der MetroMap zurück
    public List<Station> getStations() {
        return stations;
    }
    //Fügt eine Verbindung zwischen zwei Stationen hinzu
    public void addConnection(Station startStation, Station endStation) {
        // Wenn die Startstation noch nicht in der Adjazenzliste enthalten ist, füge sie hinzu
        if (!adjacencyMap.containsKey(startStation)) {
            adjacencyMap.put((startStation), new ArrayList<>());
        }
        // Wenn die Endstation noch nicht in der Adjazenzliste enthalten ist, füge sie hinzu
        if (!adjacencyMap.containsKey(endStation)) {
            adjacencyMap.put((endStation), new ArrayList<>());
        }
        // Füge die Endstation als Nachbar der Startstation hinzu, und umgekehrt
        List<Station> startNeighbors = adjacencyMap.get(startStation);
        if (!startNeighbors.contains(endStation)) {
            startNeighbors.add(endStation);
        }
        List<Station> endNeighbors = adjacencyMap.get(endStation);
        if (!endNeighbors.contains(startStation)) {
            endNeighbors.add(startStation);
        }

        adjacencyMap.get(startStation).add((endStation));
        adjacencyMap.get(endStation).add((startStation));
    }
//    Lädt die Stationen aus Textdateien in einem Verzeichnis
    public void loadStations(String directory) {
        // Erstelle ein File-Objekt für das Verzeichnis
        File folder = new File(directory);
        // Erstelle ein Array mit den Dateien im Verzeichnis
        File[] listOfFiles = folder.listFiles();
        // Iteriere über die Dateien im Verzeichnis
        for (File file : listOfFiles) {
            // Wenn die Datei eine Textdatei ist
            if (file.isFile() && file.getName().endsWith(".txt")) {
                // Erstelle einen FileInputStream, einen InputStreamReader und einen BufferedReader
                try (FileInputStream inputStream = new FileInputStream(file);
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_16LE);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader);)
                {
        // Lese die erste Zeile der Datei und erstelle eine Line mit dem Namen der Datei
                    String line;
                    Line stationLine = new Line(file.getName().replace(".txt", ""));
                    Station prevStation = null;
                    // Iteriere über die Zeilen der Datei
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] parts = line.split(",");
                        String name = parts[0];
                        Station station = getStationByName(name);
                        if (station == null)
                            station = new Station(name);
                        if (prevStation != null)
                            addConnection(station, prevStation);
                        addStation(station);
                        station.addLines(stationLine);
                        prevStation = station;
                    }
                } catch (IOException e) {
                    System.err.format("IOException: %s%n", e);
                }
            }
        }
    }
    //Sucht eine Station anhand ihres Namens in der Liste von Stationen und gibt sie zurück.
    public Station getStationByName(String stationName) {
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        return null;
    }
    //Gibt eine Liste von Nachbarstationen für die gegebene Station zurück.
    public List<Station> getNeighbors(Station station) {
        // Erstelle eine leere ArrayList für Nachbarstationen.
        List<Station> neighbors = new ArrayList<>();
        // // Überprüfe, ob die gegebene Station im adjacencyMap enthalten ist.
        if (!adjacencyMap.containsKey(station)) {
            return neighbors;
        }
        // Iteriere über die Namen der Nachbarstationen aus adjacencyMap.
        for (Station stationName : adjacencyMap.get(station)) {
            // Erhalte die Nachbarstation aus der Liste der Stationen, indem die getStationByName-Methode aufgerufen wird.
            Station neighbor = getStationByName(stationName.getName());
            // Wenn eine Nachbarstation gefunden wird, füge sie der Liste von Nachbarstationen hinzu.
            if (neighbor != null) {
                neighbors.add(neighbor);
            }
        }
        // Gib die Liste von Nachbarstationen zurück.
        return neighbors;
    }
    public void addLine(Line line) {
    }

}
