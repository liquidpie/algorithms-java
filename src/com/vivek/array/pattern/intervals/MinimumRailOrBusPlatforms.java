package com.vivek.array.pattern.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Minimum Number of Platforms Required for a Railway/Bus Station
 *
 * Given the arrival and departure times of all trains that reach a railway station,
 * the task is to find the minimum number of platforms required for the railway station so that no train waits.
 * We are given two arrays that represent the arrival and departure times of trains that stop.
 *
 * Examples:
 *
 *     Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}, dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
 *     Output: 3
 *     Explanation: There are at-most three trains at a time (time between 9:40 to 12:00)
 *
 *     Input: arr[] = {9:00, 9:40}, dep[] = {9:10, 12:00}
 *     Output: 1
 *     Explanation: Only one platform is needed.
 *
 * Solution 1: Using Heap ->
 * Store the arrival time and departure time and sort them based on arrival time then check if the arrival time of the
 * next train is smaller than the departure time of the previous train if it is smaller then increment the number of the
 * platforms needed otherwise not.
 * Time Complexity: O(N*log(N)), Heaps take log(n) time for pushing element and there are n elements.
 * Auxiliary Space: O(N), Space required by heap to store the element.
 * ---------------------------------------------------------------------------------------------------------------------
 * Solution 2: Using Event Sorting ->
 * The idea is to consider all events in sorted order. Once the events are in sorted order,
 * trace the number of trains at any time keeping track of trains that have arrived, but not departed.
 *
 *     arr[]  = {9:00,  9:40, 9:50,  11:00, 15:00, 18:00}
 *     dep[]  = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
 *
 *     All events are sorted by time.
 *
 *     Total platforms at any time can be obtained by subtracting total departures from total arrivals by that time.
 *
 *      Time      Event Type     Total Platforms Needed at this Time
 *      9:00       Arrival                      1
 *      9:10       Departure                0
 *      9:40       Arrival                      1
 *      9:50       Arrival                      2
 *      11:00      Arrival                     3
 *      11:20      Departure               2
 *      11:30      Departure               1
 *      12:00      Departure               0
 *      15:00      Arrival                     1
 *      18:00      Arrival                     2
 *      19:00      Departure               1
 *      20:00      Departure               0
 *
 *     Minimum Platforms needed on railway station = Maximum platforms needed at any time = 3
 *
 * Time Complexity: O(N * log N), One traversal O(n) of both the array is needed after sorting O(N * log N).
 * Auxiliary space: O(1), As no extra space is required.
 * ---------------------------------------------------------------------------------------------------------------------
 * Solution 3: Using Named Time array (O(n) time complexity) ->
 * All the trains will arrive and depart in only one day, i.e. a 24-hour time frame, hence we can use an array and
 * for every arrival increase the value at that time (time in 24-hour format),
 * and for every departure decrement the value at the index. In the array, every index denotes time in 24hr format and
 * the sum of traversal up to that index is the number of platforms required, at that time.
 *
 * Illustration:
 *
 *     arrivals – { 1200 , 1230 , 1240 , 1245}
 *     departures – { 1210 , 1245 , 1250 , 1250}
 *     make an array of size 2361 having 0 as the default value .
 *     for the above example the array will have the following values – 1 ,-1 , 1 , -1 , 0 , -2
 *     at indices – 1200 , 1210 , 1230 ,1240 ,1245,1250
 *     Rest all indices have the default value of 0, the number of platforms required at different times are –
 *     platforms        time
 *        1             1200
 *        2             1210
 *        3             1230
 *        2             1240
 *        2             1245
 *        1             1250
 *     3 is the max so 3 is the answer
 *
 * Complexity Analysis:
 *     Time Complexity: O(N).
 *     Space Complexity: O(1).
 *
 * https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 */
public class MinimumRailOrBusPlatforms {

    public static void main(String[] args) {
        int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };
        System.out.println(countPlatformsUsingNamedTimeArray(arr, dep));
    }

    static int countPlatformsUsingNamedTimeArray(int[] arr, int[] dep) {
        int n = arr.length;
        // As time range from 0 to 2359 in
        // 24 hour clock, we declare an array
        // for values from 0 to 2360
        int[] platform = new int[2361];

        int count = 1;
        for (int i = 0; i < n; i++) {
            // Increment the platforms for arrival
            ++platform[arr[i]];
            // Once train departs we decrease the platform count
            --platform[dep[i]];
        }
        // We are running loop till 2361 because
        // maximum time value in a day can be 23:60
        for (int i = 1; i < 2361; i++) {
            // Taking cumulative sum of platform give us required number of platform for every minute
            platform[i] = platform[i] + platform[i - 1];
            count = Math.max(count, platform[i]);
        }
        return count;
    }

    static int countPlatformsUsingEventSorting(int[] arr, int[] dep) {
        // Sort arrival and departure arrays
        Arrays.sort(arr);
        Arrays.sort(dep);

        // plat_needed indicates number of platforms
        // needed at a time
        int platformNeeded = 1;
        int count = 1;
        int i = 1;
        int j = 0;
        int n = arr.length;

        // Similar to merge in merge sort to process
        // all events in sorted order
        while (i < n && j < n) {
            // If next event in sorted order is arrival,
            // increment count of platforms needed
            if (arr[i] <= dep[j]) {
                platformNeeded++;
                i++;
            }
            // Else decrement count of platforms needed
            else if (arr[i] > dep[j]) {
                platformNeeded--;
                j++;
            }
            // Update result if needed
            if (platformNeeded > count) {
                count = platformNeeded;
            }
        }

        return count;
    }

    static int countPlatformsUsingHeap(int[] arr, int[] dep) {
        TrainSchedule[] trains = new TrainSchedule[arr.length];

        // store the departure and arrival time
        for (int i = 0; i < arr.length; i++) {
            trains[i] = new TrainSchedule(arr[i], dep[i]);
        }

        // sort this array based on arrival time
        Arrays.sort(trains, Comparator.comparingInt(t -> t.arrTime));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(trains[0].depTime);
        int count = 1;
        for (int i = 1; i < trains.length; i++) {
            TrainSchedule current = trains[i];
            // check if the arrival time of the current train is less than or equal to the departure time
            // of the previous train which is kept on top of the priority queue
            if (current.arrTime <= priorityQueue.peek()) {
                count++;
            } else {
                priorityQueue.poll();
            }
            // push new departure time in the priority queue
            priorityQueue.add(current.depTime);
        }

        return count;
    }

    private static class TrainSchedule {
        int arrTime, depTime;

        public TrainSchedule(int arrTime, int depTime) {
            this.arrTime = arrTime;
            this.depTime = depTime;
        }
    }



}
