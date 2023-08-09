package com.vivek.linkedlist;

/**
 * Merge K sorted linked lists
 *
 * Given K sorted linked lists of size N each, merge them and print the sorted output.
 *
 * Examples:
 *
 * Input: k = 3, n =  4
 * list1 = 1->3->5->7->NULL
 * list2 = 2->4->6->8->NULL
 * list3 = 0->9->10->11->NULL
 *
 * Output: 0->1->2->3->4->5->6->7->8->9->10->11
 * Merged lists in a sorted order
 * where every element is greater
 * than the previous element.
 *
 * Input: k = 3, n =  3
 * list1 = 1->3->7->NULL
 * list2 = 2->4->8->NULL
 * list3 = 9->10->11->NULL
 *
 * Output: 1->2->3->4->7->8->9->10->11
 * Merged lists in a sorted order
 * where every element is greater
 * than the previous element.
 *
 * Divide and Conquer approach is used
 *
 *  1. The idea is to pair up K lists and merge each pair in linear time using O(n) space.
 *  2. After the first cycle, K/2 lists are left each of size 2*N. After the second cycle, K/4 lists are left each of size 4*N and so on.
 *  3. Repeat the procedure until we have only one list left.
 *
 *  Complexity Analysis:
 *
 *     Assuming N(n*k) is the total number of nodes, n is the size of each linked list, and k is the total number of linked lists.
 *
 *     Time Complexity: O(N*log k) or O(n*k*log k)
 *     As outer while loop in function mergeKLists() runs log k times and every time it processes n*k elements.
 *     O(n * k * log k).  There are log K levels as in each level the K arrays are divided in half and at each level, the k arrays are traversed.
 *     Auxiliary Space: O(N) or O(n*k)
 *     Because recursion is used in SortedMerge() and to merge the final 2 linked lists of size N/2, N recursive calls will be made.
 *
 *  Reference: https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/
 *
 */
public class MergeKSortedLists {

    static Node mergeKLists(Node[] arr, int k) {
        if (k == 0)
            return null;
        int last = k - 1;
        while (last != 0) {
            int i = 0;
            int j = last;
            while (i < j) {
                // merge List i with List j and store merged list in List i
                arr[i] = sortedMerge(arr[i], arr[j]);

                i++;
                j--;

                if (i >= j)
                    last = j;
            }
        }
        return arr[0];
    }

    private static Node sortedMerge(Node a, Node b) {
        if (a == null)
            return b;
        else if (b == null)
            return a;

        Node result = null;

        if (a.data <= b.data) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    public static void main(String[] args) {
        int k = 3;
        Node[] arr = new Node[k];

        arr[0] = new Node(1);
        arr[0].next = new Node(3);
        arr[0].next.next = new Node(5);
        arr[0].next.next.next = new Node(7);

        arr[1] = new Node(2);
        arr[1].next = new Node(4);
        arr[1].next.next = new Node(6);
        arr[1].next.next.next = new Node(8);

        arr[2] = new Node(0);
        arr[2].next = new Node(9);
        arr[2].next.next = new Node(10);
        arr[2].next.next.next = new Node(11);

        // Merge all lists
        Node head = mergeKLists(arr, k);
        Node.print(head);
    }

}
