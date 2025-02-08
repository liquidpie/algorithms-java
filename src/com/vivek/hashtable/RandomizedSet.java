package com.vivek.hashtable;

import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)
 *
 * Implement the RandomizedSet class:
 *
 *  - RandomizedSet() Initializes the RandomizedSet object.
 *  - bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 *  - bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 *  - int getRandom() Returns a random element from the current set of elements
 *    (it's guaranteed that at least one element exists when this method is called).
 *    Each element must have the same probability of being returned.
 *
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * Example 1:
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 *
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 *
 * Solution:
 * We're asked to implement the structure which provides the following operations in average O(1) time:
 *
 *  - Insert
 *  - Delete
 *  - GetRandom
 *
 * First of all - why this weird combination? The structure looks quite theoretical, but it's widely used in popular statistical algorithms
 * like Markov chain Monte Carlo and Metropolis–Hastings algorithm. These algorithms are for sampling from a probability distribution when
 * it's difficult to compute the distribution itself.
 *
 * Let's figure out how to implement such a structure. Starting from the Insert, we immediately have two good candidates with O(1) average insert time:
 *
 *      Hashmap (or HashSet, the implementation is very similar): Java HashMap / Python dictionary
 *      Array List: Java ArrayList / Python list
 *
 * Let's consider them one by one.
 *
 * Hashmap provides Insert and Delete in average constant time, although has problems with GetRandom.
 *
 * The idea of GetRandom is to choose a random index and then retrieve an element with that index. There are no indexes in the hashmap,
 * and hence to get a true random value, one has first to convert hashmap keys in a list, which would take linear time.
 * The solution here is to build a list of keys aside and use this list to compute GetRandom in constant time.
 *
 *      | Array List has indexes and could provide Insert and GetRandom in average constant time, though has problems with Delete.
 *
 * Deleting a value at an arbitrary index takes linear time. The solution here is to always delete the last value:
 *
 *      - Swap the element to delete with the last one.
 *      - Pop the last element out.
 *
 * For that, one has to compute an index of each element in constant time and hence needs a hashmap which stores element -> its index dictionary.
 *
 * Both ways converge into the same combination of data structures:
 *  - Hashmap element -> its index.
 *  - Array List of elements.
 *
 * Reference:
 * https://leetcode.com/problems/insert-delete-getrandom-o1/description/
 */
public class RandomizedSet {

    private final Map<Integer, Integer> map; // num -> index
    private final List<Integer> nums;
    private final Random random;

    public RandomizedSet() {
        this.map = new HashMap<>();
        this.nums = new ArrayList<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        nums.add(val);
        map.put(val, nums.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;
        int idx = map.get(val);
        int lastIdx = nums.size() - 1;
        // swap last element to remove it in O(1)
        if (idx != lastIdx) {
            nums.set(idx, nums.get(lastIdx));
            map.put(nums.get(idx), idx);
        }
        nums.remove(lastIdx);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        randomizedSet.insert(3);

        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(1, 0);
        freq.put(2, 0);
        freq.put(3, 0);
        for (int i = 0; i < 300; i++) {
            int num = randomizedSet.getRandom();;
            freq.put(num, freq.get(num) + 1);
        }

        System.out.println(freq);
    }

}
