package com.vivek.string;

import com.vivek.linkedlist.DllNode;
import com.vivek.linkedlist.DoublyLinkedList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Find the first non-repeating character from a stream of characters
 *
 * Given a stream of characters, find the first non-repeating character from the stream. You need to tell the first non-repeating character in O(1) time at any moment.
 *
 * Example:
 *
 *  a b c c a a a c d b e e f g h d …
 *
 *  Ans: a a a a b b b b b d d d d d d f ...
 *
 * Reference: https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
 *
 */
public class FirstNonRepeatingCharInStream {

    /*
        Uses queue and lookup array. Front of the queue stores non-repeating char
        Space Complexity: O(1) as it uses constant space
        Time Complexity: O(n * 26) where max 26 chars could be anytime in queue to perform remove operation
     */
    static void findFirstNonRepeatingChar(char[] stream) {
        Queue<Character> queue = new LinkedList<>();
        boolean[] lookup = new boolean[26];
        for (char ch : stream) {
            if (!lookup[ch - 'a']) {
                queue.add(ch);
                lookup[ch - 'a'] = true;
            } else {
                if (!queue.isEmpty()) {
                    queue.remove(ch);
                }
            }
            System.out.print(queue.peek() + " ");
        }
    }

    /*
        Uses DoublyLinkedList along with Hashtable lookup
        Space Complexity: O(1) as it uses constant space
        Time Complexity: O(n)
     */
    static void findFirstNonRepeatingCharByDll(char[] stream) {
        DoublyLinkedList<Character> dll = new DoublyLinkedList<>();
        Map<Character, DllNode<Character>> lookup = new HashMap<>();

        for (char ch : stream) {
            if (!lookup.containsKey(ch)) {
                DllNode<Character> node = dll.append(ch);
                lookup.put(ch, node);
            } else {
                DllNode<Character> node = lookup.get(ch);
                dll.remove(node);
            }
            System.out.print(dll.getFirst() + " ");
        }
    }

    public static void main(String[] args) {
        char[] stream = {'a', 'b', 'c', 'c', 'a', 'a', 'a', 'c', 'd', 'b', 'e', 'e', 'f', 'g', 'h', 'd'};
        findFirstNonRepeatingCharByDll(stream);
    }


}
