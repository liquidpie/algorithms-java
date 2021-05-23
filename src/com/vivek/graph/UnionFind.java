package com.vivek.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by VJaiswal on 07/03/17.
 *
 * This data structure is used for Kruskal's Algorithm
 */
public class UnionFind<E extends Comparable<E>> {

    private final HashMap<GenericGraph.Vertex<E>, HashSet<GenericGraph.Vertex<E>>> membershipMap =
            new HashMap<>();

    public UnionFind(GenericGraph<E> graph) {
        for (GenericGraph.Vertex<E> vertex : graph.getVertices()) {
            HashSet<GenericGraph.Vertex<E>> set = new HashSet<>();
            set.add(vertex);
            membershipMap.put(vertex, set);
        }
    }

    public boolean find(GenericGraph.Vertex<E> v1, GenericGraph.Vertex<E> v2) {
        return membershipMap.get(v1) == membershipMap.get(v2);
    }

    public void union(GenericGraph.Vertex<E> v1, GenericGraph.Vertex<E> v2) {
        HashSet<GenericGraph.Vertex<E>> firstSet = membershipMap.get(v1); // first set is the bigger set
        HashSet<GenericGraph.Vertex<E>> secondSet = membershipMap.get(v2);

        // swap sets so that firstSet is always bigger
        if (secondSet.size() > firstSet.size()) {
            HashSet<GenericGraph.Vertex<E>> temp = firstSet;
            firstSet = secondSet;
            secondSet = temp;
        }

        // changing part membership of each vertex from smaller set
        for (GenericGraph.Vertex<E> v : secondSet) {
            membershipMap.put(v, firstSet);
        }

        firstSet.addAll(secondSet);

    }

}
