package com.vivek.search.examples;

import java.util.HashMap;
import java.util.Map;

/**
 * 278. First Bad Version
 *
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 *
 * Example 1:
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 *
 * Example 2:
 *
 * Input: n = 1, bad = 1
 * Output: 1
 *
 * Solution:
 *
 * Intuition -
 *
 * The versions are ordered, and if a version is bad, all subsequent ones are bad too.
 * This suggests a binary search approach to find the first bad version efficiently.
 *
 *
 * Initialize two pointers: left = 1, right = n.
 * While left < right, compute mid = (left + right) // 2.
 * If mid is a bad version, narrow the search to the left half by setting right = mid.
 * Otherwise, search the right half by setting left = mid + 1.
 * When the loop ends, left points to the first bad version.
 *
 * Complexity -
 *
 * Time complexity: (O(log n))
 * Space complexity: (O(1))
 *
 *
 * Reference: https://leetcode.com/problems/first-bad-version
 */
public class FirstBadVersion {

    private int bad;

    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (isBadVersion(m))
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    private boolean isBadVersion(int k) {
        return k >= bad;
    }

    public static void main(String[] args) {
        FirstBadVersion helper = new FirstBadVersion();
        {
            helper.bad = 1;
            System.out.println(helper.firstBadVersion(3));
        }
    }

}
