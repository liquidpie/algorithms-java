package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * All Paths for a Sum (medium)
 *
 * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values
 * of each path equals ‘S’.
 *
 * Example:
 *          1
 *     7        9
 *   4   5    2   7
 * Sum: 12
 * Output: There are two paths
 * 1) 1 -> 7 -> 4
 * 2) 1 -> 9 -> 2
 *
 * Solution #
 * This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. There will be
 * two differences:
 * 1. Every time we find a root-to-leaf path, we will store it in a list.
 * 2. We will traverse all paths and will not stop processing after finding the first path.
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(N2), where ‘N’ is the total number of nodes in the tree.
 * This is due to the fact that we traverse each node once (which will take O(N)),
 * and for every leaf node we might have to store its path which will take O(N).
 * We can calculate a tighter time complexity of O(NlogN) from the space complexity discussion below.
 *
 * Space complexity #
 * If we ignore the space required for the allPaths list, the space complexity of the above algorithm will be O(N ) in the worst case.
 * This space will be used to store the recursion stack.
 * The worst case will happen when the given tree is a linked list (i.e., every node has only one child).
 * How can we estimate the space used for the allPaths array?
 *
 * Here we have seven nodes (i.e., N = 7 ). Since, for binary trees, there exists only one path to reach any leaf node,
 * we can easily say that total root-to-leaf paths in a binary tree can’t be more than the number of leaves.
 * As we know that there can’t be more than N /2 leaves in a binary tree,
 * therefore the maximum number of elements in allPaths will be O(N/2) = O(N).
 * Now, each of these paths can have many nodes in them. For a balanced binary tree (like above),
 * each leaf node will be at maximum depth.
 * As we know that the depth (or height) of a balanced binary tree is O(logN) we can say that, at the most,
 * each path can have logN nodes in it. This means that the total size of the allPaths list will be O(N ∗ logN).
 * If the tree is not balanced, we will still have the same worst-case space complexity.
 *
 * From the above discussion, we can conclude that the overall space complexity of our algorithm is O(N ∗ logN).
 * Also from the above discussion, since for each leaf node, in the worst case, we have to copy log(N) nodes to store its path,
 * therefore the time complexity of our algorithm will also be O(N ∗ logN).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: DFS
 */
public class RootToLeafPathSumAllPaths {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(7);
        root.right = new Node(9);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(2);
        root.right.right = new Node(7);

        System.out.println(findPaths(root, 12));
    }

    static List<List<Integer>> findPaths(Node root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsRecursive(root, sum, paths, currentPath);
        return paths;
    }

    private static void findPathsRecursive(Node node, int sum, List<List<Integer>> paths, List<Integer> currentPath) {
        if (node == null)
            return;

        // add the current node to the path
        currentPath.add(node.data);

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (node.data == sum && node.left == null && node.right == null) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            findPathsRecursive(node.left, sum - node.data, paths, currentPath);
            findPathsRecursive(node.right, sum - node.data, paths, currentPath);
        }

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }
}
