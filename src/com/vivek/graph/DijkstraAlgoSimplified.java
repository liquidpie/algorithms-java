package com.vivek.graph;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/introduction-to-dijkstras-shortest-path-algorithm/
 */
public class DijkstraAlgoSimplified {

    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        pq.offer(new AbstractMap.SimpleEntry<>(start, 0));
        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> entry = pq.poll();
            String current_node = entry.getKey();
            int dist = entry.getValue();
            if (dist > distances.get(current_node)) {
                continue;
            }
            for (Map.Entry<String, Integer> neighbor : graph.get(current_node).entrySet()) {
                String neighbor_node = neighbor.getKey();
                int weight = neighbor.getValue();
                int distance = dist + weight;
                if (distance < distances.get(neighbor_node)) {
                    distances.put(neighbor_node, distance);
                    pq.offer(new AbstractMap.SimpleEntry<>(neighbor_node, distance));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", new HashMap<>());
        graph.get("A").put("B", 2);
        graph.get("A").put("C", 1);
        graph.put("B", new HashMap<>());
        graph.get("B").put("A", 2);
        graph.get("B").put("D", 3);
        graph.put("C", new HashMap<>());
        graph.get("C").put("A", 1);
        graph.get("C").put("D", 1);
        graph.put("D", new HashMap<>());
        graph.get("D").put("B", 3);
        graph.get("D").put("C", 1);

        String start_node = "A";
        Map<String, Integer> distances = dijkstra(graph, start_node);
        List<Map.Entry<String, Integer>> sorted_distances = new ArrayList<>(distances.entrySet());
        Collections.sort(sorted_distances, Map.Entry.comparingByValue());
        System.out.println(sorted_distances);
    }

}
