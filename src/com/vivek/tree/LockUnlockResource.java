package com.vivek.tree;

/**
 * Created by VJaiswal on 21/06/18.
 *
 * Given an n-ary tree of resources arranged hierarchically such that height of tree is O(Log N) where N is total number of nodes (or resources). A process needs to lock a resource node in order to use it. But a node cannot be locked if any of its descendant or ancestor is locked.

 Following operations are required for a given resource node:

 islock()- returns true if a given node is locked and false if it is not. A node is locked if lock() has successfully executed for the node.
 Lock()- locks the given node if possible and updates lock information. Lock is possible only if ancestors and descendants of current node are not locked.
 unLock()- unlocks the node and updates information.

 islock()  O(1)
 Lock()    O(log N)
 unLock()  O(log N)

 The idea is to augment tree with following three fields:

 A boolean field isLocked (same as above method).
 Parent-Pointer to access all ancestors in O(Log n) time.
 Count-of-locked-descendants. Note that a node can be ancestor of many descendants. We can check if any of the descendants is locked using this count.

 Let us see how operations work using this Approach.

 isLock(): Check isLocked of the given node.
 Lock(): Traverse all ancestors. If none of the ancestors is locked, Count-of-locked-descendants is 0 and isLocked is false, set isLocked of current node as true. And increment Count-of-locked-descendants for all ancestors. Time complexity is O(Log N) as there can be at most O(Log N) ancestors.
 unLock(): Traverse all ancestors and decrease Count-of-locked-descendants for all ancestors. Set isLocked of current node as false. Time complexity is O(Log N)


 https://www.geeksforgeeks.org/locking-and-unlocking-of-resources-in-the-form-of-n-ary-tree/
 */
public class LockUnlockResource {

    private static final int N_ARY = 3;

    public static boolean isLocked(ResourceNode node) {
        return node.isLocked;
    }

    public static void lock(ResourceNode node) {
        // lock validation
        if (node.isLocked)
            return;

        if (node.lockedDescendentsCount != 0) {
            System.out.println("Can't be locked : " + node.data + ", as a descendent is locked");
            return;
        }

        ResourceNode parent = node.parent;
        while (parent != null) {
            if (parent.isLocked) {
                System.out.println("Can't be locked : " + node.data + ", as an ancestor is locked");
                return;
            }
            parent = parent.parent;
        }

        // Resource could be locked
        // Set lockedDescendentsCount for ancesters
        ResourceNode ancestor = node.parent;
        while (ancestor != null) {
            ancestor.lockedDescendentsCount += 1;
            ancestor = ancestor.parent;
        }

        node.isLocked = true;
        System.out.println("Locked : " + node.data);
    }

    public static void unlock(ResourceNode node) {
        if (!node.isLocked)
            return;

        ResourceNode parent = node.parent;
        while (parent != null) {
            parent.lockedDescendentsCount -= 1;
            parent = parent.parent;
        }

        node.isLocked = false;
        System.out.println("Unlocked : " + node.data);
    }


    private static class ResourceNode {
        int data;
        ResourceNode[] children;
        ResourceNode parent;
        int lockedDescendentsCount;
        boolean isLocked;
        int childrenSize;

        ResourceNode(int data, ResourceNode parent) {
            this.data = data;
            this.parent = parent;
            children = new ResourceNode[N_ARY];
        }

        ResourceNode child(ResourceNode node) {
            children[childrenSize++] = node;
            return this;
        }
    }

    public static void main(String[] args) {
        ResourceNode root = new ResourceNode(1, null);
        ResourceNode rootChild1 = new ResourceNode(2, root);
        ResourceNode rootChild2 = new ResourceNode(3, root);
        ResourceNode rootChild3 = new ResourceNode(4, root);
        ResourceNode child1Child1 = new ResourceNode(5, rootChild1);
        ResourceNode child1Child2 = new ResourceNode(6, rootChild1);
        ResourceNode child1Child3 = new ResourceNode(7, rootChild1);
        ResourceNode child2Child1 = new ResourceNode(8, rootChild2);
        ResourceNode child2Child2 = new ResourceNode(9, rootChild2);
        ResourceNode child2Child3 = new ResourceNode(10, rootChild2);
        ResourceNode child3Child1 = new ResourceNode(11, rootChild3);
        ResourceNode child3Child2 = new ResourceNode(12, rootChild3);
        ResourceNode child3Child3 = new ResourceNode(13, rootChild3);

        root.child(rootChild1).child(rootChild2).child(rootChild3);
        rootChild1.child(child1Child1).child(child1Child2).child(child1Child3);
        rootChild2.child(child2Child1).child(child2Child2).child(child2Child3);
        rootChild3.child(child3Child1).child(child3Child2).child(child3Child3);

        lock(root);
        unlock(root);
        lock(rootChild3);
        lock(rootChild1);
        lock(child2Child2);
        lock(root);

    }

}
