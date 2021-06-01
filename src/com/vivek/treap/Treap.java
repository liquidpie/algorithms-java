package com.vivek.treap;

/**
 * Reference: https://www.geeksforgeeks.org/treap-set-2-implementation-of-search-insert-and-delete
 */
public class Treap {

    /**
     * Same as standard BST search. Priority is not considered for search
     */
    static Node search(Node root, int key) {
        if (root == null)
            return root;

        if (key == root.key)
            return root;

        if (key < root.key)
            return search(root.left, key);

        return search(root.right, key);
    }

    /**
     * 1) Create new node with key equals to x and value equals to a random value.
     * 2) Perform standard BST insert.
     * 3) A newly inserted node gets a random priority, so Max-Heap property may be violated. Use rotations to make sure that inserted node’s priority follows max heap property.
     *
     * During insertion, we recursively traverse all ancestors of the inserted node.
     * a) If new node is inserted in left subtree and root of left subtree has higher priority than root, perform right rotation.
     * b) If new node is inserted in right subtree and root of right subtree has higher priority than root, perform left rotation.
     */
    static Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key <= root.key) {
            root.left = insert(root.left, key);
            if (root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        } else {
            root.right = insert(root.right, key);
            if (root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    /**
     * The delete implementation here is slightly different from the steps discussed in README.
     * 1) If node is a leaf, delete it.
     * 2) If node has one child NULL and other as non-NULL, replace node with the non-empty child.
     * 3) If node has both children as non-NULL, find max priority of left and right children.
     * ….a) If priority of right child is greater, perform left rotation at node
     * ….b) If priority of left child is greater, perform right rotation at node.
     *
     * The idea of step 3 is to move the node to down so that we end up with either case 1 or case 2.
     */
    static Node delete(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            // If key is at root and both left and right are not NULL
            if (root.right.priority > root.left.priority) {
                root = leftRotate(root);
                root.left = delete(root.left, key);
            } else {
                root = rightRotate(root);
                root.right = delete(root.right, key);
            }
        }
        return root;
    }

    /*
    T1, T2 and T3 are subtrees of the tree rooted with y (on left side) or x (on right side)
                y                               x
               / \     Right Rotation          /  \
              x   T3   – – – – – – – >        T1   y
             / \       < - - - - - - -            / \
            T1  T2     Left Rotation            T2  T3
     */

    private static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        // rotation
        x.right = T2;
        y.left = x;
        // return new root
        return y;
    }

    private static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        // rotation
        x.right = y;
        y.left = T2;
        // return new root
        return x;
    }

    public static void main(String[] args) {
        Node root = null;
        root = insert(root, 50);
        root = insert(root, 30);
        root = insert(root, 20);
        root = insert(root, 40);
        root = insert(root, 70);
        root = insert(root, 60);
        root = insert(root, 80);

        System.out.println("Inorder traversal of the given tree");
        Traversal.inOrder(root);

        System.out.println("\nDelete 20");
        root = delete(root, 20);
        System.out.println("Inorder traversal of the modified tree");
        Traversal.inOrder(root);

        System.out.println("\nDelete 30");
        root = delete(root, 30);
        System.out.println("Inorder traversal of the modified tree");
        Traversal.inOrder(root);

        System.out.println("\nDelete 50");
        root = delete(root, 50);
        System.out.println("Inorder traversal of the modified tree");
        Traversal.inOrder(root);

        Node res = search(root, 50);
        System.out.println(res != null ? "50 found" : "50 Not Found");
    }

}
