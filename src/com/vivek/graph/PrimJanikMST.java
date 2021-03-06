package com.vivek.graph;

import java.util.*;

/**
 * Created by VJaiswal on 02/03/17.
 */
public class PrimJanikMST {

    private int totalWeight = Integer.MAX_VALUE;
    private List<Edge> mst;

    public void getMinimumSpanningTree(Graph graph, Vertex start) {
        if (graph == null) {
            throw new RuntimeException("Graph should not be null");
        }

        int cost = 0;

        final Set<Vertex> unvisited = new HashSet<>();
        unvisited.addAll(graph.getVertices());
        unvisited.remove(start);

        final List<Edge> tree = new ArrayList<>();
        final Queue<Edge> edgesAvailable = new PriorityQueue<>();

        Vertex vertex = start;

        while (!unvisited.isEmpty()) {
            // Add all edges to unvisited vertices
            for (Edge e : vertex.getAdjacencyMap().values()) {
                if (unvisited.contains(e.getDest()))
                    edgesAvailable.add(e);
            }

            // Remove the lowest cost edge
            final Edge e = edgesAvailable.remove();
            cost += e.getWeight();
            tree.add(e); // O(1)

            vertex = e.getDest();
            unvisited.remove(vertex); // O(1)
        }


        totalWeight = cost;
        mst = tree;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public List<Edge> getMst() {
        return mst;
    }

    // Enclosing Graph
    static class Graph {
        private final List<Vertex> vertices;
        private final List<Edge> edges;

        public Graph(List<Vertex> vertices, List<Edge> edges) {
            this.vertices = vertices;
            this.edges = edges;
        }

        public List<Vertex> getVertices() {
            return vertices;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    static class Vertex implements Comparable {
        private final int id;
        private Map<Vertex, Edge> adjacencyMap;

        public Vertex(int id) {
            this.id = id;
            adjacencyMap = new HashMap<>();
        }

        public int getId() { return id; }

        public Map<Vertex, Edge> getAdjacencyMap() {
            return adjacencyMap;
        }

        public void addAdjacentEdge(Vertex node, Edge edge) {
            adjacencyMap.put(node, edge);
        }

        @Override
        public int hashCode() {
            int prime = 31;
            int result = 1;
            return prime * (result + id);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) { return true; }
            if (other == null) { return false; }
            if (getClass() != other.getClass()) { return false; }
            Vertex o = (Vertex) other;
            return this.id == o.id;
        }

        @Override
        public String toString() { return "" + id; }

        @Override
        public int compareTo(Object o) {
            Vertex other = (Vertex) o;
            return this.id < other.id ? this.id == other.id ? 0 : -1 : 1;
        }

    }

    static class Edge implements Comparable {
        private final Vertex src;
        private final Vertex dest;
        private final int weight;

        public Edge(Vertex src, Vertex dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public Vertex getSrc() {
            return src;
        }

        public Vertex getDest() {
            return dest;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Object o) {
            Edge other = (Edge) o;
            if (this.src == other.src && this.dest == other.dest) {
                return 0;
            }
            if (this.weight > other.weight) {
                return 1;
            }
            if (this.weight < other.weight) {
                return -1;
            }
            return 0;
        }


        @Override
        public String toString() {
            return src + " " + dest + " [" + weight + "]";
        }
    }
}
