package com.vivek.search.binarysearch;

import java.util.Arrays;

/**
 * 875. Koko Eating Bananas
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * Solution:
 * 💡 Intuition
 *
 * To find the minimum eating speed k such that Koko can eat all the bananas in h hours, we use binary search on possible values of k.
 * If she can finish all piles in h hours with speed k, try a smaller k. If not, increase k.
 * 🛠️ Approach
 *
 *     Binary Search between 1 and max(piles).
 *     For a mid value k, calculate total hours needed using ceil(pile/k) for each pile.
 *     If total hours ≤ h, update answer and search left.
 *     Else, search right.
 *
 * ⏱️ Complexity
 *
 *     Time Complexity: O(nlogm) where n is number of piles, and m is the max value in piles
 *     Space Complexity: O(1)
 *
 * Reference:
 * https://leetcode.com/problems/koko-eating-bananas
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        int k = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hours = 0;
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; // ceiling division
            }
            if (hours <= h) {
                k = mid;
                right = mid - 1; // try smaller speed
            } else {
                left = mid + 1; // try faster speed
            }
        }
        return k;
    }

}
