package com.vivek.graph.pattern.dfs;

import java.util.*;

/**
 * 547. Number of Provinces
 *
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * Example 1:
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * Example 2:
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 * Reference: https://leetcode.com/problems/number-of-provinces
 */
public class NumberOfProvinces {

    public static void main(String[] args) {
        int[][] isConnected = {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };

        NumberOfProvinces helper = new NumberOfProvinces();
        System.out.println(helper.findCircleNum(isConnected));
    }


    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] visited = new int[n];

        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                dfs(i, isConnected, visited);
                visited[i] = 1;
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int src, int[][] isConnected, int[] visited) {
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[src][neighbor] == 1 && visited[neighbor] == 0) {
                visited[neighbor] = 1;
                dfs(neighbor, isConnected, visited);
            }
        }
    }

}
