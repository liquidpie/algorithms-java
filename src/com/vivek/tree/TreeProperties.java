package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by VJaiswal on 23/11/17.
 */
public class TreeProperties {

    static int height(Node node) {
        if (node == null)
            return 0;
        int lHeight = height(node.left);
        int rHeight = height(node.right);

        return Math.max(lHeight, rHeight) + 1;
    }

    static int sumOfLeftSubtree(Node node) {
        if (node == null) {
            return 0;
        }

        int oldValue = node.data;
        node.data = oldValue + sumOfLeftSubtree(node.left);

        return node.data + sumOfLeftSubtree(node.right);
    }

    static Node mirrorOfTree(Node node) {
        if (node == null) {
            return node;
        }

        Node left = mirrorOfTree(node.left);
        Node right = mirrorOfTree(node.right);

        node.right = left;
        node.left = right;

        return node;
    }

    static int getLeafCount(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null)
            return 1;

        return getLeafCount(node.left) + getLeafCount(node.right);
     }

    /**
     * Count of nodes
     */
     static int size(Node node) {
        if (node == null)
            return 0;

        int lsize = size(node.left);
        int rsize = size(node.right);

        return lsize + rsize + 1;
     }

    /**
     * Root to leaf path sum equal to a given number
     */
     static boolean hasPathSum(Node node, int sum) {
         if (node == null) {
             return sum == 0;
         }

         boolean ans = false;
         int subsum = sum - node.data;

         if (subsum == 0 && node.left == null && node.right == null)
             return true;

         if (node.left != null) {
             ans = ans || hasPathSum(node.left, subsum);
         }
         if (node.right == null) {
             ans = ans || hasPathSum(node.right, subsum);
         }

         return ans;
     }

    /**
     * determine if a binary tree is height-balanced
     * An empty tree is height-balanced. A non-empty binary tree T is balanced if:
     *    1) Left subtree of T is balanced
     *    2) Right subtree of T is balanced
     *    3) The difference between heights of left subtree and right subtree is not more than 1.
     */
     static boolean isBalanced(Node node) {
         if (node == null)
             return true;

         int lheight = height(node.left);
         int rheight = height(node.right);

         if (Math.abs(lheight - rheight) <= 1 &&
                 isBalanced(node.left) &&
                 isBalanced(node.right))
             return true;

         return false;
     }

    /**
     * Given a binary tree, print all root-to-leaf paths
     */
    static void printPaths(Node node) {
        int path[] = new int[1000];
        printPathsRecur(node, path, 0);
    }

    private static void printPathsRecur(Node node, int[] path, int len) {
        if (node == null)
            return;

        path[len] = node.data;
        len++;
        if (node.left == null && node.right == null)
            System.out.println(path);

        printPathsRecur(node.left, path, len);
        printPathsRecur(node.right, path, len);
    }

    /**
     * find sum of all left leaves
     */
    static int sumLeftLeaves(Node node) {
        int sum = 0;

        if (node != null) {
            if (isLeaf(node.left)) {
                sum += node.left.data;
            } else {
                sum += sumLeftLeaves(node.left);
            }

            if (node.right != null) {
                sum += sumLeftLeaves(node.right);
            }
        }

        return sum;
    }

    static boolean isLeaf(Node node) {
        if (node == null)
            return false;

        return node.left == null && node.right == null;
    }

    static int diameter(Node node) {
        if (node == null)
            return 0;

        int lheight = height(node.left);
        int rheight = height(node.right);

        int ldiameter = diameter(node.left);
        int rdiameter = diameter(node.right);

        return Math.max(lheight + rheight + 1,
                Math.max(ldiameter, rdiameter));
    }

    /**
     * Start from the root and level as 1. If the key matches with root’s data, return level.
     * Else recursively call for left and right subtrees with level as level + 1.
     */
    static int levelOfNode(Node node, int data, int level) {
        if (node == null) {
            return 0;
        }

        if (node.data == data) {
            return level;
        }

        int leftLevel = levelOfNode(node.left, data, level + 1);
        if (leftLevel != 0)
            return leftLevel;

        int rightLevel = levelOfNode(node.right, data, level + 1);
        return rightLevel;
    }

    /**
     * Given a Binary Tree and a key, write a function that prints all the ancestors of
     * the key in the given binary tree.
     */
    static boolean printAncestors(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data)
            return true;

        if (printAncestors(node.left, data) ||
                printAncestors(node.right, data)) {
            System.out.print(data + " ");
        }

        return false;
    }

    static boolean isSubtreeOf(Node node1, Node node2) {
        if (node2 == null)
            return true;
        if (node1 == null)
            return false;

        if (IdenticalTrees.areIdentical(node1, node2)) {
            return true;
        }

        return isSubtreeOf(node1.left, node2) ||
                isSubtreeOf(node1.right, node2);
    }

    /**
     * Given a root of a tree, and an integer k.
     * Print all the nodes which are at k distance from root.
     */
    static void printKdistantNodes(Node node, int k) {
        if (node == null)
            return;

        if (k == 0) {
            System.out.print(node.data + " ");
        } else {
            printKdistantNodes(node.left, k - 1);
            printKdistantNodes(node.right, k - 1);
        }
    }

    /**
     * Given a Binary tree and a key in the binary tree,
     * find the node right to the given key.
     * If there is no node on right side, then return NULL.
     */
    static Node nextRightNodeOf(Node root, int data) {
        if (root == null)
            return null;

        // queue for level order traversal
        Queue<Node> qNode = new LinkedList<>();
        // queue to store level
        Queue<Integer> qLevel = new LinkedList<>();

        qNode.add(root);
        qLevel.add(0);

        while (!qNode.isEmpty()) {

            Node node = qNode.poll();
            int level = qLevel.poll();
            if (node.data == data) {
                if (!qNode.isEmpty() && qLevel.peek() == level) {
                    return qNode.peek();
                }
                return null;
            }

            if (node.left != null) {
                qNode.add(node.left);
                qLevel.add(level + 1);
            }

            if (node.right != null) {
                qNode.add(node.right);
                qLevel.add(level + 1);
            }
        }

        return null;
    }

    /**
     * Level order traversal can be modified to check whether a tree is Complete or not.
     */
    static boolean isCompleteBinaryTree(Node root) {
        if (root == null)
            return false;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        boolean isNotFull = false;

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            // Check if left child is present
            if (node.left != null) {
                // If we have seen a non full node, and we see a node
                // with non-empty left child, then the given tree is not
                // a complete Binary Tree
                if (isNotFull)
                    return false;
                queue.add(node.left);
            } else {
                isNotFull = true;
            }

            if (node.right != null) {
                // If we have seen a non full node, and we see a node
                // with non-empty right child, then the given tree is not
                // a complete Binary Tree
                if (isNotFull)
                    return false;
                queue.add(node.right);
            } else {
                isNotFull = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println(getLeafCount(root));
    }

}
