package com.vivek.tree.bst;

import com.vivek.tree.Node;
import com.vivek.tree.Traversal;

/**
 * Two nodes of a BST are swapped, correct the BST
 *
 * Input Tree:
 *          10
 *         /  \
 *        5    8
 *       / \
 *      2   20
 *
 * In the above tree, nodes 20 and 8 must be swapped to fix the tree.
 * Following is the output tree
 *          10
 *         /  \
 *        5    20
 *       / \
 *      2   8
 *
 * The inorder traversal of a BST produces a sorted array. So a simple method is to store inorder traversal of the input tree in an auxiliary array.
 * Sort the auxiliary array. Finally, insert the auxiliary array elements back to the BST, keeping the structure of the BST same.
 * The time complexity of this method is O(nLogn) and the auxiliary space needed is O(n).
 *
 * We can solve this in O(n) time and with a single traversal of the given BST. Since inorder traversal of BST is always a sorted array, the problem can be reduced to a problem where two elements of a sorted array are swapped. There are two cases that we need to handle:
 * 1. The swapped nodes are not adjacent in the inorder traversal of the BST.
 *
 *  For example, Nodes 5 and 25 are swapped in {3 5 7 8 10 15 20 25}.
 *  The inorder traversal of the given tree is 3 25 7 8 10 15 20 5
 *
 * If we observe carefully, during inorder traversal, we find node 7 is smaller than the previous visited node 25. Here save the context of node 25 (previous node). Again, we find that node 5 is smaller than the previous node 20. This time, we save the context of node 5 (the current node ). Finally, swap the two node’s values.
 * 2. The swapped nodes are adjacent in the inorder traversal of BST.
 *
 *   For example, Nodes 7 and 8 are swapped in {3 5 7 8 10 15 20 25}.
 *   The inorder traversal of the given tree is 3 5 8 7 10 15 20 25
 *
 * Unlike case #1, here only one point exists where a node value is smaller than the previous node value. e.g. node 7 is smaller than node 8.
 *
 * How to Solve?
 * We will maintain three-pointers, first, middle, and last. When we find the first point where the current node value is smaller than the previous node value,
 * we update the first with the previous node & the middle with the current node.
 * When we find the second point where the current node value is smaller than the previous node value,
 * we update the last with the current node. In the case of #2, we will never find the second point.
 * So, the last pointer will not be updated. After processing, if the last node value is null, then two swapped nodes of BST are adjacent.
 *
 * Reference:
 * https://www.geeksforgeeks.org/fix-two-swapped-nodes-of-bst
 */
public class FixBstWithIncorrectNodes {

    static Node first, middle, last, prev;

    static void correctBST(Node root) {
        first = middle = last = prev = null;

        // set the pointers to find out two nodes
        correctBSTUtil(root);

        // swap not-adjacent nodes
        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        }
        // swap adjacent nodes
        else if (first != null && middle != null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    private static void correctBSTUtil(Node root) {
        if (root == null)
            return;

        correctBSTUtil(root.left);

        // If this node is smaller than the previous node, it's violating the BST rule
        if (prev != null && root.data < prev.data) {
            // If this is first violation, mark these two nodes as 'first' and 'middle'
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                // If this is second violation, mark this node as last
                last = root;
            }
        }

        // Mark this node as previous
        prev = root;

        correctBSTUtil(root.right);
    }

    public static void main(String[] args) {
        /*
                 6
                / \
               10  2
              / \ / \
             1  3 7 12

            10 and 2 are swapped
        */

        Node root = new Node(6);
        root.left = new Node(10);
        root.right = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.right = new Node(12);
        root.right.left = new Node(7);

        Traversal traversal = new Traversal();
        traversal.inOrder(root);

        correctBST(root);

        System.out.println();
        traversal.inOrder(root);
    }

}
