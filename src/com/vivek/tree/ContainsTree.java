package com.vivek.tree;

/**
 * You have two very large binary trees: T1, with millions of nodes, and T2, with hundreds of nodes.
 * Create an algorithm to decide if T2 is a subtree of T1
 */
public class ContainsTree {

    /**
     * The treeMatch procedure visits each node in the small tree at most once and is called
     * no more than once per node of the large tree. Worst case runtime is at most O(n * m),
     * where n and m are the sizes of trees T1 and T2, respectively. If k is the number of occurrences of T2’s root in T1,
     * the worst case runtime can be characterized as O(n + k * m).
     */
    boolean containsTree(Node n1, Node n2) {
        if (n2 == null)
            return true;     // The empty tree is always a subtree
        return subTree(n1, n2);
    }

    boolean subTree(Node n1, Node n2) {
        if (n1 == null)
            return false;    // big tree empty & subtree still not found
        if (n1.data == n2.data) {
            return matchTree(n1, n2);
        }
        return subTree(n1.left, n2) || subTree(n1.right, n2);
    }

    boolean matchTree(Node n1, Node n2) {
        if (n2 == null && n1 == null)
            return true;     // nothing left in the subtree
        if (n1 == null || n2 == null)
            return false;    // big tree empty & subtree still not found
        if (n1.data != n2.data)
            return false;    // data doesn’t match

        return matchTree(n1.left, n2.left) && matchTree(n1.right, n2.right);
    }

}
