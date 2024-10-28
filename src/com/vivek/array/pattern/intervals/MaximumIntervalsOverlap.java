package com.vivek.array.pattern.intervals;

import java.util.Arrays;

/**
 *  Given a list of intervals, find the point where the maximum number of intervals overlap.
 *
 *  Problem variant 1:
 *  There is entry time array and exit time array for each person respectively we need to find out the time where there are maximum person.
 *  eg: entry= [1,6,7,3,9,5] exit: [4, 8,10, 6, 12, 10]
 *  this means one person enters at 1 and leave by 4 then second enter at 6 and leave by 8 so on 2 question:
 *  Which DS we can use to 1. insert 2. delete 3. seacrh 4. get random in O(1) complexity for each operation.
 *  There might be more than 1 DS we can use. Ans is : hashmap and array
 *
 *  Problem variant 2:
 * Consider a big party where a log register for guest’s entry and exit times is maintained.
 * Find the time at which there are maximum guests in the party. Note that entries in register are not in any order.
 * Example :
 *
 * Input: arrl[] = {1, 2, 9, 5, 5}
 *        exit[] = {4, 5, 12, 9, 12}
 * First guest in array arrives at 1 and leaves at 4,
 * second guest arrives at 2 and leaves at 5, and so on.
 *
 * Output: 5
 * There are maximum 3 guests at time 5.
 *
 * Solution ##
 * An Efficient Solution is to use sorting n O(nLogn) time. The idea is to consider all events (all arrivals and exits) in sorted order.
 * Once we have all events in sorted order, we can trace the number of guests at any time keeping track of guests that have arrived, but not exited.
 * Consider the above example.
 *
 *     arr[]  = {1, 2, 10, 5, 5}
 *     dep[]  = {4, 5, 12, 9, 12}
 *
 * Below are all events sorted by time.  Note that in sorting, if two
 * events have same time, then arrival is preferred over exit.
 *  Time     Event Type         Total Number of Guests Present
 * ------------------------------------------------------------
 *    1        Arrival                  1
 *    2        Arrival                  2
 *    4        Exit                     1
 *    5        Arrival                  2
 *    5        Arrival                  3    // Max Guests
 *    5        Exit                     2
 *    9        Exit                     1
 *    10       Arrival                  2
 *    12       Exit                     1
 *    12       Exit                     0
 *
 * Total number of guests at any time can be obtained by subtracting
 * total exits from total arrivals by that time.
 * So maximum guests are three at time 5.
 * Following is the implementation of above approach. Note that the implementation doesn’t create a single sorted list of all events,
 * rather it individually sorts arr[] and dep[] arrays, and then uses merge process of merge sort to process them together as a single sorted array.
 *
 * Time Complexity: O(nLogn).
 * Space Complexity: O(1) as no extra space has been taken.
 *
 * Reference:
 * 1. Grokking the Coding Interview
 *    Pattern: Merge Intervals
 * 2. https://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
 *
 */
public class MaximumIntervalsOverlap {

    public static void main(String[] args) {
        int[] arrival = {1, 2, 10, 5, 5};
        int[] exit = {4, 5, 12, 9, 12};
        findMaxGuests(arrival, exit);
    }

    static void findMaxGuests(int[] arrival, int[] exit) {
        int n = arrival.length;
        Arrays.sort(arrival);
        Arrays.sort(exit);

        // guestsIn indicates number of guests at a time
        int guestsIn = 1;
        int maxGuests = 1;
        int time = arrival[0];
        int i = 1;
        int j = 0;

        // Similar to merge in merge sort to process
        // all events in sorted order
        while (i < n && j < n) {
            if (arrival[i] <= exit[j]) {
                guestsIn++;
                if (guestsIn > maxGuests) {
                    maxGuests = guestsIn;
                    time = arrival[i];
                }
                i++;
            } else {
                guestsIn--;
                j++;
            }
        }

        System.out.println("Maximum Number of Guests = " + maxGuests + " at time " + time);
    }

}
