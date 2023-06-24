package com.vivek.tree.bst;

import com.vivek.tree.Node;
import com.vivek.tree.Traversal;
import com.vivek.tree.TreeProperties;

/**
 * Split a BST into two balanced BSTs based on a value K
 *
 * Given a Binary Search tree and an integer K, we have to split the tree into two Balanced Binary Search Tree, where BST-1 consists of all the nodes which are less than K and BST-2 consists of all the nodes which are greater than or equal to K.
 * Note: The arrangement of the nodes may be anything but both BST should be Balanced.
 *
 * Examples:
 *
 * Input:
 *          40
 *         /   \
 *       20     50
 *      /  \      \
 *     10   35     60
 *         /      /
 *       25      55
 * K = 35
 * Output:
 * First BST: 10 20 25
 * Second BST: 35 40 50 55 60
 * Explanation:
 * After splitting above BST
 * about given value K = 35
 * First Balanced Binary Search Tree is
 *          20
 *         /   \
 *       10     25
 * Second Balanced Binary Search Tree is
 *          50
 *         /   \
 *       35     55
 *         \      \
 *          40     60
 * OR
 *          40
 *         /   \
 *       35     55
 *             /   \
 *            50    60
 *
 * Input:
 *          100
 *         /   \
 *       20     500
 *      /  \
 *     10   30
 *            \
 *             40
 * K = 50
 * Output:
 * First BST: 10 20 30 40
 * Second BST: 100 500
 * Explanation:
 * After splitting above BST
 * about given value K = 50
 * First Balanced Binary Search Tree is
 *          20
 *         /   \
 *       10     30
 *                 \
 *                  40
 * Second Balanced Binary Search Tree is
 *          100
 *             \
 *              500
 *
 * Approach:
 *
 * First store the inorder traversal of given BST in an array
 * Then, split this array about given value K
 * Now construct first balanced BST by first splitting part and second BST by second splitting part, using the approach used in this article.
 *
 * Time Complexity: where n is the number of nodes in the BST. This is because the function storeInorder() traverses through
 * all the nodes of the BST inorder and the function createBST() traverses through all the nodes of the balanced BST that it creates.
 *
 * Auxiliary Space: where n is the number of nodes in the BST. This is because an array of size n is created to store the
 * inorder traversal of the BST. Additionally, a new balanced BST is created from the inorder traversal, which also requires space.
 *
 * https://www.geeksforgeeks.org/split-a-bst-into-two-balanced-bsts-based-on-a-value-k/
 */
public class SplitBST {

    static int index;

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(8);

        splitBST(root, 5);
    }

    static void splitBST(Node root, int k) {
        // store the size of the BST
        int numNode = BSTProperties.sizeOfTree(root);

        // Take auxiliary array for storing the inorder traversal of BST
        int[] inOrder = new int[numNode + 1];
        index = 0;

        // Function call for storing inorder traversal of BST
        storeInorder(root, inOrder);

        // Function call for getting splitting index
        int splitIndex = getSplittingIndex(inOrder, k);

        Node root1 = null;
        Node root2 = null;

        // Creation of first Balanced Binary Search Tree
        if (splitIndex != -1)
            root1 = SortedArrayToBST.createBST(inOrder, 0, splitIndex);

        // Creation of Second Balanced Binary Search Tree
        if (splitIndex != (index - 1))
            root2 = SortedArrayToBST.createBST(inOrder, splitIndex + 1, index - 1);

        Traversal traversal = new Traversal();
        System.out.println("First BST : ");
        traversal.inOrder(root1);
        System.out.println();
        System.out.println("Second BST : ");
        traversal.inOrder(root2);
    }

    // Function to return the splitting index of the array
    static int getSplittingIndex(int[] inOrder, int k) {
        for (int i = 0; i < index; i++) {
            if (inOrder[i] >= k) {
                return i - 1;
            }
        }
        return index - 1;
    }

    static void storeInorder(Node root, int[] inOrder) {
        // Base condition
        if (root == null) {
            return;
        }

        // Left recursive call
        storeInorder(root.left, inOrder);

        // Store elements in inorder array
        inOrder[index++] = root.data;

        // Right recursive call
        storeInorder(root.right, inOrder);
    }

}
