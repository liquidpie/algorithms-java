package com.vivek.graph;

import java.util.*;

public class MinimumSpanningTree {

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int m = in.nextInt();

            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                vertices.add(new Vertex(i));
            }

            for (int i = 0; i < m; i++) {
                Vertex u = vertices.get(in.nextInt() - 1);
                Vertex v = vertices.get(in.nextInt() - 1);
                int w = in.nextInt();
                Edge e = new Edge(u, v, w);
                u.addAdjacentEdge(v, e);
                v.addAdjacentEdge(u, e);
                edges.add(e);
            }

            Graph graph = new Graph(vertices, edges);
            System.out.println(getMst(graph, vertices.get(in.nextInt() - 1)));

        }

    }


    static int getMst(Graph graph, Vertex start) {
        int cost = 0;

        final Set<Vertex> unvisited = new HashSet<>();
        unvisited.addAll(graph.getVertices());
        unvisited.remove(start);

        final Queue<Edge> edgesAvailable = new PriorityQueue<>();

        Vertex vertex = start;

        while (!unvisited.isEmpty()) {
            // Add all edges to unvisited vertices
            for (Edge e : vertex.getAdjacencyMap().values()) {
                if (unvisited.contains(e.getDest()) ||
                        unvisited.contains(e.getSrc()))
                    edgesAvailable.add(e);
            }

            // Remove the lowest cost edge
            Edge e = edgesAvailable.remove();
            while (!unvisited.contains(e.getDest()) && !unvisited.contains(e.getSrc())) {
                e = edgesAvailable.remove();
            }
            cost += e.getWeight();

            vertex = vertex != e.getDest() ? e.getDest() : e.getSrc();
            if (!unvisited.contains(vertex)) {
                for (Vertex v : unvisited) {
                    vertex = v;
                    break;
                }
            }
            unvisited.remove(vertex);
        }
        return cost;
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
        public int compareTo(Object o) {
            Vertex other = (Vertex) o;
            return this.id < other.id ? this.id == other.id ? 0 : -1 : 1;
        }

        @Override
        public String toString() {
            return "Vertex " + this.id;
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
        public int compareTo(Object o) {
            Edge other = (Edge) o;
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
    }

}