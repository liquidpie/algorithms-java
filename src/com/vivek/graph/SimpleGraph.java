package com.vivek.graph;

import java.util.LinkedList;

public class SimpleGraph {

    private final int v;  // number of vertices
    private final LinkedList<Integer>[] adjacency;

    public SimpleGraph(int v) {
        this.v = v;
        this.adjacency = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjacency[i] = new LinkedList<>();
        }
    }

    void addEdge(int u, int v) {
        adjacency[u].add(v);
    }

    public LinkedList<Integer>[] getAdjacency() {
        return adjacency;
    }

    public int getV() {
        return v;
    }
}
