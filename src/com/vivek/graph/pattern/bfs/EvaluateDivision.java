package com.vivek.graph.pattern.bfs;

import java.util.*;

/**
 * 399. Evaluate Division
 *
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * note: x is undefined => -1.0
 *
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * Reference: https://leetcode.com/problems/evaluate-division
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        EvaluateDivision helper = new EvaluateDivision();

        List<List<String>> equations = List.of(List.of("a","b"), List.of("b","c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = List.of(List.of("a","c"), List.of("b","a"), List.of("a","e"), List.of("a","a"), List.of("x","x"));
        System.out.println(Arrays.toString(helper.calcEquation(equations, values, queries)));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> adj = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double value = values[i];

            adj.computeIfAbsent(equation.get(0), k -> new HashMap<>()).put(equation.get(0), 1.0);
            adj.computeIfAbsent(equation.get(1), k -> new HashMap<>()).put(equation.get(1), 1.0);

            adj.get(equation.get(0)).put(equation.get(1), value);
            adj.get(equation.get(1)).put(equation.get(0), 1 / value);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0);
            String end = query.get(1);

            result[i] = adj.containsKey(start) && adj.containsKey(end) ? bfs(adj, start, end) : -1.0;
//            result[i] = dfs(adj, start, end, new HashSet<>());
        }

        return result;
    }

    private double bfs(Map<String, Map<String, Double>> adj, String start, String end) {
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 1.0));
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (String neighbor : adj.get(current.vertex).keySet()) {
                double newValue = current.value * adj.get(current.vertex).get(neighbor);
                if (neighbor.equals(end)) {
                    return newValue;
                }
                else if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, newValue));
                    visited.add(neighbor);
                }
            }
        }

        return -1.0;
    }

    /**
     * dfs solution: https://leetcode.com/problems/evaluate-division/solutions/171649/1ms-dfs-with-explanations-by-gracemeng-podz/?envType=study-plan-v2&envId=top-interview-150
     */
    private double dfs(Map<String, Map<String, Double>> adj, String start, String end, Set<String> visited) {
        if (!adj.containsKey(start))
            return -1.0;

        if (adj.get(start).containsKey(end))
            return adj.get(start).get(end);

        visited.add(start);
        for (Map.Entry<String, Double> neighbor : adj.get(start).entrySet()) {
            if (!visited.contains(neighbor.getKey())) {
                double productWeight = dfs(adj, neighbor.getKey(), end, visited);
                if (productWeight != -1.0)
                    return productWeight * neighbor.getValue();
            }
        }
        return -1.0;
    }

    static class Node {
        String vertex;
        double value;

        Node(String vertex, double value) {
            this.vertex = vertex;
            this.value = value;
        }
    }

}
