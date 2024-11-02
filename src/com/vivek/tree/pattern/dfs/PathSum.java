package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VJaiswal on 30/04/18.
 */
public class PathSum {

    /**
     * Root to any node path sum equal to a given number
     */
    boolean rootToNode(Node node, int sum) {
        if (node == null)
            return sum == 0;

        boolean ans = false;
        int subsum = sum - node.data;

        if (subsum == 0)
            return true;

        if (node.left != null) {
            ans = ans || rootToNode(node.left, subsum);
        }
        if (node.right != null) {
            ans = ans || rootToNode(node.right, subsum);
        }

        return ans;
    }

    /**
     * Print paths from Root to any node whose path sum equal to a given number
     */
    void printRootToNodePaths(Node node, List<Node> paths, int sum, int sumSoFar) {
        if (node == null)
            return;

        // add current node to paths
        paths.add(node);

        sumSoFar += node.data;

        // print the required path
        if (sumSoFar == sum) {
            printPaths(paths, 0);
        }

        printRootToNodePaths(node.left, paths, sum, sumSoFar);

        printRootToNodePaths(node.right, paths, sum, sumSoFar);

        // Remove the current element from the path
        paths.remove(paths.size() - 1);
    }


    /**
     * Print paths from Any node to any node whose path sum equal to a given number
     *
     * The basic idea to solve the problem is to do a preorder traversal of the given tree.
     * We also need a container (vector) to keep track of the path that led to that node.
     * At each node we check if there are any path that sums to k,
     * if any we print the path and proceed recursively to print each path.
     */
    void printNodeToNodePaths(Node node, List<Node> paths, int sum) {
        if (node == null)
            return;

        // add current node to paths
        paths.add(node);

        // check if there's any k sum path
        // in the left sub-tree.
        printNodeToNodePaths(node.left, paths, sum);

        // check if there's any k sum path
        // in the right sub-tree.
        printNodeToNodePaths(node.right, paths, sum);

        // check if there's any k sum path that
        // terminates at this node
        // Traverse the entire path as
        // there can be negative elements too
        int result = 0;
        for (int i = paths.size() - 1; i >= 0; i--) {
            result += paths.get(i).data;
            // If path sum is k, print the path
            if (result == sum)
                printPaths(paths, i);
        }

        // Remove the current element from the path
        paths.remove(paths.size() - 1);
    }

    void printPaths(List<Node> paths, int i) {
        for (int j = i; j < paths.size(); j++) {
            System.out.print(paths.get(j).data + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        PathSum pathSum = new PathSum();

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

        pathSum.rootToNode(root, sum);

    }

}
