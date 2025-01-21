package com.vivek.graph.examples;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Calculates the cost of the fare for the cheapest path between two stations in a metro system.
 */
public class MinFareBetweenTrainStations {

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", new HashMap<>());
        graph.get("A").put("B", 5);
        graph.get("A").put("D", 15);
        graph.put("B", new HashMap<>());
        graph.get("B").put("C", 7);
        graph.get("B").put("E", 10);
        graph.put("C", new HashMap<>());
        graph.get("C").put("D", 3);

        // Calculate fares
        System.out.println("Fare from A to C: " + calculateFare(graph,"A", "C")); // Output: 12
        System.out.println("Fare from A to D: " + calculateFare(graph,"A", "D")); // Output: 15
        System.out.println("Fare from A to E: " + calculateFare(graph,"A", "E")); // Output: 15
    }

    static int calculateFare(Map<String, Map<String, Integer>> graph, String start, String end) {
        // add bidirectional connections
        Map<String, Map<String, Integer>> bigraph = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                bigraph.computeIfAbsent(entry.getKey(), k -> new HashMap<>())
                        .put(innerEntry.getKey(), innerEntry.getValue());
                bigraph.computeIfAbsent(innerEntry.getKey(), k -> new HashMap<>())
                        .put(entry.getKey(), innerEntry.getValue());
            }
        }
        graph.putAll(bigraph);

        // Dijkstra's Algorithm for shortest path
        Map<String, Integer> distances = new HashMap<>();
        for (String station : graph.keySet()) {
            distances.put(station, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        pq.add(Map.entry(start, 0));

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> current = pq.poll();
            String currentStation = current.getKey();
            int currentDistance = current.getValue();

            for (Map.Entry<String, Integer> neighbor : graph.get(currentStation).entrySet()) {
                String neighborStation = neighbor.getKey();
                int travelCost = neighbor.getValue();
                int newDistance = currentDistance + travelCost;

                if (newDistance < distances.get(neighborStation)) {
                    distances.put(neighborStation, newDistance);
                    pq.add(Map.entry(neighborStation, newDistance));
                }
            }
        }

        int fare = distances.get(end);
        return fare == Integer.MAX_VALUE ? -1 : fare; // Return -1 if the station is unreachable
    }

}
