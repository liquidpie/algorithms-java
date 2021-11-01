package com.vivek.array.examples;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given a list of intervals representing the start and end time of ‘N’ meetings,
 * find the minimum number of rooms required to hold all the meetings.
 *
 * Approach 1: Using Prefix Sum
 *
 * Reference: https://www.geeksforgeeks.org/minimum-halls-required-for-class-scheduling
 *
 * Approach 2: Using Priority Queue
 *
 * Instead of manually iterating on every room that’s been allocated and checking if the room is available or not, we can keep all the rooms in a min heap where the key for the min heap would be the ending time of meeting.
 * So, every time we want to check if any room is free or not, simply check the topmost element of the min heap as that would be the room that would get free the earliest out of all the other rooms currently occupied.
 * If the room we extracted from the top of the min heap isn’t free, then no other room is. So, we can save time here and simply allocate a new room.
 *
 *  1. Sort the given meetings by their start time.
 *  2. Initialize a new min-heap and add the first meeting’s ending time to the heap. We simply need to keep track of the ending times as that tells us when a meeting room will get free.
 *  3. For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
 *  4. If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
 *  5. If not, then we allocate a new room and add it to the heap.
 *  6. After processing all the meetings, the size of the heap will tell us the number of rooms allocated.
 *
 * Reference: https://www.learnbay.io/meeting-rooms-ii
 *
 */
public class MinimumMeetingRooms {

    static int usingPrefixSum(int[][] arr) {
        if (arr == null)
            return 0;
        if (arr.length == 1)
            return 1;

        // find max end time
        int max = 0;
        for (int[] elt : arr) {
            max = Math.max(max, elt[1]);
        }

        int[] prefixSum = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            prefixSum[arr[i][0]]++;
            prefixSum[arr[i][1]]--;
        }

        int result = prefixSum[0];
        for (int i = 1; i <= max; i++) {
            prefixSum[i] += prefixSum[i - 1];
            result = Math.max(result, prefixSum[i]);
        }
        return result;
    }

    static int usingPriorityQueue(int[][] arr) {
        if (arr == null)
            return 0;
        if (arr.length <= 1)
            return arr.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        // sort meetings by start time
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // add the first meeting
        queue.add(arr[0][1]);

        for (int i = 1; i < arr.length; i++) {
            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (arr[i][0] >= queue.peek()) {
                queue.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            queue.add(arr[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return queue.size();
    }

    public static void main(String[] args) {
        int[][] meetings = {
                { 0, 5 }, { 1, 2 }, { 1, 10 }
        };

        System.out.println(usingPriorityQueue(meetings));
    }

}
