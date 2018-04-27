package com.vivek.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by VJaiswal on 07/03/17.
 */
public class KruskalMST {

    private List<GenericGraph.Edge<Integer>> tree;
    private int totalWeight;

    public List<GenericGraph.Edge<Integer>> getTree() {
        return tree;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void getMST(GenericGraph<Integer> graph) {
        final List<GenericGraph.Edge<Integer>> tree = new ArrayList<>();
        int weight = 0;

        Queue<GenericGraph.Edge<Integer>> edgeQueue = new PriorityQueue<>(graph.getEdges());

        UnionFind<Integer> uf = new UnionFind<>(graph);

        while (!edgeQueue.isEmpty()) {
            GenericGraph.Edge<Integer> edge = edgeQueue.poll();

            if (!uf.find(edge.getSrc(), edge.getDest())) {
                uf.union(edge.getSrc(), edge.getDest());
                tree.add(edge);
                weight += edge.getWeight();
            }
        }

        this.tree = tree;
        this.totalWeight = weight;
    }

}
