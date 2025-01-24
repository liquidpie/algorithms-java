package com.vivek.graph;

import java.util.*;

/**
 * In this problem, starting locations are at the top and destinations are at the bottom i.e. the graph is
 * directed exclusively downwards
 *
 *        A         E        J
 *      /  \      /  \        \
 *     B   C     F    L        M
 *      \ / \  /
 *       k   G
 *          / \
 *         H   I
 *
 *  paths = {
 *      {"B", "K"},
 *      {"C", "K"},
 *      {"E", "L"},
 *      {"F", "G"},
 *      {"J", "M"},
 *      {"E", "F"},
 *      {"C", "G"},
 *      {"A", "B"},
 *      {"A", "C"},
 *      {"G", "H"},
 *      {"G", "I"}
 *  }
 *
 *  Output: (unordered)
 *  [
 *      "A" : ["K", "H", "I"],
 *      "E" : ["H", "L", "I"],
 *      "J" : ["M"]
 *  ]
 */
public class LeafNodesForMultipleSources {

    public static void main(String[] args) {
        String[][] paths = {
                      {"B", "K"},
                      {"C", "K"},
                      {"E", "L"},
                      {"F", "G"},
                      {"J", "M"},
                      {"E", "F"},
                      {"C", "G"},
                      {"A", "B"},
                      {"A", "C"},
                      {"G", "H"},
                      {"G", "I"}
        };

        var result = findLeafNodes(paths);
        System.out.println(result);

    }

    public static Map<String, List<String>> findLeafNodes(String[][] paths) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegreeMap = new HashMap<>();

        for (String[] path : paths) {
            indegreeMap.putIfAbsent(path[0], 0);
            indegreeMap.put(path[1], indegreeMap.getOrDefault(path[1], 0) + 1);
            graph.computeIfAbsent(path[0], k -> new ArrayList<>()).add(path[1]);
        }

        Map<String, List<String>> leafNodes = new HashMap<>();

        // find source nodes
        for (Map.Entry<String, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0)
                leafNodes.put(entry.getKey(), new ArrayList<>());
        }

        for (String source : leafNodes.keySet()) {
            List<String> nodes = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            dfs(source, visited, graph, nodes);
            leafNodes.put(source, nodes);
        }

        return leafNodes;
    }

    static void dfs(String source, Set<String> visited, Map<String, List<String>> graph, List<String> nodes) {
        if (!graph.containsKey(source) && !visited.contains(source)) {
            nodes.add(source);
        }

        visited.add(source);

        for (String node : graph.getOrDefault(source, List.of())) {
            dfs(node, visited, graph, nodes);
        }
    }

}
