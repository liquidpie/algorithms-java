package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

/**
 * Path With Given Sequence (medium)
 *
 * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given
 * tree.
 *
 * Example:
 *       1
 *     /  \
 *    7    9
 *       /   \
 *      2     9
 * Sequence: [1, 9, 9]
 * Output: true
 * Explanation: The tree has a path 1 -> 9 -> 9.
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree.
 * This is due to the fact that we traverse each node once.
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
public class RootToLeafPathWithGivenSequence {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(0);
        root.right = new Node(1);
        root.left.left = new Node(1);
        root.right.left = new Node(6);
        root.right.right = new Node(5);

        System.out.println(findPath(root, new int[]{1, 0, 1}));
    }

    static boolean findPath(Node root, int[] sequence) {
        if (root == null)
            return sequence.length == 0;

        return findPathRecursive(root, sequence, 0);
    }

    private static boolean findPathRecursive(Node node, int[] sequence, int sequenceIndex) {
        if (node == null)
            return false;

        if (sequenceIndex >= sequence.length || node.data != sequence[sequenceIndex])
            return false;

        // if the current node is a leaf, add it is the end of the sequence, we have found a path!
        if (node.left == null && node.right == null && sequenceIndex == sequence.length - 1)
            return true;

        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recusrive call return true
        return findPathRecursive(node.left, sequence, sequenceIndex + 1)
                || findPathRecursive(node.right, sequence, sequenceIndex + 1);
    }

}
