package com.vivek.iterators;

import java.util.*;

/**
 * Implement a graph iterator with the following requirements :
 *     Input : a undirected graph (all edge weights are positive) and a seed node
 *     Expectation : Must visit nodes in the graph in increasing order of distances from the given node.
 *
 *     Hint: Variation of Dijkstra’s algorithm
 *
 *     To visit nodes in the undirected weighted graph in increasing order of distances from the given node,
 *     you can use Dijkstra’s algorithm. It is a shortest path algorithm that works on graphs with non-negative edge weights.
 *     It starts at the source node and explores the neighboring nodes first,
 *     choosing the node with the smallest distance from the source node.
 *     Then it updates the distance of its neighbors and continues until all nodes have been visited.
 *     You can use a priority queue to keep track of the nodes with the smallest distance from the source node.
 */
public class GraphIterator implements Iterator<String> {
    private final Map<String, Map<String, Integer>> graph;
    private final Map<String, Integer> distances;
    private final Set<String> visited;
    private final PriorityQueue<Node> queue;

    public GraphIterator(Map<String, Map<String, Integer>> graph, String seed) {
        this.graph = graph;
        this.distances = new HashMap<>();
        this.visited = new HashSet<>();
        this.queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

        // Initialize distances with infinity for all nodes except the seed node
        for (String node : graph.keySet()) {
            if (node.equals(seed)) {
                distances.put(node, 0);
            } else {
                distances.put(node, Integer.MAX_VALUE);
            }
        }

        // Push seed node into the queue
        queue.add(new Node(seed, 0));
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (!queue.isEmpty()) {
            // Pop the node with the minimum distance from the queue
            Node current = queue.poll();

            // Check if the node has already been visited
            if (visited.contains(current.node)) {
                continue;
            }

            // Mark the node as visited
            visited.add(current.node);

            // Update the distances and push neighbors into the queue
            Map<String, Integer> neighbors = graph.get(current.node);
            for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
                String neighbor = entry.getKey();
                int weight = entry.getValue();

                if (visited.contains(neighbor)) {
                    continue;
                }

                int newDistance = distances.get(current.node) + weight;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    queue.add(new Node(neighbor, newDistance));
                }
            }

            // Return the current node
            return current.node;
        }

        throw new NoSuchElementException();
    }

    private static class Node {
        String node;
        int distance;

        Node(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", new HashMap<>());
        graph.put("B", new HashMap<>());
        graph.put("C", new HashMap<>());
        graph.put("D", new HashMap<>());
        graph.put("E", new HashMap<>());

        graph.get("A").put("B", 1);
        graph.get("A").put("C", 2);
        graph.get("B").put("A", 1);
        graph.get("B").put("D", 3);
        graph.get("C").put("A", 2);
        graph.get("C").put("D", 1);
        graph.get("D").put("B", 3);
        graph.get("D").put("C", 1);
        graph.get("D").put("E", 4);
        graph.get("E").put("D", 4);

        String seedNode = "A";
        GraphIterator iterator = new GraphIterator(graph, seedNode);

        while (iterator.hasNext()) {
            String node = iterator.next();
            System.out.println(node);
        }
    }
}

