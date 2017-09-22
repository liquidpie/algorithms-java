package com.vivek.graph;

/**
 * Created by VJaiswal on 23/02/17.
 */

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathWeight {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unsettledNodes;
    private Map<Vertex, Integer> distance;

    private Map<Vertex, List<Vertex>> adjacentsMap;

    public ShortestPathWeight(Graph graph) {
        this.nodes = new ArrayList<>(graph.getVertices());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void execute(Vertex s) {
        settledNodes = new HashSet<>();
        unsettledNodes = new HashSet<>();
        distance = new HashMap<>();
        adjacentsMap = new HashMap<>();

        distance.put(s, 0);
        unsettledNodes.add(s);
        while (unsettledNodes.size() > 0) {
            Vertex node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimumDistance(node);
        }
    }

    private void findMinimumDistance(Vertex node) {
        List<Vertex> adjacentNodes = getAdjacentNodes(node);
        for (Vertex target : adjacentNodes) {
            int t1 = getShortestDistance(target);
            int t2 = getShortestDistance(node) + getDistance(node, target);
            if (t1 > t2) {
                distance.put(target, t2);
                unsettledNodes.add(target);
            }
        }
    }

    private int getDistance(Vertex node, Vertex target) {
        return Edge.WEIGHT;
    }

    private List<Vertex> getAdjacentNodes(Vertex node) {
        List<Vertex> adjacentNodes = node.getAdjacent();
        if (adjacentsMap.containsKey(node)) {
            return adjacentsMap.get(node);
        }

        adjacentNodes = adjacentNodes.parallelStream().filter(e -> !isSettled(e)).collect(Collectors.toList());
        adjacentsMap.put(node, adjacentNodes);
        return adjacentNodes;
    }

    private Vertex getMinimum(Set<Vertex> vertices) {
        Vertex min = null;
        for (Vertex node : vertices) {
            if (min == null) {
                min = node;
            } else if (getShortestDistance(node) < getShortestDistance(min)) {
                min = node;
            }
        }
        return min;
    }

    private boolean isSettled(Vertex vertex) { return settledNodes.contains(vertex); }

    private int getShortestDistance(Vertex node) {
        Integer d = distance.get(node);
        return d == null ? Integer.MAX_VALUE : d;
    }

    public int getPathWeight(Vertex node) {
        return distance.get(node) == null ? -1 : distance.get(node);
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
        private List<Vertex> adjacent;

        public Vertex(int id) {
            this.id = id;
            adjacent = new ArrayList<>();
        }

        public int getId() { return id; }

        public List<Vertex> getAdjacent() {
            return adjacent;
        }

        public void addAdjacentNode(Vertex node) {
            adjacent.add(node);
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

    static class Edge {
        private final Vertex src;
        private final Vertex dest;
        private static final int WEIGHT = 6;

        public Edge(Vertex src, Vertex dest) {
            this.src = src;
            this.dest = dest;
        }

        public Vertex getSrc() {
            return src;
        }

        public Vertex getDest() {
            return dest;
        }

        @Override
        public String toString() {
            return src + " " + dest;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        long start = System.currentTimeMillis();

        try (Scanner in = new Scanner(System.in)) {
            int q = in.nextInt();
            for (int i = 0; i < q; i++) {
                Map<Integer, Vertex> vertexMap = new LinkedHashMap<>();
                List<Vertex> vertices = new ArrayList<>();
                List<Edge> edges = new ArrayList<>();

                int n = in.nextInt();
                int m = in.nextInt();
                for (int k = 1; k <= n; k++) {
                    vertexMap.put(k, new Vertex(k));
                }

                vertices.addAll(vertexMap.values());

                for (int j = 0; j < m; j++) {
                    Vertex src = vertexMap.get(in.nextInt());
                    Vertex dest = vertexMap.get(in.nextInt());
                    Edge edge = new Edge(src, dest);
                    edges.add(edge);

                    src.addAdjacentNode(dest);
                    dest.addAdjacentNode(src);
                }
                Vertex s = vertexMap.get(in.nextInt());

                Graph graph = new Graph(vertices, edges);

                ShortestPathWeight sol = new ShortestPathWeight(graph);
                sol.execute(s);

                for (Vertex node : vertices) {
                    if (node != s)
                        System.out.print(sol.getPathWeight(node) + " ");
                }
                System.out.println();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start));
    }


}