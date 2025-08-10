package com.vivek.linkedlist;

/**
 * 160. Intersection of Two Linked Lists
 *
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 *
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 *
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Reference:
 * https://leetcode.com/problems/intersection-of-two-linked-lists
 */
public class IntersectionOfLinkedLists {

    /**
     * I found most solutions here preprocess linkedlists to get the difference in len.
     * Actually we don't care about the "value" of difference, we just want to make sure two pointers reach the intersection node at the same time.
     *
     * We can use two iterations to do that. In the first iteration, we will reset the pointer of one linkedlist
     * to the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two pointers until
     * they points to the same node. Our operations in first iteration will help us counteract the difference.
     * So if two linkedlist intersects, the meeting point in second iteration must be the intersection point.
     * If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null.
     *
     *
     * Visualization of this solution:
     * Case 1 (Have Intersection & Same Len):
     *
     *        a
     * A:     a1 → a2 → a3
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *        b
     *
     *             a
     * A:     a1 → a2 → a3
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *             b
     *
     *                  a
     * A:     a1 → a2 → a3
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *                  b
     *
     * A:     a1 → a2 → a3
     *                    ↘ a
     *                      c1 → c2 → c3 → null
     *                    ↗ b
     * B:     b1 → b2 → b3
     *
     * Since a == b is true, end loop while(a != b), return the intersection node a = c1.
     *
     * Case 2 (Have Intersection & Different Len):
     *
     *             a
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *        b
     *
     *                  a
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *             b
     *
     * A:          a1 → a2
     *                    ↘ a
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *                  b
     *
     * A:          a1 → a2
     *                    ↘      a
     *                      c1 → c2 → c3 → null
     *                    ↗ b
     * B:     b1 → b2 → b3
     *
     * A:          a1 → a2
     *                    ↘           a
     *                      c1 → c2 → c3 → null
     *                    ↗      b
     * B:     b1 → b2 → b3
     *
     * A:          a1 → a2
     *                    ↘                a = null, then a = b1
     *                      c1 → c2 → c3 → null
     *                    ↗           b
     * B:     b1 → b2 → b3
     *
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗                b = null, then b = a1
     * B:     b1 → b2 → b3
     *        a
     *
     *             b
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *             a
     *
     *                  b
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *                  a
     *
     * A:          a1 → a2
     *                    ↘ b
     *                      c1 → c2 → c3 → null
     *                    ↗ a
     * B:     b1 → b2 → b3
     *
     * Since a == b is true, end loop while(a != b), return the intersection node a = c1.
     *
     * Case 3 (Have No Intersection & Same Len):
     *
     *        a
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *        b
     *
     *             a
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *             b
     *
     *                  a
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *                  b
     *
     *                       a = null
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *                       b = null
     *
     * Since a == b is true (both refer to null), end loop while(a != b), return a = null.
     *
     * Case 4 (Have No Intersection & Different Len):
     *
     *        a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *        b
     *
     *             a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *             b
     *
     *                  a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                  b
     *
     *                       a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                       b = null, then b = a1
     *
     *        b                   a = null, then a = b1
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *
     *             b
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *        a
     *
     *                  b
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *             a
     *
     *                       b
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                  a
     *
     *                            b = null
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                       a = null
     *
     * Since a == b is true (both refer to null), end loop while(a != b), return a = null.
     *
     * Notice that if list A and list B have the same length, this solution will terminate in no more than 1 traversal;
     * if both lists have different lengths, this solution will terminate in no more than 2 traversals -- in the second traversal,
     * swapping a and b synchronizes a and b before the end of the second traversal.
     * By synchronizing a and b I mean both have the same remaining steps in the second traversal so that it's guaranteed
     * for them to reach the first intersection node, or reach null at the same time (technically speaking, in the same iteration)
     * -- see Case 2 (Have Intersection & Different Len) and Case 4 (Have No Intersection & Different Len).
     */
    public Node getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null)
            return null;

        Node a = headA;
        Node b = headB;

        while (a != b) {
            a = a != null ? a.next : headB;
            b = b != null ? b.next : headA;
        }
        return a;
    }

}
