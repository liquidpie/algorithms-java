package com.vivek.array.pattern.leftright;

/**
 * Consider a devotee wishing to give offerings to temples along with a mountain range.
 * The temples are located in a row at different heights. Each temple should receive at least one offer.
 * If two adjacent temples are at different altitudes,
 * then the temple that is higher up should receive more offerings than the one that is lower down.
 * If two adjacent temples are at the same height, then their offerings relative to each other do not matter.
 * Given the number of temples and the heights of the temples in order, find the minimum number of offerings to bring.
 *
 * Input  : 3
 *          1 2 2
 * Output : 4
 * All temples must receive at-least one offering.
 * Now, the second temple is at a higher altitude
 * compared to the first one. Thus it receives one
 * extra offering.
 * The second temple and third temple are at the
 * same height, so we do not need to modify the
 * offerings. Offerings given are therefore: 1, 2,
 * 1 giving a total of 4.
 *
 * Input  : 6
 *          1 4 3 6 2 1
 * Output : 10
 * We can distribute the offerings in the following
 * way, 1, 2, 1, 3, 2, 1. The second temple has to
 * receive more offerings than the first due to its
 * height being higher. The fourth must receive more
 * than the fifth, which in turn must receive more
 * than the sixth. Thus the total becomes 10.
 *
 *
 * Greedy Approach:
 * ---------------
 * If we somehow manage to make sure that the temple at higher mountain is getting more offerings than our problem is solved.
 * For this we can make use of greedy (since we have to compare only the neighbors of current index).
 * The approach is to do two traversals (in two directions), first one to make sure that the temple gets more offerings than the left temple
 * (at higher position) and second one to make sure that the temple at higher position from the right gets more offerings.
 *
 * Dynamic Programming Approach:
 * ----------------------------
 * By using Dynamic Programming, we can improve the time complexity.
 * In this method, we create a structure of length n which maintains the maximum decreasing chain to the left of each temple
 * and the maximum decreasing chain to the right of each temple. We go through once from 0 to N setting the value of left for each temple.
 * We then go from N to 0 setting the value of right for each temple. We then compare the two and pick the maximum for each temple.
 *
 * Reference: https://www.geeksforgeeks.org/temple-offerings
 */
public class TempleOfferings {

    static int greedyApproach(int[] arr) {
        if (arr == null)
            return -1;

        int n = arr.length;

        int[] offerings = new int[n];
        offerings[0] = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                offerings[i] = offerings[i - 1] + 1;
            } else {
                offerings[i] = 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1] && offerings[i] <= offerings[i + 1]) {
                offerings[i] = offerings[i + 1] + 1;
            }
        }

        int sum = 0;
        for (int val : offerings) {
            sum += val;
        }

        return sum;
    }

    static int dpApproach(int[] arr) {
        if (arr == null)
            return -1;

        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // default cases
        left[0] = 1;
        right[n - 1] = 1;

        // left direction
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // right direction
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // find max of two lists and sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 6, 2, 1};
        System.out.println(greedyApproach(arr));
        System.out.println(dpApproach(arr));
    }

}
