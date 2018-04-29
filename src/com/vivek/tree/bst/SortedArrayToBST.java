package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * Given a sorted (increasing order) array, create a binary search tree
 */
public class SortedArrayToBST {

    Node createBST(int[] array) {
        return addToTree(array, 0, array.length);
    }

    Node addToTree(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(array[mid]);

        node.left = addToTree(array, start, mid - 1);
        node.right = addToTree(array, mid + 1, end);

        return node;
    }

}
