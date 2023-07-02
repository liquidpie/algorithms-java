package com.vivek.array.maxmin;

/**
 * Minimum Time to Complete Trips
 *
 * You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.
 *
 * Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip. Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.
 *
 * You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. Return the minimum time required for all buses to complete at least totalTrips trips.
 *
 * Example 1:
 *
 * Input: time = [1,2,3], totalTrips = 5
 * Output: 3
 * Explanation:
 * - At time t = 1, the number of trips completed by each bus are [1,0,0].
 *   The total number of trips completed is 1 + 0 + 0 = 1.
 * - At time t = 2, the number of trips completed by each bus are [2,1,0].
 *   The total number of trips completed is 2 + 1 + 0 = 3.
 * - At time t = 3, the number of trips completed by each bus are [3,1,1].
 *   The total number of trips completed is 3 + 1 + 1 = 5.
 * So the minimum time needed for all buses to complete at least 5 trips is 3.
 *
 * Example 2:
 *
 * Input: time = [2], totalTrips = 1
 * Output: 2
 * Explanation:
 * There is only one bus, and it will complete its first trip at t = 2.
 * So the minimum time needed to complete 1 trip is 2.
 *
 * Solution:
 * https://walkccc.me/LeetCode/problems/2187/
 * https://leetcode.com/discuss/interview-question/1515709/Uber-or-CodeSignal-OA-or-SE-2
 *
 * https://leetcode.com/problems/minimum-time-to-complete-trips/
 */
public class MinimumTimeToCompleteTrips {

    public static void main(String[] args) {
        int[] times = {1,3,5,7,8};
        int noOfTrips = 10;

        System.out.println(minimumTime(times, noOfTrips));
    }

    static long minimumTime(int[] time, int totalTrips) {
        // finding the bus from the array which takes minimum time
        int minTime = minBusTime(time);
        long l = 1; // lowest unit of time
        long h = (long) minTime * totalTrips;
        while (l < h) {
            long mid = (l + h) / 2;
            // find trips completed in mid unit of time
            long count = findTrips(time, mid);
            if (count < totalTrips) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return h;
    }

    static long findTrips(int[] time, long mid) {
        long numTrips = 0;
        for (int i = 0; i < time.length; i++) {
            numTrips += mid / time[i];
        }

        return numTrips;
    }

    private static int minBusTime(int[] time) {
        int min = time[0];
        for (int i = 1; i < time.length; i++) {
            min = Math.min(min, time[i]);
        }

        return min;
    }

}
