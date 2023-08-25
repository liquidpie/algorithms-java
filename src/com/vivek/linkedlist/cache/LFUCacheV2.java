package com.vivek.linkedlist.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFU Cache
 *
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 *     LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 *     int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 *     void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 *
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 * https://leetcode.com/problems/lfu-cache/solutions/3117785/java-hashmap-dll/
 */
public class LFUCacheV2 {

    private final int capacity;
    private final Map<Integer, Node> cache; // [Key, Value]
    private final Map<Integer, LinkedHashSet<Node>> frequencyMap; // [Frequency, LinkedHashSet in LRU order (LRU Node to the end)]
    private int minFrequency = 0;

    public LFUCacheV2(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;

        Node node = cache.get(key);
        incrementFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            incrementFrequency(node);
        } else {
            if (cache.size() == capacity) {
                Node removedNode = frequencyMap.get(minFrequency).iterator().next();
                System.out.println("Evicted Node: " + removedNode);
                frequencyMap.get(minFrequency).remove(removedNode);
                cache.remove(removedNode.key);
            }

            minFrequency = 1;
            Node node = new Node(key, value, 1);
            cache.put(key, node);
            frequencyMap.putIfAbsent(node.frequency, new LinkedHashSet<>());
            frequencyMap.get(node.frequency).add(node);
        }
    }

    private void incrementFrequency(Node node) {
        frequencyMap.get(node.frequency).remove(node); // O(1) operation
        if (node.frequency == minFrequency && frequencyMap.get(minFrequency).isEmpty())
            minFrequency++;
        node.frequency++;
        frequencyMap.putIfAbsent(node.frequency, new LinkedHashSet<>());
        frequencyMap.get(node.frequency).add(node);
    }

    static class Node {
        int key;
        int value;
        int frequency;

        public Node(int key, int value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", frequency=" + frequency +
                    '}';
        }
    }

    public static void main(String[] args) {
        LFUCacheV2 lfuCache = new LFUCacheV2(3);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.put(3, 3);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.put(3, 33);
        lfuCache.put(1, 11);
        lfuCache.put(4, 4);
    }

}
