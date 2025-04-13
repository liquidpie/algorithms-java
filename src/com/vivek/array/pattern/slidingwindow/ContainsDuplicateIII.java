package com.vivek.array.pattern.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 220. Contains Duplicate III
 *
 * You are given an integer array nums and two integers indexDiff and valueDiff.
 *
 * Find a pair of indices (i, j) such that:
 *
 *     i != j,
 *     abs(i - j) <= indexDiff.
 *     abs(nums[i] - nums[j]) <= valueDiff, and
 *
 * Return true if such pair exists or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
 * Output: true
 * Explanation: We can choose (i, j) = (0, 3).
 * We satisfy the three conditions:
 * i != j --> 0 != 3
 * abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
 * abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
 *
 * Example 2:
 *
 * Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
 * Output: false
 * Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
 *
 * Solution:
 * https://leetcode.com/problems/contains-duplicate-iii/editorial/
 *
 * Approach #2 (Binary Search Tree) [Accepted]
 * Intuition
 *
 * If elements in the window are maintained in sorted order, we can apply binary search twice to check if Condition 2 is satisfied.
 *
 * By utilizing self-balancing Binary Search Tree, one can keep the window ordered at all times with logarithmic time insert and delete.
 *
 * Algorithm
 *
 * The real bottleneck of Approach #1 is due to all elements in the sliding window are being scanned to check if Condition 2 is satisfied. Could we do better?
 *
 * If elements in the window are in sorted order, we can apply Binary Search twice to search for the two boundaries x+t and x−t for each element x.
 *
 * Unfortunately, the window is unsorted. A common mistake here is attempting to maintain a sorted array.
 * Although searching in a sorted array costs only logarithmic time,
 * keeping the order of the elements after insert and delete operation is not as efficient.
 * Imagine you have a sorted array with k elements and you are adding a new item x.
 * Even if you can find the correct position in O(logk) time, you still need O(k) time to insert x into the sorted array.
 * The reason is that you need to shift all elements after the insert position one step backward.
 * The same reasoning applies to removal as well. After removing an item from position i,
 * you need to shift all elements after i one step forward.
 * Thus, we gain nothing in speed compared to the naive linear search approach above.
 *
 * To gain an actual speedup, we need a dynamic data structure that supports faster insert, search and delete.
 * Self-balancing Binary Search Tree (BST) is the right data structure. The term Self-balancing means the tree automatically keeps
 * its height small after arbitrary insert and delete operations. Why does self-balancing matter?
 * That is because most operations on a BST take time directly proportional to the height of the tree.
 *
 * Here is the entire algorithm in pseudocode:
 *
 * Initialize an empty BST set
 * Loop through the array, for each element x
 * Find the smallest element s in set that is greater than or equal to x, return true if s−x≤t
 * Find the greatest element g in set that is smaller than or equal to x, return true if x−g≤t
 * Put x in set
 * If the size of the set is larger than k, remove the oldest item.
 * Return false
 * One may consider the smallest element s that is greater or equal to x as the successor of x in the BST, as in:
 * "What is the next greater value of x?". Similarly, we consider the greatest element g that is smaller or equal to x
 * as the predecessor of x in the BST, as in: "What is the previous smaller value of x?".
 * These two values s and g are the two closest neighbors from x.
 * Thus by checking the distance from them to x, we can conclude if Condition 2 is satisfied.
 *
 * Complexity Analysis
 *
 * Time complexity: O(nlog(min(n,k))).
 * We iterate through the array of size n. For each iteration, it costs O(log min(k,n)) time (search, insert or delete)
 * in the BST, since the size of the BST is upper bounded by both k and n.
 *
 * Space complexity: O(min(n,k)).
 * Space is dominated by the size of the BST, which is upper bounded by both k and n.
 *
 * Note
 *
 * When the array's elements and t's value are large, they can cause overflow in arithmetic operation.
 * Consider using a larger size data type instead, such as long.
 *
 * -------
 *
 * Approach #3 (Buckets) [Accepted]
 * Intuition
 *
 * Inspired by bucket sort, we can achieve linear time complexity in our problem using buckets as window.
 *
 * Algorithm
 *
 * Bucket sort is a sorting algorithm that works by distributing the elements of an array into a number of buckets.
 * Each bucket is then sorted individually, using a different sorting algorithm. Here is an illustration of buckets.
 *
 * Illustration of buckets
 *
 * Figure 3. Illustration of buckets.
 *
 * From the above example, we have 8 unsorted integers.
 * We create 5 buckets covering the inclusive ranges of [0,9],[10,19],[20,29],[30,39],[40,49] individually.
 * Each of the eight elements is in a particular bucket. For element with value x, its bucket label is x/w and here we have w=10.
 * Sort each bucket using some other sorting algorithm and then collect all of them bucket by bucket.
 *
 * Back to our problem, the critical issue we are trying to solve is:
 *
 * For a given element x is there an item in the window that is within the range of [x−t,x+t]?
 * Could we do this in constant time?
 * Let us consider an example where each element is a person's birthday. Your birthday, say some day in March,
 * is the new element x. Suppose that each month has 30 days and you want to know if anyone has a birthday within t=30 days of yours.
 * Immediately, we can rule out all other months except February, March, April.
 *
 * The reason we know this is because each birthday belongs to a bucket we called month!
 * And the range covered by the buckets are the same as distance t which simplifies things a lot.
 * Any two elements that are not in the same or adjacent buckets must have a distance greater than t.
 *
 * We apply the above bucketing principle and design buckets covering the ranges of ...,[0,t],[t+1,2t+1],....
 * We keep the window using this buckets. Then, each time, all we need to check is the bucket that x belongs to and its two adjacent buckets.
 * Thus, we have a constant time algorithm for searching almost duplicate in the window.
 *
 * One thing worth mentioning is the difference from bucket sort – Each of our buckets contains at most one element at any time,
 * because two elements in a bucket means "almost duplicate" and we can return early from the function.
 * Therefore, a HashMap with an element associated with a bucket label is enough for our purpose.
 *
 * Complexity Analysis
 *
 * Time complexity: O(n).
 * For each of the n elements, we do at most three searches, one insert, and one delete on the HashMap, which costs constant time on average.
 * Thus, the entire algorithm costs O(n) time.
 *
 * Space complexity: O(min(n,k)).
 * Space is dominated by the HashMap, which is linear to the size of its elements. The size of the HashMap is upper bounded by both n and k.
 * Thus the space complexity is O(min(n,k)).
 *
 * Reference: https://leetcode.com/problems/contains-duplicate-iii
 */
