package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * Created by VJaiswal on 30/11/17.
 */
public class FloorCeilNode {

    /**
     * Node with greatest value smaller than or equal to the key provided
     */
    static int floor(Node node, int key) {
        if (node == null)
            return -1;

        if (node.data == key) {
            return node.data;
        }

        if (node.data > key) {
            return floor(node.left, key);
        }

        int floor = floor(node.right, key);
        return floor <= key ? floor : node.data;
    }

    /**
     * Node with smallest value larger than or equal to the key provided
     */
    static int ceil(Node node, int key) {

        if (node == null)
            return -1;

        if (node.data == key) {
            return node.data;
        }

        // If root's key is smaller, ceil must be in right subtree
        if (node.data < key) {
            return ceil(node.right, key);
        }

        // Else, either left subtree or root has the ceil value
        int ceil = ceil(node.left, key);
        return ceil >= key ? ceil : node.data;
    }

}
