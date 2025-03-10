package com.vivek.tree;

/**
 * 1644. Lowest Common Ancestor of a Binary Tree II
 *
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
 * If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 *
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants
 * (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 *
 * Approach 1:
 * In the LCA - I solution, we recursively search for the common ancestor by checking the tree starting from the root.
 * The stopping condition for the recursion is when the root is either empty or matches one of the nodes (p or q). In that case, we return the root.
 *
 * This solution doesn't handle the cases where p or q might not be in the binary tree.
 *  If the root matches p, the recursion stops, and we don't check further in that subtree.
 *  Similarly, if q doesn't exist in the subtree of p, we won't know that q is missing, leading to incorrect results.
 *
 *  For example:
 *
 * If the method returns p as the common ancestor, we need to confirm if q is present in the subtree of p. If not, the result is incorrect.
 * Similarly, if the method returns q, we need to check if p is in the subtree of q.
 * If the method returns null, it means neither p nor q is in the tree.
 * This ensures that both nodes are present before confirming the common ancestor.
 *
 * Algorithm
 * Call LCA(root, p, q) to find the lowest common ancestor (LCA) of nodes p and q starting from root.
 *
 * If ans (the result of LCA) is p, check if q is in the subtree of p by calling dfs(p, q).
 * If dfs(p, q) returns true, return p as the answer.
 * If dfs(p, q) returns false, return null (indicating q is not in the subtree of p).
 * If ans is q, check if p is in the subtree of q by calling dfs(q, p).
 * If dfs(q, p) returns true, return q as the answer.
 * If dfs(q, p) returns false, return null (indicating p is not in the subtree of q).
 * Otherwise, return ans, the LCA found by LCA() function.
 *
 * LCA function:
 *
 * If node is null, or if node is either p or q, return node (base case).
 * Recursively call LCA(node.left, p, q) and LCA(node.right, p, q) to explore the left and right subtrees.
 * If both left and right subtrees contain one of p or q, return node as the LCA.
 * If only the left subtree contains one of p or q, return the result from the left subtree.
 * If only the right subtree contains one of p or q, return the result from the right subtree.
 *
 * dfs function:
 *
 * If node is the same as target, return true (found the target).
 * If node is null, return false (reached a leaf node without finding the target).
 * Recursively search for the target in both the left and right subtrees using logical OR.
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/editorial/
 *
 * Reference:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */
public class LCABinaryTreeII {

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        System.out.println(lowestCommonAncestor(root, root.left, null)); // null
    }

    static Node lowestCommonAncestor(Node root, Node p, Node q) {
        // Step 1: Find the lowest common ancestor of nodes p and q
        Node ans = lca(root, p, q);
        // Step 2: Check if the LCA is p, meaning q must be in p's subtree
        if (ans == p)
            // Verify if q is in the subtree of p
            return dfs(p, q) ? p : null;
        // Step 3: Check if the LCA is q, meaning p must be in q's subtree
        else if (ans == q)
            // Verify if p is in the subtree of q
            return dfs(p, q) ? q : null;
        // Step 4: If neither p nor q is the ancestor of the other, return the LCA
        return ans;
    }

    private static Node lca(Node root, Node p, Node q) {
        if (root ==  null)
            return null;

        if (root == p || root == q)
            return root;

        Node leftLCA = lowestCommonAncestor(root.left, p, q);
        Node rightLCA = lowestCommonAncestor(root.right, p, q);

        if (leftLCA != null && rightLCA != null)
            return root;

        return leftLCA != null ? leftLCA : rightLCA;
    }

    private static boolean dfs(Node root, Node node) {
        if (root == null)
            return false;

        if (root == node)
            return true;

        return dfs(root.left, node) || dfs(root.right, node);
    }

}
