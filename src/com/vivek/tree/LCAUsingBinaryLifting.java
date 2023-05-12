package com.vivek.tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * LCA in a tree using Binary Lifting Technique
 *
 * Given a binary tree, the task is to find the Lowest Common Ancestor of the given two nodes in the tree.
 * Let G be a tree then LCA of two nodes u and v is defined as the node w in the tree which is an ancestor of both u
 * and v and is farthest from the root node.If one node is the ancestor of another one than that particular node
 * is the LCA of those two nodes.
 *
 * Approach:
 * Binary Lifting is a dynamic programming approach where we pre-compute an array memo[1, n][1, log(n)] where memo[i][j]
 * contains 2^j-th ancestor of node i. For computing the values of memo[][], the following recursion may be used
 *
 *     memo state:
 *
 *        memo[i][j] = i-th node’s 2^(j)th ancestor in the path
 *
 *     memo initialization:
 *
 *        memo[i][j] = memo[i][0] (first parent (2^0) of each node is given)
 *
 *     memo trans:
 *
 *        memo[i][j] = memo[memo[i][j – 1]][j - 1]
 *
 *     meaning: A(i,2^j)=A( A(i , 2^(j-1) ) , 2^(j-1) )
 *
 *     To find the (2^j)-th ancestor of i, recursively find i-th node’s 2^(j-1)th ancestor’s 2^(j-1)th ancestor. (2^(j) = 2^(j-1) + 2^(j-1))
 *
 *     So:
 *
 *     memo[i][j] = parent[i] if j = 0 and
 *     memo[i][j] = memo[memo[i][j – 1]][j – 1] if j > 0.
 *
 * We first check whether a node is an ancestor of other or not and if one node is ancestor of other than it is the LCA
 * of these two nodes otherwise we find a node which is not the common ancestor of both u and v and is highest
 * (i.e. a node x such that x is not the common ancestor of u and v but memo[x][0] is) in the tree.
 * After finding such a node (let it be x), we print the first ancestor of x i.e. memo[x][0] which will be the required LCA.
 *
 * Time Complexity: The time taken in pre-processing is O(NlogN) and every query takes O(logN) time.
 * So the overall time complexity of the solution is O(NlogN).
 * Auxiliary Space: O(NlogN)
 *
 * https://www.geeksforgeeks.org/lca-in-a-tree-using-binary-lifting-technique/
 */
public class LCAUsingBinaryLifting {

    // ArrayList to store tree
    static ArrayList<Integer> g[];
    static int memo[][], lev[], log;

    // Pre-processing to calculate values of memo[][]
    static void dfs(int u, int p) {
        // Using recursion formula to calculate
        // the values of memo[][]
        memo[u][0] = p;
        for (int i = 1; i <= log; i++)
            memo[u][i] = memo[memo[u][i - 1]][i - 1];
        for (int v : g[u]) {
            if (v != p) {

                // Calculating the level of each node
                lev[v] = lev[u] + 1;
                dfs(v, u);
            }
        }
    }

    // Function to return the LCA of nodes u and v
    static int lca(int u, int v) {
        // The node which is present farthest
        // from the root node is taken as u
        // If v is farther from root node
        // then swap the two
        if (lev[u] < lev[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Finding the ancestor of u
        // which is at same level as v
        for (int i = log; i >= 0; i--) {
            if ((lev[u] - (int)Math.pow(2, i)) >= lev[v])
                u = memo[u][i];
        }

        // If v is the ancestor of u
        // then v is the LCA of u and v
        if (u == v)
            return u;

        // Finding the node closest to the root which is
        // not the common ancestor of u and v i.e. a node
        // x such that x is not the common ancestor of u
        // and v but memo[x][0] is
        for (int i = log; i >= 0; i--) {
            if (memo[u][i] != memo[v][i]) {
                u = memo[u][i];
                v = memo[v][i];
            }
        }

        // Returning the first ancestor
        // of above found node
        return memo[u][0];
    }

    // Driver code
    public static void main(String args[]) {

        // Number of nodes
        int n = 9;
        g = new ArrayList[n + 1];

        // log(n) with base 2
        log = (int)Math.ceil(Math.log(n) / Math.log(2));
        memo = new int[n + 1][log + 1];

        // Stores the level of each node
        lev = new int[n + 1];

        // Initialising memo values with -1
        for (int i = 0; i <= n; i++)
            Arrays.fill(memo[i], -1);
        for (int i = 0; i <= n; i++)
            g[i] = new ArrayList<>();

        // Add edges
        g[1].add(2);
        g[2].add(1);
        g[1].add(3);
        g[3].add(1);
        g[1].add(4);
        g[4].add(1);
        g[2].add(5);
        g[5].add(2);
        g[3].add(6);
        g[6].add(3);
        g[3].add(7);
        g[7].add(3);
        g[3].add(8);
        g[8].add(3);
        g[4].add(9);
        g[9].add(4);
        dfs(1, 1);
        System.out.println("The LCA of 6 and 9 is " + lca(6, 9));
        System.out.println("The LCA of 5 and 9 is " + lca(5, 9));
        System.out.println("The LCA of 6 and 8 is " + lca(6, 8));
        System.out.println("The LCA of 6 and 1 is " + lca(6, 1));
    }

}
