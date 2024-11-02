package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

import java.util.ArrayList;

/**
 * Root to leaf path sum equal to a given number
 *
 * Solution #
 * To recursively traverse a binary tree in a DFS fashion, we can start from the root and at every step,
 * make two recursive calls one for the left and one for the right child.
 *
 * Here are the steps for our Binary Tree Path Sum problem:
 * 1. Start DFS with the root of the tree.
 * 2. If the current node is not a leaf node, do two things:
 *
 * Subtract the value of the current node from the given number to get a new sum => S = S - node.value
 * Make two recursive calls for both the children of the current node with the new number calculated in the previous step.
 *
 * 3. At every step, see if the current node being visited is a leaf node and if its value is equal to the given number ‘S’.
 *    If both these conditions are true, we have found the required root-to-leaf path, therefore return true.
 * 4. If the current node is a leaf but its value is not equal to the given number ‘S’, return false.
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
public class RootToLeafPathSum {

    public static void main(String[] args) {
        Node root  = new Node(10);
        root.left  = new Node(28);
        root.right = new Node(13);

        root.right.left   = new Node(14);
        root.right.right  = new Node(15);

        root.right.left.left   = new Node(21);
        root.right.left.right  = new Node(22);
        root.right.right.left  = new Node(23);
        root.right.right.right = new Node(24);

        int sum = 38;

        System.out.println(rootToLeaf(root, sum));
    }

    static boolean rootToLeaf(Node node, int sum) {
        if (node == null) {
            return sum == 0;
        }

        if (node.data == sum && node.left == null && node.right == null)
            return true;

        return rootToLeaf(node.left, sum - node.data)
                || rootToLeaf(node.right, sum - node.data);
    }

}
