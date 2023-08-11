package com.vivek.graph;

import java.util.ArrayList;
import java.util.List;

public class SimpleGraph {

    public final int vertices;  // number of vertices
    public final List<List<Integer>> adjacencyList;

    public SimpleGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v) {
        if (!edgeExists(u, v)) {
            adjacencyList.get(u).add(v);
        }
    }

    boolean edgeExists(int u, int v) {
        return adjacencyList.get(u).contains(v);
    }
}