public class ContainsDuplicateIII {

    public static void main(String[] args) {
        {
            int[] nums = {1, 2, 3, 1};
            boolean result = containsNearbyAlmostDuplicate(nums, 3, 0);
            System.out.println(result);
        }
        {
            int[] nums = {1,5,9,1,5,9};
            boolean result = containsNearbyAlmostDuplicate(nums, 2, 3);
            System.out.println(result);
        }
        {
            int[] nums = {1, 2, 1, 1};
            boolean result = containsNearbyAlmostDuplicate(nums, 1, 0);
            System.out.println(result);
        }
        {
            int[] nums = {-2, 3};
            boolean result = containsNearbyAlmostDuplicate(nums, 2, 5);
            System.out.println(result);
        }
    }

    static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {

            Integer s = set.ceiling(nums[i]);
            if (s != null && (long) s <= nums[i] + valueDiff)
                return true;

            Integer g = set.floor(nums[i]);
            if (g != null && g >= nums[i] - valueDiff)
                return true;

            set.add(nums[i]);
            if (set.size() > indexDiff)
                set.remove(nums[i - indexDiff]);
        }

        return false;
    }

    static boolean containsNearbyAlmostDuplicateLinearTime(int[] nums, int indexDiff, int valueDiff) {
        Map<Long, Long> buckets = new HashMap<>();
        long window = (long) valueDiff + 1;

        for (int i = 0; i < nums.length; i++) {
            // Get the ID of the bucket from element value x and bucket width w
            // Java's division `/` rounds towards zero, but we need floor division for correct bucketing.
            long bucket = Math.floorDiv(nums[i], window);

            if (buckets.containsKey(bucket))
                return true;

            if (buckets.containsKey(bucket - 1) && Math.abs(nums[i] - buckets.get(bucket - 1)) <= valueDiff)
                return true;

            if (buckets.containsKey(bucket + 1) && Math.abs(nums[i] - buckets.get(bucket + 1)) <= valueDiff)
                return true;

            buckets.put(bucket, (long) nums[i]);
            if (buckets.size() > indexDiff) {
                buckets.remove(Math.floorDiv(nums[i - indexDiff], window));
            }
        }
        return false;
    }

}
