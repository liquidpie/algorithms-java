package com.vivek.linkedlist.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LFU Cache with following methods: get(key), put(key, value) and resize(capacity)
 *
 * LFU evicts keys from the cache based on least frequent usage. However, in case of tie, it evicts keys which are least recently used
 *
 * Time Complexity:
 *  get : O(1)
 *  put : O(1)
 *  resize : O(1) or O(k) where k is number of evictions needed for downsizing cache
 *
 * Space Complexity: O(1) for all operations
 *
 * https://www.enjoyalgorithms.com/blog/least-frequently-used-cache
 */
public class LFUCache<K, V> {

    private final Map<K, Entry> cache;
    private final Map<Integer, DoublyLinkedList> freqList;
    private int capacity;
    private int minFreq;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.freqList = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    public V get(K key) {
        if (!cache.containsKey(key))
            return null;

        Entry entry = cache.get(key);
        updateFreq(entry);
        return entry.value;
    }

    public void put(K key, V value) {
        if (capacity == 0) // if capacity is set to zero, return immediately
            return;
        if (cache.containsKey(key)) {
            // update entry
            Entry entry = cache.get(key);
            updateFreq(entry);
            entry.value = value;
        } else {
            // insert entry
            if (cache.size() >= capacity) {
                evictEntry();
            }
            Entry newEntry = new Entry(key, value);
            cache.put(key, newEntry);
            freqList.computeIfAbsent(1, dll -> new DoublyLinkedList()).addNode(newEntry);
            minFreq = 1;
        }
    }

    public void resize(int newCapacity) {
        if (newCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        while (cache.size() > newCapacity) {
            evictEntry();
        }
        this.capacity = newCapacity;
    }

    private void updateFreq(Entry entry) {
        int currentFreq = entry.freq;
        // remove from previous freq list, in case of a tie, node with lru will be removed
        freqList.get(currentFreq).removeNode(entry);
        // update minFreq when old freq is equal to minFreq
        if (freqList.get(currentFreq).isEmpty() && minFreq == currentFreq) {
            minFreq++;
        }
        // increment freq
        entry.freq++;
        // add entry to freq list with new frequency
        freqList.computeIfAbsent(entry.freq, dll -> new DoublyLinkedList()).addNode(entry);
    }

    private void evictEntry() {
        if (minFreq == 0) // if minFreq is already 0, there's nothing to remove
            return;
        DoublyLinkedList dll = freqList.get(minFreq);
        Entry entry = dll.removeLast();
        if (dll.isEmpty()) {
            freqList.remove(minFreq);
            minFreq++;
        }
        cache.remove(entry.key);
    }

    private class Entry {
        K key;
        V value;
        int freq;
        Entry prev, next;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    /**
     * DLL which adds a new entry to the beginning of the list and removes entry from the end of the list
     * This simulates LRU in case of tie
     */
    private class DoublyLinkedList {
        Entry head, tail;
        private int size;
        DoublyLinkedList() {
            head = new Entry(null, null);
            tail = new Entry(null, null);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addNode(Entry entry) {
            Entry next = head.next;
            head.next = entry;
            entry.prev = head;
            entry.next = next;
            next.prev = entry;
            size++;
        }

        void removeNode(Entry entry) {
            Entry prev = entry.prev;
            Entry next = entry.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        Entry removeLast() {
            if (size > 0) {
                Entry entry = tail.prev;
                removeNode(entry);
                return entry;
            }
            return null;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    // Debugging method to display the current state of the cache
    public void displayCache() {
        System.out.println("Cache Contents:");
        cache.forEach((key, entry) -> System.out.println(key + " -> " + entry.value + " (Freq: " + entry.freq + ")"));
        System.out.println("Frequency Map:");
        freqList.forEach((freq, list) -> {
            System.out.print("Freq " + freq + ": ");
            Entry current = list.head.next;
            while (current != list.tail) {
                System.out.print(current.key + " ");
                current = current.next;
            }
            System.out.println();
        });
    }

    public static void main(String[] args) {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(3);

        {
            // Test Case 1: Normal usage and resize
            lfuCache.put(1, "A");
            lfuCache.put(2, "B");
            lfuCache.put(3, "C");

            System.out.println(lfuCache.get(1)); // Access key 1 (increases its frequency)
            lfuCache.displayCache();
            lfuCache.put(4, "D"); // Evicts key 2 (least frequently used)

            System.out.println(lfuCache.get(2)); // null (evicted)
            System.out.println(lfuCache.get(3)); // C
            System.out.println(lfuCache.get(4)); // D

            lfuCache.resize(2); // Resize cache to capacity 2
            lfuCache.put(5, "E"); // Evicts least frequently used key

            lfuCache.displayCache();
        }

        {
            // Test Case 2: Resize cache to lower capacity
            lfuCache.put(1, "A");
            lfuCache.put(2, "B");
            lfuCache.put(3, "C");

            lfuCache.get(1);
            lfuCache.get(1);

            lfuCache.get(2);

            lfuCache.displayCache();

            lfuCache.resize(1);

            lfuCache.displayCache();
        }
    }
}
