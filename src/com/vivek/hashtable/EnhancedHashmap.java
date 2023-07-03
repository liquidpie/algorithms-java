package com.vivek.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * You have created a programming language and now you have decided to add hashmap support to it. It was found that in common programming languages, it is impossible to add a number to all hashmap keys/values. So, you have decided to implement your own hashmap in your new language with following operations.
 *
 * insert x y - insert and object with key x and value y
 * get x - return the value of an object with key x
 * addToKey x - add x to all keys in map
 * addToValue y - add y to all values in map
 * Your task is to implement this hashmap, apply the given queries, and to find the sum of all the results for get operations
 *
 * For Example
 *
 * For queryType=["insert","insert","addToValue","addToKey","get"] and query=[[1,2],[2,3],[2],[1],[3]], the output should be hashMap(queryType,query)=5.
 * Explanation
 *
 * insert 1 2 - hashmap will be {1:2}
 * insert 2 3 - hashmap will be {1:2,2:3}
 * addToValue 2 - hashmap will be {1:4,2:5}
 * addToKey 1 - hashmap will be {2:4,3:5}
 * get 3 - value is 5
 *
 * Solution:
 * I would suggest you create your own OffsetIntegerMap that can map between integers and handle an offset on the keys and values.
 *
 * You don't necessarily have to implement the HashMap from scratch, define your own limited interface and
 * implement it with an existing Map<Integer, Integer> through composition.
 *
 * By handling the offsets separately from the keys and values the complexity of the offset operations end up O(1)
 * instead of O(n) when doing recalculations and the Map<> put and get operations stay at their original O(1).
 *
 *
 * https://leetcode.com/discuss/interview-question/933426/OA-Uber
 * https://codereview.stackexchange.com/questions/244468/enhanced-hashmap-add-a-number-to-all-keys-values
 */

public class EnhancedHashmap {

    public static void main(String[] args) {
        String[] queryType = {"insert", "insert", "addToValue", "addToKey", "get"};
        int[][] query = {{1, 2}, {2, 3}, {2}, {1}, {3}};
        System.out.println(hashMap(queryType, query));
    }

    static long hashMap(String[] queryType, int[][] query) {
        long sum = 0;
        var map = new OffsetIntegerMap();
        for (int i = 0; i < queryType.length; i++) {
            String currQuery = queryType[i];
            switch (currQuery) {
                case "insert" -> map.put(query[i][0], query[i][1]);
                case "addToValue" -> map.addToValues(query[i][0]);
                case "addToKey" -> map.addToKeys(query[i][0]);
                case "get" -> sum += map.get(query[i][0]);
            }
        }
        return sum;
    }

    static class OffsetIntegerMap {
        private final Map<Integer, Integer> actualMap;
        private int keyOffset = 0;
        private int valueOffset = 0;

        public OffsetIntegerMap() {
            actualMap = new HashMap<>();
        }

        public int size() {
            return actualMap.size();
        }

        public boolean isEmpty() {
            return actualMap.isEmpty();
        }

        public boolean containsKey(int key) {
            var keyWithoutOffset = key - keyOffset;
            return actualMap.containsKey(keyWithoutOffset);
        }

        public boolean containsValue(int value) {
            var valueWithoutOffset = value - valueOffset;
            return actualMap.containsValue(valueWithoutOffset);
        }

        public Integer get(int key) {
            var keyWithoutOffset = key - keyOffset;
            var value = actualMap.get(keyWithoutOffset);
            if (value == null) return null;
            return value + valueOffset;
        }

        public Integer put(int key, int value) {
            var keyWithoutOffset = key - keyOffset;
            var valueWithoutOffset = value - valueOffset;
            var oldValue = actualMap.put(keyWithoutOffset, valueWithoutOffset);
            if (oldValue == null) return null;
            return oldValue + valueOffset;
        }

        public Integer remove(int key) {
            var keyWithoutOffset = key - keyOffset;
            var oldValue = actualMap.remove(keyWithoutOffset);
            if (oldValue == null) return null;
            return oldValue + valueOffset;
        }

        public void clear() {
            actualMap.clear();
            keyOffset = 0;
            valueOffset = 0;
        }

        public int getKeyOffset() {
            return keyOffset;
        }

        public void setKeyOffset(int keyOffset) {
            this.keyOffset = keyOffset;
        }

        public int getValueOffset() {
            return valueOffset;
        }

        public void setValueOffset(int valueOffset) {
            this.valueOffset = valueOffset;
        }

        public void addToValues(int toAdd) {
            this.valueOffset += toAdd;
        }

        public void addToKeys(int toAdd) {
            this.keyOffset += toAdd;
        }
    }

}
