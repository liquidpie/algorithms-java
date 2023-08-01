package com.vivek.linkedlist;

/**
 * Swap Nodes in Pairs
 *
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 * Example 2:
 *
 * Input: head = []
 * Output: []
 *
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 * Solution:
 * I will be placing a dummy node before the head node so that the code we write can also be applicable to the head node also,
 * and we don't have to specifically write different conditions for the head node.
 *
 * 1. As mentioned earlier, we will place a dummyNode before the head node.
 * 2. Now, let the head be our currNode. That means the node with value 1 is the currNode, and we have to swap it with the node with value 2.
 *   So for this, we will also have to keep track of the node previous to the currNode, let it be prevNode,
 *   as it's next pointer value will have to change after we swap the currNode and the node next to currNode.
 * 3. Now the very first thing to do is change the next pointer of prevNode to point to currNode->next.
 *    Why?? Because in the answer we want the node with value 2 after the dummyNode. Right? So we will have to connect
 *    dummyNode (prevNode) to the node with value 2 (currNode->next). This means
 *         prevNode->next = currNode->next
 * 4. Now, in our finl answer node with value 1 should be at the place of node with value 2. So the next pointer of node
 *    with value 1 should point to whatever the node with value 2 is pointing to originally. That means we will have to
 *    change currNode->next to the next of next of prevNode, as currently prevNode is dummyNode, prevNode->next is node with value 2 and prevNode->next->next = next of node with value 2. This means
 *         currNode->next = prevNode->next->next
 * 5. Now, as in the answer the node with value 2 should point to node with value 1. That means
 *         prevNode->next->next = currNode
 * 6. After this iteration, nodes 1 and 2 will get swapped and our linked list will look like this.
 * 7. Now for the next iteration, we have to swap nodes with values 3 and 4. For that the prevNode should point to node
 *    with value 1 and the currNode should point to node with value 3. This means
 *         prevNode = currNode
 *         currNode = currNode->next
 * 8. We should stop this procedure when either there is no nodes left to swap or there is only one node left which cannot be swapped with any node.
 * 9. At the end, as we can see that our head of the list has been misplaced in the procedure of swapping,
 * so we can return dummyNode->next to return the swapped linked list.
 *
 * https://leetcode.com/problems/swap-nodes-in-pairs/solutions/1775033/swapping-nodes-not-just-the-values-visual-explanation-well-explained-c/
 *
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node result = swapPairsOptimized(head);
        Node.print(result);
    }

    static Node swapPairsOptimized(Node head) {
        if (head == null || head.next == null)
            return head;

        Node dummy = new Node(-1);
        Node prev = dummy;
        Node curr = head;

        while (curr != null && curr.next != null) {
            prev.next = curr.next;
            curr.next = prev.next.next;
            prev.next.next = curr;

            prev = curr;
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * Breaks the list into odd and even index based lists
     * And then merges it.
     *
     * Not optimal
     */
    static Node swapPairs(Node head) {
        if (head == null)
            return head;

        Node node = head;
        Node odd = node;
        Node oddHead = node;
        node = node.next;
        if (node == null)
            return head;
        Node even = node;
        Node evenHead = node;

        boolean isOdd = true;
        while (node != null) {
            node = node.next;
            if (isOdd) {
                odd.next = node;
                odd = odd.next;
            } else {
                even.next = node;
                even = even.next;
            }
            isOdd = !isOdd;
        }

        head = evenHead;
        evenHead = evenHead.next;
        Node node1 = head;
        isOdd = true;
        while (evenHead != null && oddHead != null) {
            if (isOdd) {
                node1.next = oddHead;
                oddHead = oddHead.next;
            } else {
                node1.next = evenHead;
                evenHead = evenHead.next;
            }
            node1 = node1.next;
            isOdd = !isOdd;
        }

        node1.next = oddHead;

        return head;
    }



}
