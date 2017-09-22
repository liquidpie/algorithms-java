package com.vivek.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by VJaiswal on 07/03/17.
 *
 * This data structure is used for Kruskal's Algorithm
 */
public class UnionFind<E extends Comparable<E>> {

    private HashMap<Graph.Vertex<E>, HashSet<Graph.Vertex<E>>> membershipMap =
            new HashMap<>();

    public UnionFind(Graph<E> graph) {
        for (Graph.Vertex<E> vertex : graph.getVertices()) {
            HashSet<Graph.Vertex<E>> set = new HashSet<>();
            set.add(vertex);
            membershipMap.put(vertex, set);
        }
    }

    public boolean find(Graph.Vertex<E> v1, Graph.Vertex<E> v2) {
        return membershipMap.get(v1) == membershipMap.get(v2);
    }

    public void union(Graph.Vertex<E> v1, Graph.Vertex<E> v2) {
        HashSet<Graph.Vertex<E>> firstSet = membershipMap.get(v1); // first set is the bigger set
        HashSet<Graph.Vertex<E>> secondSet = membershipMap.get(v2);

        // swap sets so that firstSet is always bigger
        if (secondSet.size() > firstSet.size()) {
            HashSet<Graph.Vertex<E>> temp = firstSet;
            firstSet = secondSet;
            secondSet = temp;
        }

        // changing part membership of each vertex from smaller set
        for (Graph.Vertex<E> v : secondSet) {
            membershipMap.put(v, firstSet);
        }

        firstSet.addAll(secondSet);

    }

}
