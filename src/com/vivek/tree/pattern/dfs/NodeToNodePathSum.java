package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Node to Node Count Paths for a Sum (medium)
 *
 * Given a binary tree and a number ‘S’,
 * find all paths in the tree such that the sum of all the node values of each path equals ‘S’.
 * Please note that the paths can start or end at any node
 * but all paths must follow direction from parent to child (top to bottom).
 *
 * Example:
 *        1
 *     /    \
 *    7      9
 *   / \    / \
 *  6   5  2   3
 *
 * S: 12
 * Output: 3
 * Explanation:
 * There are three paths with sum '12': 7 -> 5, 1 -> 9 -> 2, and 9 -> 3
 *
 * Solution #
 * This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. But there will
 * be four differences:
 * 1. We will keep track of the current path in a list which will be passed to every recursive call.
 * 2. Whenever we traverse a node we will do two things:
 *      - Add the current node to the current path.
 *      - As we added a new node to the current path, we should find the sums of all sub-paths ending at the current node.
 *        If the sum of any sub-path is equal to ‘S’ we will increment our path count.
 * 3. We will traverse all paths and will not stop processing after finding the first path.
 * 4. Remove the current node from the current path before returning from the function. This is needed to
 * Backtrack while we are going up the recursive call stack to process other paths.
 *
 * Time Complexity #
 * The time complexity of the above algorithm is O(N2) in the worst case, where ‘N’ is the total number of nodes in the tree.
 * This is due to the fact that we traverse each node once, but for every node, we iterate the current path.
 * The current path, in the worst case, can be O(N ) (in the case of a skewed tree).
 * But, if the tree is balanced, then the current path will be equal to the height of the tree, i.e., O(logN).
 * So the best case of our algorithm will be O(NlogN).
 *
 * Space complexity #
 * The space complexity of the above algorithm will be O(N). This space will be used to store the recursion stack.
 * The worst case will happen when the given tree is a linked list (i.e., every node has only one child).
 * We also need O(N ) space for storing the currentPath in the worst case.
 * Overall space complexity of our algorithm is O(N).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: DFS
 */
public class NodeToNodePathSum {

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(4);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        System.out.println("Tree has path: " + countPaths(root, 11));
    }

    static int countPaths(Node root, int sum) {
        List<Integer> currentPath = new ArrayList<>();
        return countPathsRecursive(root, sum, currentPath);
    }

    private static int countPathsRecursive(Node currentNode, int sum, List<Integer> currentPath) {
        if (currentNode == null)
            return 0;

        // add the current node to the path
        currentPath.add(currentNode.data);
        int pathCount = 0;
        int pathSum = 0;
        // find the sums of all sub paths in the current path list
        ListIterator<Integer> pathIter = currentPath.listIterator(currentPath.size());
        while (pathIter.hasPrevious()) {
            pathSum += pathIter.previous();
            // if the sum of any sub path is equal to sum, we increment our path count
            if (pathSum == sum)
                pathCount++;
        }

        pathCount += countPathsRecursive(currentNode.left, sum, currentPath);
        pathCount += countPathsRecursive(currentNode.right, sum, currentPath);

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
        return pathCount;
    }

}
