package com.vivek.graph.examples;

import com.vivek.graph.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Multiple routes a city and each route results in a throughput
 * Which route gives maximum throughput?
 * Certain routes have a limit
 *
 * 150 Kgs of weight, but certain route will allow only 100kgs
 *
 * A(1) <-(100)-> B(2) <-(50)-> C(3)
 * A(1) <-(120)-> C(3)
 *
 * Todo
 *
 * Asked in MS
 *
 */
public class MaxThroughputRoute {


    static void findRoute(Graph graph, int start, int end, int weight) {
        Set<Integer> route = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        route.add(start);
        // dfs(graph, start, end, weight, visited, route);

    }


}
