package com.vivek.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. All O`one Data Structure
 *
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 *     AllOne() Initializes the object of the data structure.
 *     inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 *     dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure.
 *     It is guaranteed that key exists in the data structure before the decrement.
 *     getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 *     getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 *
 * Note that each function must run in O(1) average time complexity.
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 *
 * Solution:
 * https://leetcode.com/problems/all-oone-data-structure/solutions/5828122/all-o-one-data-structure/
 *
 * We use a doubly linked list for this. Each node represents a frequency and holds all keys linked to that frequency.
 * This setup allows us to add and remove keys efficiently as their frequencies change.
 *
 *
 * Reference:
 * https://leetcode.com/problems/all-oone-data-structure
 */
public class AllO1DataStructure {

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "hello"
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "leet"
    }

    static class AllOne {

        private final Map<String, Entry> entryMap;
        private final DLL dll;

        public AllOne() {
            this.entryMap = new HashMap<>();
            this.dll = new DLL();
        }

        public void inc(String key) {
            if (entryMap.containsKey(key)) {
                Entry current = entryMap.get(key);
                current.keys.remove(key);
                Entry next = current.next;
                if (next == dll.tail || next.freq != current.freq + 1) {
                    next = dll.addAfter(current, current.freq + 1);
                }
                next.keys.add(key);
                entryMap.put(key, next);
                if (current.keys.isEmpty()) {
                    dll.remove(current);
                }
            } else {
                Entry entry = dll.getFirst();
                if (entry == null || entry.freq > 1) {
                    entry = new Entry(1);
                    dll.addToHead(entry);
                }
                entry.keys.add(key);
                entryMap.put(key, entry);
            }
        }

        public void dec(String key) {
            if (!entryMap.containsKey(key))
                return;

            Entry entry = entryMap.get(key);
            entry.keys.remove(key);
            if (entry.freq == 1) {
                entryMap.remove(key);
            } else {
                Entry prev = entry.prev;
                if (prev.freq == entry.freq - 1) {
                    prev.keys.add(key);
                    entryMap.put(key, prev);
                } else {
                    Entry newPrev = dll.addAfter(prev, entry.freq - 1);
                    newPrev.keys.add(key);
                    entryMap.put(key, newPrev);
                }
            }

            if (entry.keys.isEmpty()) {
                dll.remove(entry);
            }
        }

        public String getMaxKey() {
            if (dll.getLast() == null)
                return "";
            return dll.getLast().keys.iterator().next();
        }

        public String getMinKey() {
            if (dll.getFirst() == null)
                return "";
            return dll.getFirst().keys.iterator().next();
        }

        private static class Entry {
            private final int freq;
            private final Set<String> keys;
            private Entry prev, next;

            public Entry(int freq) {
                this.freq = freq;
                this.keys = new HashSet<>();
            }
        }

        private static class DLL {
            Entry head, tail;

            public DLL() {
                head = new Entry(0);
                tail = new Entry(0);
                head.next = tail;
                tail.prev = head;
            }

            Entry addAfter(Entry current, int freq) {
                Entry entry = new Entry(freq);
                Entry next = current.next;
                current.next = entry;
                next.prev = entry;
                entry.prev = current;
                entry.next = next;
                return entry;
            }

            void addToHead(Entry entry) {
                Entry next = head.next;
                head.next = entry;
                entry.prev = head;
                entry.next = next;
                next.prev = entry;
            }

            void remove(Entry entry) {
                Entry prev = entry.prev;
                Entry next = entry.next;
                prev.next = next;
                next.prev = prev;
            }

            Entry getFirst() {
                return head.next != tail ? head.next : null;
            }

            Entry getLast() {
                return tail.prev != head ? tail.prev : null;
            }
        }
    }

}
