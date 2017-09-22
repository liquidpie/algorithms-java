package com.vivek.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VJaiswal on 07/03/17.
 */
public class Graph<E extends Comparable<E>> {

    private List<Vertex<E>> vertices;
    private List<Edge<E>> edges;

    public Graph(List<Vertex<E>> vertices, List<Edge<E>> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<Vertex<E>> getVertices() {
        return vertices;
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }

    // Implicit structures
    public static class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> {
        private final E value;
        private Map<Vertex<E>, Edge<E>> adjacencyMap;

        public Vertex(E value, Map<Vertex<E>, Edge<E>> adjacencyMap) {
            this.value = value;
            if (adjacencyMap != null) {
                this.adjacencyMap = adjacencyMap;
            } else {
                this.adjacencyMap = new HashMap<>();
            }
        }

        public Vertex(E value) {
            this(value, null);
        }

        public E getValue() {
            return value;
        }

        public Map<Vertex<E>, Edge<E>> getAdjacencyMap() {
            return adjacencyMap;
        }

        public void addAdjacentEdge(Vertex<E> node, Edge<E> edge) {
            adjacencyMap.put(node, edge);
        }

        @Override
        public int compareTo(Vertex<E> o) {
            Vertex<E> that = o;
            final int comparison = this.value.compareTo(that.value);
            if (comparison != 0) {
                return comparison;
            }
            if (this.adjacencyMap.size() < that.adjacencyMap.size()) {
                return -1;
            }
            if (this.adjacencyMap.size() > that.adjacencyMap.size()) {
                return 1;
            }
            return 0;
        }

        @Override
        public int hashCode() {
            final int result = this.value.hashCode() + this.adjacencyMap.size();
            return 31 * result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            final Vertex<E> that = (Vertex) o;

            if (this.value != that.value) {
                return false;
            }
            if (this.adjacencyMap.size() != that.adjacencyMap.size()) {
                return false;
            }
            if (!(this.adjacencyMap.equals(that.adjacencyMap))) {
                return false;
            }
            return true;
        }

    }

    public static class Edge<E extends Comparable<E>> implements Comparable<Edge<E>> {
        private final int weight;
        private final Vertex<E> src;
        private final Vertex<E> dest;

        public Edge(Vertex<E> src, Vertex<E> dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public Vertex<E> getSrc() {
            return src;
        }

        public Vertex<E> getDest() {
            return dest;
        }

        @Override
        public int hashCode() {
            return 31 * (this.weight * this.src.value.hashCode() * this.dest.value.hashCode());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final Edge<E> that = (Edge) o;
            if (this.weight == that.weight &&
                    this.src == that.src &&
                    this.dest == that.dest) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Edge<E> o) {
            Edge<E> that = o;
            if (this.src == that.src && this.dest == that.dest) {
                return 0;
            }
            if (this.weight < that.weight) {
                return -1;
            }
            if (this.weight > that.weight) {
                return 1;
            }
            return 0;
        }
    }

}
