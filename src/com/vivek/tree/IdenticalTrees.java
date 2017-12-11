package com.vivek.tree;

/**
 * Determine if Two Trees are Identical
 *
 * Two trees are identical when they have same data and arrangement of data is also same.
 *
 * To identify if two trees are identical, we need to traverse both trees simultaneously,
 * and while traversing we need to compare data and children of the trees.
 */
public class IdenticalTrees {

    static boolean areIdentical(Node node1, Node node2) {

        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 != null && node2 != null) {
            if (node1.data == node2.data) {
                // check subtrees
                return areIdentical(node1.left, node2.left) &&
                        areIdentical(node1.right, node2.right);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(2);

        Node root2 = new Node(10);
        root2.left = new Node(8);
        root2.right = new Node(2);
        root2.left.left = new Node(3);
        root2.left.right = new Node(5);
        root2.right.left = new Node(2);

        System.out.println(areIdentical(root, root2));
    }

}
