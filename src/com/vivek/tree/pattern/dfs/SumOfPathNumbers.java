package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

/**
 * Sum of Path Numbers (medium)
 *
 * Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a
 * number. Find the total sum of all the numbers represented by all paths.
 *
 * Example:
 *
 *       1
 *      / \
 *    7     9
 *         / \
 *        2   9
 *
 * Output: 408
 * Explanation: The sum of all path numbers: 17 + 192 + 199
 *
 * Solution #
 * This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. The
 * additional thing we need to do is to keep track of the number representing the current path.
 * How do we calculate the path number for a node? Taking the first example mentioned above, say we are at node ‘7’.
 * As we know, the path number for this node is ‘17’, which was calculated by: 1 * 10 + 7 => 17 .
 * We will follow the same approach to calculate the path number of each node.
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree. This
 * is due to the fact that we traverse each node once.
 *
 * Space complexity #
 * The space complexity of the above algorithm will be O(N ) in the worst case.
 * This space will be used to store the recursion stack.
 * The worst case will happen when the given tree is a linked list (i.e., every node has only one child).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: DFS
 */
public class SumOfPathNumbers {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(0);
        root.right = new Node(1);
        root.left.left = new Node(1);
        root.right.left = new Node(6);
        root.right.right = new Node(5);
        System.out.println("Total Sum of Path Numbers: " + findSum(root));
    }

    static int findSum(Node root) {
        return findRootToLeafPathNumbers(root, 0);
    }

    private static int findRootToLeafPathNumbers(Node currentNode, int pathSum) {
        if (currentNode == null)
            return 0;

        // calculate the path number of the current node
        pathSum = 10 * pathSum + currentNode.data;

        // if the current node is a leaf, return the current path sum.
        if (currentNode.left == null && currentNode.right == null)
            return pathSum;

        // traverse the left and the right sub-tree
        return findRootToLeafPathNumbers(currentNode.left, pathSum) +
                findRootToLeafPathNumbers(currentNode.right, pathSum);
    }

}
