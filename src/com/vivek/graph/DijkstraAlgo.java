package com.vivek.graph;

import java.util.*;

/**
 * Created by VJaiswal on 23/02/17.
 */
public class DijkstraAlgo {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unsettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DijkstraAlgo(Graph graph) {
        this.nodes = new ArrayList<>(graph.getVertices());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void execute(Vertex s) {
        settledNodes = new HashSet<>();
        unsettledNodes = new HashSet<>();
        predecessors = new HashMap<>();
        distance = new HashMap<>();

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
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unsettledNodes.add(target);
            }
        }
    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSrc().equals(node) && edge.getDest().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("No edge found for vertices [" + node + ", " + target + "]");
    }

    private List<Vertex> getAdjacentNodes(Vertex node) {
        List<Vertex> adjacentNodes = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSrc().equals(node) && !isSettled(edge.getDest())) {
                adjacentNodes.add(edge.getDest());
            }
        }
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

    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }


    // Enclosing Graph
    class Graph {
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

    class Vertex {
        private final int id;

        public Vertex(int id) {
            this.id = id;
        }

        public int getId() { return id; }

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

    }

    class Edge {
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
        public String toString() {
            return src + " " + dest;
        }
    }

}
