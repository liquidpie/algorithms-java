package com.vivek.graph;

import java.util.*;

/**
 * Created by VJaiswal on 25/04/18.
 */
public class Graph {

    private final Map<Integer, Vertex> vertices;
    private final List<Edge> edges;

    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = new HashMap<>();
        vertices.forEach(vertex -> this.vertices.put(vertex.getId(), vertex));
        this.edges = edges;
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(int vertexVal1, int vertexVal2) {
        addEdge(vertexVal1, vertexVal2, 0);
    }

    public void addEdge(int vertexVal1, int vertexVal2, int weight) {
        Vertex v1 = getOrCreateVertex(vertexVal1);
        Vertex v2 = getOrCreateVertex(vertexVal2);
        Edge edge = new Edge(v1, v2, weight);
        edges.add(edge);
        v1.getAdjacencyMap().put(v2, edge);
    }

    public Vertex getVertex(int id) {
        return vertices.get(id);
    }

    private Vertex getOrCreateVertex(int id) {
        if (!vertices.containsKey(id)) {
            vertices.put(id, new Vertex(id));
        }
        return vertices.get(id);
    }

    public static class Vertex implements Comparable<Vertex> {
        private final int id;
        private final Map<Vertex, Edge> adjacencyMap; // outgoing edges for directed graph

        public Vertex(int id) {
            this.id = id;
            adjacencyMap = new HashMap<>();
        }

        public int getId() { return id; }

        public Map<Vertex, Edge> getAdjacencyMap() {
            return adjacencyMap;
        }

        public Set<Vertex> getAdjacentVertices() {
            return adjacencyMap.keySet();
        }

        public Collection<Edge> getAdjacentEdges() {
            return adjacencyMap.values();
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
        public int compareTo(Vertex other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return "Vertex " + this.id;
        }

    }

    public static class Edge implements Comparable<Edge> {
        private final Vertex src;
        private final Vertex dest;
        private final int weight;

        public Edge(Vertex src, Vertex dest) {
            this(src, dest, 0);
        }

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

        public Vertex opposite(Vertex v) {
            return v == src ? dest : src;
        }

        @Override
        public int hashCode() {
            return 31 * (this.weight * this.src.id * this.dest.id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final Edge that = (Edge) o;
            if (this.weight == that.weight &&
                    this.src == that.src &&
                    this.dest == that.dest) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Edge other) {
            if (this.src == other.src && this.dest == other.dest) {
                return 0;
            }
            if (this.src == other.dest && this.dest == other.src) {
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
            return "Edge (" + src + ", " + dest + ", " + weight + ")";
        }
    }

}
