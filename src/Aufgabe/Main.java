package Aufgabe;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;



public class Main {
    public static void main(String[] args) {
//      Ich erstelle eine MetroMap-Klasse
        MetroMap metroMap = new MetroMap();
        metroMap.loadStations("Lines");
        Scanner scanner = new Scanner(System.in);
        String folderPath = "Lines";

//      Betrachten Sie den Ordner Lines und die darin enthaltenen txt-Dateien
        File folder = new File(folderPath);

        if (!folder.isDirectory()){
            System.out.println(folderPath + " is not a directory");
            return;
        }
        File[] files = folder.listFiles();

        for(File file : files){
            if (file.isFile() && file.getName().endsWith(".txt")){
                try {
                    Scanner scanner1 = new Scanner(file);
                    System.out.println("\n" + "Inhalt der Datei" + " " + file.getName() + ":\n");
                    while (scanner1.hasNextLine()){
                        String line = scanner.nextLine();
                        System.out.println(line);
                    }
//                    scanner.close();
                    List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_16);
                    for (String line :lines){
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.err.format("Problem: %s%n", e);
                }
            }
        }
//  Die Logik hier ist, wenn der Benutzer den Start und Zielbahnhof eingibt
        System.out.println("Start");
        String startStationInput = scanner.nextLine().trim();
        Station startStation = metroMap.getStationByName(startStationInput);
        while (startStation == null){
            System.out.print("Die Station wurde nicht gefunden, versuchen Sie es erneut: ");
            startStationInput = scanner.nextLine().trim();
            startStation = metroMap.getStationByName(startStationInput);
        }
        System.out.println("Ziel");
        String endStationInput = scanner.nextLine().trim();
        Station endStation = metroMap.getStationByName(endStationInput);
        while (endStation == null){
            System.out.print("Die Station wurde nicht gefunden, versuchen Sie es erneut: ");
            endStationInput = scanner.nextLine().trim();
            endStation = metroMap.getStationByName(endStationInput);
        }

//        Anzeige und Suche von Sendern und Programmantwortausgabe
        List<Station> path = getShortestPath(startStation, endStation, metroMap);
        if (path == null) {
            System.out.println("Kein Pfad gefunden");
        } else {
            for (int i = 0; i < path.size(); i++) {
                Station station = path.get(i);
                System.out.print(station.getName() + " ");
                List<Line> lines = station.getLines();
                if (lines.size() > 1 && i > 0) {
                    Line prevLine = path.get(i-1).getLines().stream()
                            .filter(lines::contains)
                            .findFirst().orElse(null);
                    Line currLine = lines.stream()
                            .filter(l -> !l.equals(prevLine))
                            .findFirst().orElse(null);
                    if (prevLine != null && currLine != null) {
                        System.out.print("(Wechseln zu " + currLine.getName() + ") ");
                    }
                } else if (lines.size() == 1 && i > 0) {
                    Line prevLine = path.get(i-1).getLines().stream()
                            .filter(lines::contains)
                            .findFirst().orElse(null);
                    if (prevLine != null && !prevLine.equals(lines.get(0))) {
                        System.out.print("(Wechseln zu " + lines.get(0).getName() + ") ");
                    }
                }
                if (lines.size() == 1) {
                    System.out.print("(" + lines.get(0).getName() + ")");
                }
                System.out.println();
            }

            }
        }

//    Dieser Code implementiert eine Suche nach dem kürzesten Weg zwischen zwei Stationen in einer U-Bahn unter Verwendung des BFS-Suchalgorithmus.
    public static List<Station> getShortestPath(Station startStation, Station endStation, MetroMap metroMap) {
        Map<Station, Station> parents = new HashMap<>();
        Set<Station> visited = new HashSet<>();
        Queue<Station> queue = new LinkedList<>();

        queue.add(startStation);
        visited.add(startStation);

        while (!queue.isEmpty()) {
            Station currentStation = queue.remove();
            if (currentStation.equals(endStation)) {
                break;
            }
            List<Station> neighbors = metroMap.getNeighbors(currentStation);
            for (Station neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parents.put(neighbor, currentStation);
                    queue.add(neighbor);
                }
            }
        }

        List<Station> path = new ArrayList<>();
        Station currentStation = endStation;
        while (currentStation != null) {
            path.add(0, currentStation);
            currentStation = parents.get(currentStation);
        }

        if (!path.isEmpty() && path.get(0).equals(startStation)) {
            return path;
        } else {
            return null;
        }
    }
}
