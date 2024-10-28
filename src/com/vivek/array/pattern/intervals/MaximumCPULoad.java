package com.vivek.array.pattern.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Maximum CPU Load (hard) #
 *
 * We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running. Our goal
 * is to find the maximum CPU load at any time if all the jobs are running on the same machine.
 *
 * Example 1:
 * Jobs: [[1,4,3], [2,5,4], [7,9,6]]
 * Output: 7
 * Explanation: Since [1,4,3] and [2,5,4] overlap, their maximum CPU load (3+4=7) will be when bot
 * h the
 * jobs are running at the same time i.e., during the time interval (2,4).
 *
 * Example 2:
 * Jobs: [[6,7,10], [2,4,11], [8,12,15]]
 * Output: 15
 * Explanation: None of the jobs overlap, therefore we will take the maximum load of any job which
 * is 15.
 *
 * Example 3:
 * Jobs: [[1,4,2], [2,4,1], [3,6,5]]
 * Output: 8
 * Explanation: Maximum CPU load will be 8 as all jobs overlap during the time interval [3,4].
 *
 * Solution #
 * The problem follows the Merge Intervals pattern and can easily be converted to Minimum Meeting Rooms.
 * Similar to ‘Minimum Meeting Rooms’ where we were trying to find the maximum number of meetings happening at any time,
 * for ‘Maximum CPU Load’ we need to find the maximum number of jobs running at any time.
 * We will need to keep a running count of the maximum CPU load at any time to find the overall maximum load.
 *
 * Time Complexity #
 * The time complexity of the above algorithm is O(N ∗ logN), where ‘N’ is the total number of jobs.
 * This is due to the sorting that we did in the beginning. Also, while iterating the jobs,
 * we might need to poll/offer jobs to the priority queue. Each of these operations can take O(logN).
 * Overall our algorithm will take O(N logN ).
 *
 * Space complexity #
 * The space complexity of the above algorithm will be O(N), which is required for sorting.
 * Also, in the worst case, we have to insert all the jobs into the priority queue (when all jobs overlap) which will also take O(N) space.
 * The overall space complexity of our algorithm is O(N).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Merge Intervals
 */
public class MaximumCPULoad {

    public static void main(String[] args) {
        int[][] arr = {{1,4,3}, {2,5,4}, {7,9,6}};
        Job[] jobs = new Job[arr.length];
        for (int i = 0; i < arr.length; i++) {
            jobs[i] = new Job(arr[i][0], arr[i][1], arr[i][2]);
        }

        System.out.println(maxCpuLoad(jobs));
    }

    static int maxCpuLoad(Job[] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(j -> j.start));

        PriorityQueue<Job> queue = new PriorityQueue<>((Comparator.comparingInt(j -> j.end)));
        int maxLoad = 0;
        int currenLoad = 0;

        for (Job job : jobs) {
            // remove all jobs that have ended
            while (!queue.isEmpty() && job.start > queue.peek().end) {
                currenLoad -= queue.poll().cpuLoad;
            }

            // add the current job into the minHeap
            queue.add(job);
            currenLoad += job.cpuLoad;
            maxLoad = Math.max(maxLoad, currenLoad);
        }
        return maxLoad;
    }

    static class Job {
        int start;
        int end;
        int cpuLoad;

        public Job(int start, int end, int cpuLoad) {
            this.start = start;
            this.end = end;
            this.cpuLoad = cpuLoad;
        }
    }

}
